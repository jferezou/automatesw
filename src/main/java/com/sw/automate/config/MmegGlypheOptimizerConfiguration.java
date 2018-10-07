package com.sw.automate.config;


import com.sw.automate.annotation.ServiceMethod;
import com.sw.automate.config.aspects.ApplicationModule;
import com.sw.automate.config.aspects.ServiceMethodAspect;
import com.sw.automate.exception.ApplicationExceptionFactory;
import com.sw.automate.exception.SearchCausedByExceptionHandler;
import com.sw.automate.exception.ServiceExceptionHandler;
import com.sw.automate.exception.mapper.TimeoutExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Configuration spécifique au projet métier<br/>
 * <br/>
 *
 * <img src="../../../../../../../../../../parent/resources/doc/SIS Configuration.png" width="600px"/>
 */

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MmegGlypheOptimizerConfiguration {

    @Bean
    public ApplicationExceptionFactory getApplicationExceptionFactory() {
        return new ApplicationExceptionFactory();
    }

    @Bean
    public ServiceExceptionHandler getServiceExceptionHandler(ApplicationExceptionFactory applicationExceptionFactory) {
        SearchCausedByExceptionHandler checkAllStackExceptionHandler = new SearchCausedByExceptionHandler(applicationExceptionFactory);
        checkAllStackExceptionHandler.addExceptionMapper(new TimeoutExceptionMapper(applicationExceptionFactory));
        return checkAllStackExceptionHandler;
    }
    /**
     * Créer une instance de ApplicationModule
     *
     * @return une instance de ApplicationModule
     */
    @Bean
    public ApplicationModule getApplicationModule(ServiceExceptionHandler serviceExceptionHandler) {
        return new ApplicationModule("MMEG_Glyphes_Optimizer", serviceExceptionHandler);
    }


    @Bean
    public Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
    /**
     * Aspect pour la gestion des point d'entrée dans le système.
     *
     * @return aspect.
     * @see ServiceMethod
     */
    @Bean
    public ServiceMethodAspect getServiceMethodAspect() {
        return new ServiceMethodAspect();
    }
}
