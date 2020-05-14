package springapp.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

import springapp.business.SpringBusinessConfig;
import springapp.dao.SpringDAOConfiguration;

@Configuration
@EnableWebMvc
@Import({SpringBusinessConfig.class,SpringDAOConfiguration.class})
@ComponentScan(basePackageClasses = SpringStart.class)
@ComponentScan(basePackageClasses = SpringDAOConfiguration.class)
public class SpringStart implements WebApplicationInitializer, WebMvcConfigurer {

    @Override
    public void onStartup(ServletContext ctx) throws ServletException {
        System.out.println("Starting....");
        // Init application context
        AnnotationConfigWebApplicationContext webCtx
            = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringStart.class);
        webCtx.setServletContext(ctx);
        // Init dispatcher servlet
        ServletRegistration.Dynamic servlet
            = ctx.addServlet("springapp", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        //servlet.addMapping("*.htm");
        servlet.addMapping("/actions/*");
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

}