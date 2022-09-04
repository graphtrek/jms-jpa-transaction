package co.grtk.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("primaryDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.primary.hikari")
    public HikariDataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
