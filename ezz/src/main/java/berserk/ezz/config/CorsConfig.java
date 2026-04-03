package berserk.ezz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // تطبيق على جميع مسارات المشروع
                .allowedOrigins("*") // السماح من أي واجهة
                .allowedMethods("*") // السماح بجميع الطرق: GET, POST, PUT, DELETE, OPTIONS
                .allowedHeaders("*"); // السطر الأهم: السماح بجميع الـ Headers مثل Content-Type
    }
}