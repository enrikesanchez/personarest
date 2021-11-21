package com.ejemplo.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class SpringBeanTestConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBeanTestConfiguration.class);

    @Bean
    @Profile("test")
    public DataSourceInitializer dataSourceInitializerEnterpriseReference(DataSource datasource) {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        /* This code was included to avoid the invocation of the schema creation in Oracle production database */
        String baseFolder = "./database";
        LOGGER.info("Loading H2 Schema");
        resourceDatabasePopulator.addScript(new ClassPathResource(baseFolder + "/01-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource(baseFolder + "/02-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}