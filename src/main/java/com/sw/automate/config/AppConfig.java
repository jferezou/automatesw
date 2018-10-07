package com.sw.automate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({
        MmegGlypheOptimizerConfiguration.class,
        ServicesExposesConfiguration.class,
        WebMainConfiguration.class,
        SpringSecurityConfiguration.class})
public class AppConfig {



    @Bean(name = "webProperties")
    @ConfigurationProperties(prefix = "webapp")
    public WebCfgProperties webProperties() {
        return new WebCfgProperties();
    }
}
