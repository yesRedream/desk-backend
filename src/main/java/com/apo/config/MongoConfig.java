package com.apo.config;

import com.apo.util.Util;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 07/06/2017.
 */
@Configuration
public class MongoConfig {
    @Bean
    public Mongo getMongo() {
        MongoClient client = new MongoClient(Util.MONGO_URL);
        return client;
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
        MongoTemplate template = new MongoTemplate(getMongo(), Util.MONGO_DB);
        return template;
    }
}
