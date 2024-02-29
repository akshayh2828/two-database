package com.db.twodb.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "BookentityManagerFactory", transactionManagerRef = "BooktransactionManager",
basePackages = {"com.db.twodb.db2.repository"})
public class Db2connfig {
//	@Primary
	@Bean(name = "BookdataSource")
	@ConfigurationProperties(prefix = "spring.database2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
//	@Primary
	@Bean(name = "BookentityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder bulider,
			@Qualifier("BookdataSource") DataSource BookdataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");
    
    return bulider.dataSource(BookdataSource).properties(properties)
    		.packages("com.db.twodb.db2.entity").persistenceUnit("Book").build();
}
//	@Primary
	@Bean(name="BooktransactionManager")
	public PlatformTransactionManager transactionManager
	(@Qualifier("BookentityManagerFactory") EntityManagerFactory BookentityManagerFactory) {
		return new JpaTransactionManager(BookentityManagerFactory);
	}
}
