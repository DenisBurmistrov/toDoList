package ru.burmistrov.api.repository;

public interface IProjectRepository {

    String addProject(String name, String description);

    String deleteProjectById(Long projectId);

    //String printProjects();

    String clearAll();

   // void updateProjectNameById(Long id, String name);


}
