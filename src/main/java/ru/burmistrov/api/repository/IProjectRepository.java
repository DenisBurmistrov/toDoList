package ru.burmistrov.api.repository;

public interface IProjectRepository {

    void addProject(String name, String description);

    void deleteProjectById(Long projectId);

    void printProjects();

    void clearAll();

   // void updateProjectNameById(Long id, String name);


}
