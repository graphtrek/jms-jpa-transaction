package co.grtk.config;

import java.sql.Driver;

import javax.sql.XADataSource;

import me.snowdrop.boot.narayana.core.jdbc.lrco.LrcoXADataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.XADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(XADataSourceAutoConfiguration.class)
@AutoConfigureAfter(NarayanaConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
@ConditionalOnProperty(name = {"narayana.dbcp.enabled", "narayana.lrco.enabled"})
@ConditionalOnBean(XADataSourceWrapper.class)
@ConditionalOnMissingBean(XADataSource.class)
public class NarayanaLrcoXADataSourceAutoConfiguration {

    @Bean
    public XADataSource xaDataSource(DataSourceProperties properties) {
        return createXaDataSource(properties);
    }

    private LrcoXADataSource createXaDataSource(DataSourceProperties properties) {
        Driver driver = createDriver(properties.determineDriverClassName(), properties.getClassLoader());
        String url = properties.determineUrl();
        String username = properties.determineUsername();
        String password = properties.determinePassword();
        return new LrcoXADataSource(driver, url, username, password);
    }

    private Driver createDriver(String className, ClassLoader classLoader) {
        Class<?> clazz = ClassUtils.resolveClassName(className, classLoader);
        Object instance = BeanUtils.instantiateClass(clazz);
        return (Driver) instance;
    }
}
