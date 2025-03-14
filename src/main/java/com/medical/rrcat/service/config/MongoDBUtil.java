package com.medical.rrcat.service.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MongoDBUtil {
    private static MongoClient mongoClient;
    private static GridFS gridFS;

    static {
        try {
            Database db = new Database();
            MongoClientURI uri = new MongoClientURI(db.getMongoURI());
            mongoClient = new MongoClient(uri);

            // Use legacy GridFS constructor
            gridFS = new GridFS(mongoClient.getDB("rrcat"), "pdfs");
            System.out.println("✅ MongoDB Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ MongoDB Connection Failed!", e);
        }
    }

    public ObjectId uploadPdfFile(byte[] fileBytes, String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(fileBytes);

            GridFSInputFile gridFsFile = gridFS.createFile(inputStream);
            gridFsFile.setFilename(fileName);
            gridFsFile.setContentType("application/pdf");
            gridFsFile.setChunkSize(75 * 1024 * 1024);
            gridFsFile.save();

            return (ObjectId) gridFsFile.getId();
        } finally {
            closeQuietly(inputStream);
        }
    }

    public byte[] downloadPdfContent(String hexObjectId) {
        ByteArrayOutputStream baos = null;
        try {
            ObjectId fileId = new ObjectId(hexObjectId);
            GridFSDBFile gridFSFile = gridFS.findOne(fileId);

            if (gridFSFile != null) {
                baos = new ByteArrayOutputStream();
                gridFSFile.writeTo(baos);
                return baos.toByteArray();
            }
            return null;
        } catch (Exception e) {
            System.err.println("Download error: " + e.getMessage());
            return null;
        } finally {
            closeQuietly(baos);
        }
    }

    private static void closeQuietly(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                System.err.println("Error closing stream: " + e.getMessage());
            }
        }
    }

    private static void closeQuietly(ByteArrayOutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                System.err.println("Error closing stream: " + e.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("✅ MongoDB Connection Closed!");
        }
    }
}
