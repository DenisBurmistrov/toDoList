package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.List;
import java.util.Map;

public interface IProjectRepository {

    Project persist(User currentUser, String name, String description);

    void remove(User currentUser, String projectId);

    void removeAll(User currentUser);

    List<Project> findAll(User currentUser);

    void merge(User currentUser, String id, String name, String description);

    void assignExpert(User currentUser, String projectId, String userId);

    Map<String, Project> getProjects();
}
