package ru.burmistrov.tm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.configuration.SpringConfiguration;

public class AppClient {

    public static void main(final String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ServiceLocator serviceLocator = (ServiceLocator) context.getBean("bootstrap");
        serviceLocator.init();
    }

}
