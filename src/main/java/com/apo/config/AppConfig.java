package com.apo.config;

import com.apo.model.Desk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@Configuration
public class AppConfig {

    @Bean
    public Desk getDesk() {
        return new Desk();
    }
}
