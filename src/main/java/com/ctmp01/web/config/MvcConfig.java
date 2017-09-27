package com.ctmp01.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yipingdong on 2017/4/19.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/swagger-ui.html");
    }

    /*@Bean
    public RestErrorFilter restErrorFilter(){
        return new RestErrorFilter();
    }*/

   /* @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }
*/
    /*@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
    */
    /*@Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver(){
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                Locale locale = super.determineDefaultLocale(request);
                if (null == locale) {
                    String acceptLanguage = request.getHeader("Accept-Language");
                    if (acceptLanguage != null && !acceptLanguage.trim().isEmpty()) {
                        locale = request.getLocale();
                    }
                }
                return locale;
            }
        };
    }*/


}
