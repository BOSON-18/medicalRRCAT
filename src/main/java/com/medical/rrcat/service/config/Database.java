package com.medical.rrcat.service.config;

public class Database {

    private String mongoURI= "mongodb+srv://deeshankbatra663:Cleverfox18@cluster0.bkfjew1.mongodb.net/";
    private String postURI="jdbc:postgresql://localhost:5432/postgres";
    private String postuser="postgres";
    private String password="postgres";

    public String getPostURI() {
        return postURI;
    }

    public String getPostuser() {
        return postuser;
    }

    public String getPassword() {
        return password;
    }

    public String getMongoURI() {
        return mongoURI;
    }
}
