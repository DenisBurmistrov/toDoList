package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Project;

import java.util.List;
import java.util.Map;

public interface IProjectRepository {

    Project persist(String userId, String name, String description);

    void merge(String userId, String id, String name, String description);

    void remove(String userId, String projectId);

    void removeAll(String userId);

    List<Project> findAll(String userId);

    void assignExpert(String currentUserId, String projectId, String userId);

    Map<String, Project> getProjects();
}
