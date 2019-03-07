package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.List;
import java.util.Map;

public interface IProjectRepository {

    AbstractEntity persist(AbstractEntity abstractEntity);

    void merge(AbstractEntity abstractEntity);

    void remove(String userId, String projectId);

    void removeAll(String userId);

    List<Project> findAll(String userId);

    Map<String, AbstractEntity> getAbstractEntities();
}
