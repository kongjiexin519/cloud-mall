package com.cloud.mall.categoryproduct.config;

import com.cloud.mall.categoryproduct.common.ProductConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//对地址进行映射
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //对于带有images的url，不会把它当作接口，而是把它当成一个文件
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + ProductConstant.FILE_UPLOAD_DIR);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
