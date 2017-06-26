package com.opaulochaves.auginc.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author paulo
 */
@ConfigurationProperties(prefix = "auginc")
public class AugincProperties {

    private Cors cors = new Cors();
    
    private String roleHierarchy;

    public Cors getCors() {
        return cors;
    }

    public void setCors(Cors cors) {
        this.cors = cors;
    }

    public String getRoleHierarchy() {
        return roleHierarchy;
    }

    public void setRoleHierarchy(String roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }
    
    public static class Cors {

        private String mapping = "/**";
        
        private String[] allowedOrigins = new String[0];

        private String[] allowedMethods = new String[0];

        private String[] allowedHeaders = new String[0];

        private String[] exposedHeaders = new String[0];

        private boolean allowCredentials = false;

        private int maxAge = 3600;

        public String getMapping() {
            return mapping;
        }

        public void setMapping(String mapping) {
            this.mapping = mapping;
        }
        
        public String[] getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String[] allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public String[] getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedMethods(String[] allowedMethods) {
            this.allowedMethods = allowedMethods;
        }

        public String[] getAllowedHeaders() {
            return allowedHeaders;
        }

        public void setAllowedHeaders(String[] allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        public String[] getExposedHeaders() {
            return exposedHeaders;
        }

        public void setExposedHeaders(String[] exposedHeaders) {
            this.exposedHeaders = exposedHeaders;
        }

        public boolean isAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
    }

}
