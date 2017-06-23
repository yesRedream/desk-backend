package com.apo.config;

import com.apo.util.Constants;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration{

    @Override
    protected String getDatabaseName() {
        return Constants.MONGO_DB;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(Constants.MONGO_URL);
    }

}
