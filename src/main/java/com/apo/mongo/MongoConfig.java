package com.apo.mongo;

import com.apo.util.PropertyManager;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
    @Autowired
    private PropertyManager propertyManager;

    @Override
    protected String getDatabaseName() {
        return propertyManager.getProperty(MongoProperty.DATABASE);
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential
                = MongoCredential.createCredential(propertyManager.getProperty(MongoProperty.USER),
                propertyManager.getProperty(MongoProperty.DATABASE),
                propertyManager.getProperty(MongoProperty.PASSWORD).toCharArray());
        return new MongoClient(new ServerAddress(propertyManager.getProperty(MongoProperty.HOST)),
                Arrays.asList(credential));
    }

}
