package com.company.demo.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {
    @Bean
    public MapperFacade mapperFacade(){
        DefaultMapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(true).build();
        return factory.getMapperFacade();
    }
}
