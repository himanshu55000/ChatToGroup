package com.ChatToGroupBackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ChatToGroupBackend.model.*;

@Configuration
@ComponentScan("com.ElectroProShopBacked")
@EnableTransactionManagement
public class DBConfig {
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		datasource.setUsername("ChatToGroup");
		datasource.setPassword("root");
		return datasource;
	}
	
	@Bean(name="sessionfact")
	public SessionFactory getSessionFactory(){
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto","update");
		prop.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		prop.put("hibernate.show_sql","true");
		LocalSessionFactoryBuilder sessionFactBuilder = new LocalSessionFactoryBuilder(getDataSource());
		sessionFactBuilder.addProperties(prop);
		sessionFactBuilder.addAnnotatedClass(Blog.class);
		sessionFactBuilder.addAnnotatedClass(Forum.class);
		sessionFactBuilder.addAnnotatedClass(Job.class);
		sessionFactBuilder.addAnnotatedClass(UserDetails.class);
		sessionFactBuilder.addAnnotatedClass(ApplyForJob.class);
		sessionFactBuilder.addAnnotatedClass(Friends.class);
		sessionFactBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactBuilder.addAnnotatedClass(ForumComment.class);
		sessionFactBuilder.addAnnotatedClass(ProfilePicture.class);
		return sessionFactBuilder.buildSessionFactory();
	}

	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(){
		HibernateTransactionManager txm = new HibernateTransactionManager(getSessionFactory());
		return txm;	
	}
}
