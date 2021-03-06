package com.sw.automate.config;

import com.sw.automate.config.restConfig.CommonRest;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Import({
        CXFConfiguration.class,
        RESTPublisherConfiguration.class
})
public class ServicesExposesConfiguration {
    /**
     * Déploiement des services REST
     *
     * @param publisher
     *            un service permettant de publier des services rest
     * @return bean Serveur REST listant les services déployés
     */
    @Bean(name = "jaxrs")
    public JAXRSServerFactoryBean getRESTPublishedServices(final RESTPublisher publisher) {
        return publisher.publish(RESTPublisher.DEFAULT_NS, ServicesExposesConfiguration::initializeSwagger, CommonRest.class);
    }


    private static void initializeSwagger(final JAXRSServerFactoryBean factoryBean) {
        List<Feature> features = new ArrayList<>();
        Swagger2Feature swagger = new Swagger2Feature();
        swagger.setPrettyPrint(true); // pour formatter la sortie JSON/YAML pour que ça soit lisible sans passer par un outil annexe.
        swagger.setRunAsFilter(true); // de toute façon ça ne marche pas sans ça...
        swagger.setScan(false); // pour se baser uniquement sur les services déployés dans ce contexte.
        swagger.setTitle("Sw automate");
        swagger.setDescription("Sw automate");
        swagger.setVersion("1.0.0");
        swagger.setContact("J.Férézou");
        swagger.setUsePathBasedConfig(true);
        features.add(swagger);
        factoryBean.setFeatures(features);
    }
}