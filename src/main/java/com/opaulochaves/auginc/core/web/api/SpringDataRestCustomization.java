package com.opaulochaves.auginc.core.web.api;

import com.opaulochaves.auginc.core.AugincProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties({AugincProperties.class})
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

    private AugincProperties properties;

    public SpringDataRestCustomization(AugincProperties properties) {
        this.properties = properties;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        AugincProperties.Cors cors = this.properties.getCors();

        config.getCorsRegistry().addMapping(cors.getMapping())
                .allowedOrigins(cors.getAllowedOrigins())
                .allowedMethods(cors.getAllowedMethods())
                .allowedHeaders(cors.getAllowedHeaders())
                .exposedHeaders(cors.getExposedHeaders())
                .allowCredentials(cors.isAllowCredentials()).maxAge(cors.getMaxAge());
    }

}
