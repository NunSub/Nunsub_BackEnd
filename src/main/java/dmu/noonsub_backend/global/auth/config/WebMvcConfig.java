package dmu.noonsub_backend.global.auth.config;

import dmu.noonsub_backend.global.auth.interceptor.SecondaryAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final SecondaryAuthInterceptor secondaryAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(secondaryAuthInterceptor)
                .addPathPatterns("/api/**");
    }
}
