package com.mahmud.configure_datasource_programatically.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    /*
     References :
        1. https://docs.spring.io/spring-boot/api/java/org/springframework/boot/autoconfigure/orm/jpa/HibernateProperties.html
        2. https://www.baeldung.com/hibernate-spring
        3. https://stackoverflow.com/questions/28821521/configure-datasource-programmatically-in-spring-boot
        4. https://docs.spring.io/spring-boot/how-to/data-access.html#howto.data-access.configure-custom-datasource
     */
    @Bean
    @Primary
    /*
        * I have tested without @Primary, it works without @Primary
     */
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/dummy")
                .username("postgres")
                .password("mysecretpassword")
                .build();
    }

    @Bean
    public JpaProperties jpaProperties() {
        JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setShowSql(true);
        jpaProperties.setGenerateDdl(true);
        return jpaProperties;
    }

    @Bean
    public HibernateProperties hibernateProperties() {
        HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto("create-drop");
        return hibernateProperties;
    }

    /*
        * We can also use this method instead of hibernateProperties() method
        @Bean
        public Properties hibernateProperties() {
            Properties hibernateProperties = new Properties();
            hibernateProperties.setProperty("ddl-auto", "create-drop");
            //hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            return hibernateProperties;
    }

     */

}
