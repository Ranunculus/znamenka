package ru.znamenka.config;

import com.google.api.services.calendar.Calendar;
import lombok.val;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.znamenka.security.GoogleAuthorizer;
import ru.znamenka.util.locale.ExtResourceBundleSource;

import java.io.IOException;

/**
 * <p>Конфигуратор бинов
 * <p>
 * Создан 04.08.2016
 * <p>
 * Изменения:
 * <p>
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class BeanConfig {

    /**
     * Proxy для перехвата исключений
     * @return возвращает proxy
     */
    @Bean(name = "dataService")
    public ProxyFactoryBean facadeRepoProxy() {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setInterceptorNames("repositoryThrowsAdvice");
        proxy.setTargetName("convertService");

        return proxy;
    }

    /**
     * Создает бин для доступа к пулу сообщений.
     * Используется для локализации
     * @return бин для доступа к локализованным сообщениям
     */
    @Bean
    public MessageSource messageSource() {
        val source = new ExtResourceBundleSource();
        source.setBasename("static\\locale\\messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }


    @Bean
    public Calendar calendar(@Autowired GoogleAuthorizer authorizer) throws IOException {
        return authorizer.getCalendar();
    }

    @Bean
    public FileChangedReloadingStrategy fileChangedStrategy() {
        return new FileChangedReloadingStrategy();
    }

}
