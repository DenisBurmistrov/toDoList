package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.util.List;
import java.util.Map;

public interface ITaskRepository {

    AbstractEntity persist(AbstractEntity abstractEntity);

    void merge(AbstractEntity abstractEntity);

    void remove(String userId, String projectId, String taskId);

    void removeAll(String userId);

    List<Task> findAll(String userId, String projectId);

    void removeAllInProject(String userId, String projectId);

    Task findOne(String userId, String projectId, String name);

    Map<String, AbstractEntity> getAbstractEntities();
}
