package com.biztone.converter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // frontend 디렉토리를 루트 경로에서 서빙
        registry.addResourceHandler("/**")
                .addResourceLocations("file:frontend/");
        
        // CSS, JS 명시적 핸들링 (필요 시)
        registry.addResourceHandler("/css/**")
                .addResourceLocations("file:frontend/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("file:frontend/js/");
    }
}
