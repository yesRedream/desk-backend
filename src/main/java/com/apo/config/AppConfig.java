package com.apo.config;

import com.apo.db.MongoService;
import com.apo.model.desk.Desk;
import com.apo.model.user.UserDAO;
import com.apo.model.user.UserDAOImpl;
import com.apo.model.user.UserRepository;
import com.apo.model.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private MongoService mongoService;

    @Bean
    public Desk getDesk() {
        return mongoService.findDesk();
    }

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
