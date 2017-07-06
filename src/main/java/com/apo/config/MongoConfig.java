package com.apo.config;

import com.apo.util.Constants;
import com.apo.util.PropertyManager;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
    @Autowired
    private PropertyManager propertyManager;

    @Override
    protected String getDatabaseName() {
        return Constants.MONGO_DB;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential
                = MongoCredential.createCredential(propertyManager.getProperty("db.mongo.user"),
                propertyManager.getProperty("db.mongo.database"),
                propertyManager.getProperty("db.mongo.password").toCharArray());
        return new MongoClient(new ServerAddress(propertyManager.getProperty("db.mongo.host")),
                Arrays.asList(credential));
    }

}
