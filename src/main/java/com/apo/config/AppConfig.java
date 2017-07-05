package com.apo.config;

import com.apo.model.desk.dao.DeskDAO;
import com.apo.model.desk.dao.DeskDAOMongo;
import com.apo.model.desk.DeskHolder;
import com.apo.model.user.dao.UserDAO;
import com.apo.model.user.dao.UserDAOMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 05/06/2017.
 */
@Configuration
@EnableWebMvc
@EnableAsync
public class AppConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/res/");
    }

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOMongo();
    }

    @Bean
    public DeskDAO getDeskDAO() {
        return new DeskDAOMongo();
    }

    @Bean
    @Scope(value = "singleton")
    public DeskHolder getDeskHolder() {
        return new DeskHolder();
    }

    @Bean
    public Executor asyncExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        return executor;
    }
}
