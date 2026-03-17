package io.github.juli0mendes.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource hikariDataSource() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(this.password);
        hikariConfig.setDriverClassName(this.driverClassName);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setPoolName("library-db-pool");
        hikariConfig.setMaxLifetime(600000);
        hikariConfig.setConnectionTimeout(100000);
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
    }
}
