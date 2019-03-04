package ru.burmistrov.tm.api;

import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public interface IProjectRepository {

    String addProject(String name, String description);

    String deleteProjectById(Long projectId);

    //String printProjects();

    String clearAll();

    boolean checkContainsProject(Long projectId);

    boolean checkHavingTasks(Long projectId);

    Map<Long, Task> getProjectTasks(Long projectId);


   // void updateProjectNameById(Long id, String name);


}
