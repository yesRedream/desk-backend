package com.apo.config;

import com.apo.db.MongoService;
import com.apo.model.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@Configuration
public class AppConfig {
    @Autowired
    private MongoService mongoService;

    @Bean
    public Desk getDesk() {
        return mongoService.findDesk();
    }
}
