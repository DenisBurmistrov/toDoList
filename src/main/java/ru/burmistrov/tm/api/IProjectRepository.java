package ru.burmistrov.tm.api;

import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public interface IProjectRepository {

    String merge(String name, String description);

    String remove(Long projectId);

    //String printProjects();

    String removeAll();

   /* boolean checkContainsProject(Long projectId);

    boolean checkHavingTasks(Long projectId);*/

    String findAll(Long projectId);


    String updateProject(Long id, String name, String description);


}
