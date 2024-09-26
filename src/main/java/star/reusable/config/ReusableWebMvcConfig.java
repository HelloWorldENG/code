package star.reusable.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import star.reusable.interceptor.ReusableInterceptor;

/**
 * @author Luke Xie
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Configuration
public class ReusableWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ReusableInterceptor reusableInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reusableInterceptor)
                .excludePathPatterns( "/web/**" )
                .excludePathPatterns( "/remote/**" )
                .addPathPatterns( "/**" );
    }
}
