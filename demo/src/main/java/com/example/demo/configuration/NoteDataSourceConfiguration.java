package com.example.demo.configuration;

import com.example.demo.note.entity.Note;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.note.repository",
        entityManagerFactoryRef = "noteEntityManagerFactory",
        transactionManagerRef = "noteTransactionManager")
public class NoteDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.note")
    public DataSourceProperties noteDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.note.configuration")
    public DataSource noteDataSource() {
        return noteDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "noteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean noteEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "com.example.demo.dialect.EmptyDialect");
        return builder
                .dataSource(noteDataSource())
                .properties(properties)
                .packages(Note.class)
                .build();
    }

    @Primary
    @Bean(name = "noteTransactionManager")
    public PlatformTransactionManager collegeTransactionManager(
            final @Qualifier("noteEntityManagerFactory") LocalContainerEntityManagerFactoryBean noteEntityManagerFactory) {
        return new JpaTransactionManager(noteEntityManagerFactory.getObject());
    }
}
