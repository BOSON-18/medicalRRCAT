package com.medical.rrcat.service.config;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;

public class MongoDBUtil {
    private static MongoClient mongoClient;
    private static DB database;


    static {
        try {
            // ✅ Replace with your actual MongoDB Cloud URI
             Database db=new Database();
            String mongoURI = db.getMongoURI();
            // ✅ Create a MongoDB Client using the URI
            MongoClientURI uri = new MongoClientURI(mongoURI);
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDB("rrcat");

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
    public static void getAllMedicalRecords() {
        try {
            DBCollection collection = database.getCollection("test");
            DBCursor cursor = collection.find();

            System.out.println("🔹 All Documents in 'medical' Collection:");
            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                System.out.println(document);
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
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
