package ru.burmistrov.repository;

import ru.burmistrov.Bootstrap;
import ru.burmistrov.api.repository.IProjectRepository;
import ru.burmistrov.entity.Project;

import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private static long counter = 1;
    private Map<Long, Project> projects = Bootstrap.projects;


    @Override
    public void addProject(String name, String description) {
        Project project = new Project();
        project.setId(incrementCounter());
        project.setName(name);
        project.setDescription(description);

        if (projects.containsValue(project)) {
            System.out.println("Такой проект уже существует");
        } else {
            projects.put(incrementCounter(), project);
            printProjects();
        }

    }

    @Override
    public void deleteProjectById(Long projectId){

            projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId));
            printProjects();
        }



    @Override
    public void printProjects() {
        System.out.println("Список проектов:");
        projects.forEach((k, v) -> System.out.println(v));
    }


    @Override
    public void clearAll() {
        projects.clear();
    }


   /* @Override
    public void updateProjectNameById(Long id, String name) {
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getKey() == id){
                Project project = new Project((Project) pair.getValue());
                project.setName(name);
                projects.put(id, project);
                printProjects();
            }
        }
    }*/

    public Long incrementCounter(){
        return counter++;
    }

}