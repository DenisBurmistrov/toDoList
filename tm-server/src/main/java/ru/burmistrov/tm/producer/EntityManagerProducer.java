package ru.burmistrov.tm.producer;

import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @NotNull
    @Produces
    @TransactionScoped
    public EntityManager create() {
        return this.entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) entityManager.close();
    }
}

