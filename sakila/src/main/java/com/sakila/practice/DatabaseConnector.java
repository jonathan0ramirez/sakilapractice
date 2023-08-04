package com.sakila.practice;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatabaseConnector {

  @Bean
  public DataSource getDataSource(){
    return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/sakila")
        .username("sakila")
        .password("sakila")
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .build();
  }
  
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DatabaseConnector.class);
    
    DataSource dataSource = context.getBean(DataSource.class);
    
    try {
      dataSource.getConnection();
      System.out.println("Database Connection Successful");
    } catch (Exception e) {
      System.err.println("Error connecting to the database: " + e.getMessage());
    } finally {
      context.close();
    }

  }

}
