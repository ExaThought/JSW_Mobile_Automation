package com.qapitol.jsw.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.testng.Assert;

public class MongoDBDao {

    public void getMongoDBCart(String cartId){
        // MongoDB URI
        String uri = "mongodb+srv://jayadev:wwlMsdJY3np1nKIe@jswone-qa-cluster.imuf0.mongodb.net/jswone?appName=jswone-qa-cluster";

        // Create MongoDB client
        MongoClient mongoClient = MongoClients.create(uri);

        // Connect to database and collection
        MongoDatabase database = mongoClient.getDatabase("jswone");
        MongoCollection<Document> collection = database.getCollection("expired_cart");

        // Provide the cart ID to fetch
        Document query = new Document("_id", cartId);  // Treat _id as String

        // Fetch the cart document
        Document cart = collection.find(query).first();

        //Read the cart status from mongoDB and check the status
        Document metadata=(Document) cart.get("metadata");
        String status=metadata.getString("status");
        Assert.assertEquals(status,"INACTIVE");

        // Close connection
        mongoClient.close();
    }
}
