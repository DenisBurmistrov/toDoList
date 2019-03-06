package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IProjectService {

     void remove(User currentUser, String projectId);

    Project persist(User currentUser, String name, String merge);

    void merge(User currentUser, String projectId, String name, String description);

    void removeAll(User currentUser);

    List<Project> findAll(User currentUser);

    void assignUser(User currentUser, String projectId, String userId);

}
