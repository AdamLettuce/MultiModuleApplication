package pl.adamsalata.application;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.adamsalata")
@EnableAutoConfiguration
@ComponentScan(basePackages = "pl.adamsalata")
@PropertySource({"classpath:jpa-postgresql.properties", "classpath:hibernate.properties",
        "classpath:application.properties"})
@EnableWebMvc
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Value("${javax.persistence.jdbc.driver}")
    private String dataSourceDriverClassName;

    @Value("${javax.persistence.jdbc.url}")
    private String databaseURL;

    @Value("${javax.persistence.jdbc.user}")
    private String databaseUsername;

    @Value("${javax.persistence.jdbc.password}")
    private String databasePassword;

    @Value("${hibernateDialectValue}")
    private String databaseDialect;

    @Value("${hibernateShowSqlValue}")
    private boolean showSQL;

    @Value("${hibernateHbm2DdlAutoValue}")
    private String hbm2Ddl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @PersistenceContext
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibernateJPAProperties());
        entityManagerFactoryBean.setPackagesToScan("pl.adamsalata");
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriverClassName);
        dataSource.setUrl(databaseURL);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    public Properties hibernateJPAProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", databaseDialect);
        properties.put("hibernate.show_sql", showSQL);
        properties.put("hibernate.hbm2ddl.auto", hbm2Ddl);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
