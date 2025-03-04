package com.medical.rrcat.service;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class App {
    public static void main(String[] args) {
        try {
            // ✅ Get MongoDB Database
            DB database = MongoDBUtil.getDatabase();
            // same for Postgres -> add jar in lib and first connect to admin

            // ✅ Get a Collection
            DBCollection collection = database.getCollection("medical");

            // ✅ Print success message
            System.out.println("✅ Connected to MongoDB! Collection: " + collection.getName());

            MongoDBUtil.getAllMedicalRecords();
            // ✅ Close the connection when done
            MongoDBUtil.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
