package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.IProjectRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectRepository implements IProjectRepository {

    private static long counter = 1;
    private Map<Long, Project> projects = Bootstrap.projects;
    private Map<Long, Task> tasks = Bootstrap.tasks;


    @Override
    public String merge(String name, String description) {
        Project project = new Project();
        project.setId(incrementCounter());
        project.setName(name);
        project.setDescription(description);

        if (projects.containsValue(project)) {
           return "Проект с таким именем уже существует";
        } else {
            projects.put(project.getId(), project);
            return "Добавление произведено";
        }
    }

    @Override
    public String remove(Long projectId){

            projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId));
            return "";
        }



    /*@Override
    public String printProjects() {
        System.out.println("Список проектов:");
        projects.forEach((k, v) -> System.out.println(v));
        return "";
    }*/


    @Override
    public String removeAll() {
        tasks.clear();
        projects.clear();
        return "Все проекты удалены";
    }

    /*@Override
    public boolean checkContainsProject(Long projectId) {
        return projects.containsKey(projectId);
    }

    @Override
    public boolean checkHavingTasks(Long projectId) {

        return projects.get(projectId).getTasks().size() > 0;
    }*/

    @Override
    public String findAll(Long projectId) {
        tasks.forEach((k, v) -> {
            if (v != null && projectId.equals(v.getProjectId())){
                System.out.println();
            }
        });
        return "";
    }


    @Override
    public String updateProject(Long id, String name, String description) {
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getValue() == id){
                Project project = new Project();
                project.setId(id);
                project.setName(name);
                project.setDescription(description);
                projects.put(id, project);
                return "Обновление проекта произведено";
            }
        }
        return "Нет проекта с введённый ID";
    }

    public Long incrementCounter(){
        return counter++;
    }

}