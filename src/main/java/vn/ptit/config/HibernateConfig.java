package vn.ptit.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//đánh dấu để spring-boot biết file này dùng để cấu hình.
@Configuration

//bật tính năng quản lí transaction của spring container.
//transaction có 2 loại là resource-local và jta.
//đối với resourece-local thì developer phải manual transaction.
@EnableTransactionManagement

//hướng dẫn springboot biết nơi chứa các Repository
@EnableJpaRepositories(basePackages = { "vn.ptit" })

//trỏ tới cấu hình connection tới mysql. và được sử dụng bởi Environment.
@PropertySource({ "classpath:datasource.properties" })
public class HibernateConfig {
	// các thông tin từ application.properties và
	// @PropertySource được đọc và store trong này.
	@Autowired
	private Environment env;
	
	/**
	 * nằm giữa database(mysql) và project.
	 * bao gồm connection-pool.
	 * Hikari là một connection pool.
	 * @return
	 */
	@Bean(destroyMethod = "close") protected DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
        return new HikariDataSource(dataSourceConfig);
    }
	
	/**
	 * tạo EntityManagerFactory. Dùng để tạo EntiyManager.
	 * @return
	 */
	@Bean protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Properties jpaProperties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        //Specifies the action that is invoked to the database when the Hibernate SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto") );
        //Configures the naming strategy that is used when Hibernate creates new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy") );
        //If the value of this property is true, Hibernate writes all SQL statements to the console.
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql") );
        //If the value of this property is true, Hibernate will format the SQL that is written to the console.
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql") );
        jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", env.getRequiredProperty("hibernate.jdbc.lob.non_contextual_creation") );
        
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // package chứa các entities.
        entityManagerFactoryBean.setPackagesToScan("vn.ptit");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }
	
	/**
	 * vì bật tính năng @EnableTransactionManagement nên cần tạo TransactionManager để spring contatiner sử dụng.
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean protected JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
