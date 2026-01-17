package com.vaultcore.vaultcore.config;


import com.vaultcore.vaultcore.fraud.FraudDetectionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final FraudDetectionInterceptor fraudDetectionInterceptor;

    public WebConfig(FraudDetectionInterceptor fraudDetectionInterceptor) {
        this.fraudDetectionInterceptor = fraudDetectionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fraudDetectionInterceptor)
                .addPathPatterns("/api/**");
    }
}