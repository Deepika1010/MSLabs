package com.spring.microservices.ProductMS;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
//@Component
public class DataSourceConfig {

	/*
	 * @Value("${mysql.secure.mysqlurl}") private String mysqlurl;
	 * 
	 * @Value("${mysql.secure.mysqluser}") private String mysqluser;
	 * 
	 * @Value("${mysql.secure.mysqlpassword}") private String mysqlpassword;
	 * 
	 * @Bean public DataSource getDataSource() { DataSourceBuilder dataSourceBuilder
	 * = DataSourceBuilder.create();
	 * dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
	 * dataSourceBuilder.url(mysqlurl); dataSourceBuilder.username(mysqluser);
	 * dataSourceBuilder.password(mysqlpassword); return dataSourceBuilder.build();
	 * }
	 */
}
