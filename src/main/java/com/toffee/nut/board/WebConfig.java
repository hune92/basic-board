package com.toffee.nut.board;

import com.toffee.nut.board.interceptor.BasicLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BasicLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**/*")
                .excludePathPatterns("/error/**");
    }
}
