package ru.burmistrov.tm.api;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public interface IProjectRepository {

    String persist(String name, String description);

    void remove(String projectId);

    //String printProjects();

    void removeAll();

   /* boolean checkContainsProject(Long projectId);

    boolean checkHavingTasks(Long projectId);*/

    Map<String, Project> findAll();


    String merge(String id, String name, String description);


}
