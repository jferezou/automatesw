package com.sw.automate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAsync
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

    @Bean(name = "asyncExecutor")
    public AsyncTaskExecutor getAsyncTaskExecutor(@Value("${async.poolSize:6}") final int poolSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(poolSize);
        executor.setCorePoolSize(poolSize);
        return executor;
    }

}
