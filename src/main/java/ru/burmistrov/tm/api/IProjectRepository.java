package ru.burmistrov.tm.api;

import ru.burmistrov.tm.entity.Project;

import java.util.Map;

public interface IProjectRepository {

    String persist(String name, String description);

    void remove(String projectId);

    void removeAll();

    Map<String, Project> findAll();

    String merge(String id, String name, String description);

    void assignExpert(String projectId, String userId);
}
