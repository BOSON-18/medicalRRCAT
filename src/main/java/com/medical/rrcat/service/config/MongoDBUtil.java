package com.medical.rrcat.service.config;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MongoDBUtil {
    private static MongoClient mongoClient;
    private static DB database;
    private static GridFS gridFSBucket;


    static {
        try {
            // ✅ Replace with your actual MongoDB Cloud URI
            Database db = new Database();
            String mongoURI = db.getMongoURI();
            // ✅ Create a MongoDB Client using the URI
            MongoClientURI uri = new MongoClientURI(mongoURI);
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDB("rrcat");
            gridFSBucket = new GridFS(database);

            System.out.println("✅ MongoDB Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ MongoDB Connection Failed!", e);
        }
    }

    // ✅ Method to get the database instance
    public static DB getDatabase() {
        return database;
    }


    public ObjectId uploadPdfFile(String filePath, String fileName) {

        try {

            File pdfFile = new File(filePath);
            InputStream inputStream = new FileInputStream(pdfFile);
            GridFSInputFile gridFsFile = gridFSBucket.createFile(inputStream);
            gridFsFile.setFilename(fileName);
            gridFsFile.setContentType("application.pdf");

            // setting chunk size to max 75 Mb
            gridFsFile.setChunkSize(75 * 1024 * 1024);
            // Bhot Saare chunks banenge inke ab if file size exceeds 75 Mb
            gridFsFile.save();

            return (ObjectId) gridFsFile.getId();


        } catch (Exception e) {
            System.out.println("File Upload Exception->" + e);
            return null;
        }

    }

    public GridFSDBFile getFileById(ObjectId fileId) {
        return gridFSBucket.findOne(fileId);
    }

    public void downloadPdfFile(ObjectId fileId, String downloadPath) {
        try {

            GridFSDBFile gridFSDBFile = gridFSBucket.findOne(fileId);

            if (gridFSDBFile != null) {
                FileOutputStream outputStream = new FileOutputStream(downloadPath);
                gridFSDBFile.writeTo(outputStream);
                outputStream.close();
                System.out.println("File downloaded successfully to " + downloadPath);
            }

        } catch (Exception e) {
            System.out.println("Download PDF Error->" + e);

        }
    }

    // ✅ Method to close the connection (Call this when done)
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("✅ MongoDB Connection Closed!");
        }
    }
}
