package ru.burmistrov.tm.producer;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.service.PropertyService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerFactoryProducer {

    @Inject
    private PropertyService propertyService;

    @NotNull
    public EntityManagerFactory getEntityManagerFactory() throws IOException {

        @NotNull final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
        settings.put(Environment.URL, propertyService.getJdbcUrl());
        settings.put(Environment.USER, propertyService.getJdbcUsername());
        settings.put(Environment.PASS, propertyService.getJdbcPassword());
        settings.put(Environment.DIALECT,
                "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        @NotNull final StandardServiceRegistryBuilder registryBuilder
                = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        @NotNull final StandardServiceRegistry registry = registryBuilder.build();
        @NotNull final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        @NotNull final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    @Produces
    public EntityManager getEntityManager() throws IOException {
        return getEntityManagerFactory().createEntityManager();
    }

}
