package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IProjectService {

     void remove(String userId, String projectId);

    Project persist(String userId, String name, String merge);

    void merge(String userId, String projectId, String name, String description);

    void removeAll(String userId);

    List<Project> findAll(String userId);

    void assignUser(String CurrentUserId, String projectId, String userId);

}
