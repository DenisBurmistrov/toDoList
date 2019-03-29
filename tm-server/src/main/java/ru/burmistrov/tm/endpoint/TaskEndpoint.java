package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.ITaskEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.dto.TaskDto;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@WebService
public class TaskEndpoint implements ITaskEndpoint {

    private ServiceLocator serviceLocator;

    public TaskEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void updateTaskById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "taskId") @NotNull final String taskId,
             @WebParam(name = "newName") @NotNull final String newName, @WebParam(name = "description") @NotNull final String description,
             @WebParam(name = "dateEnd") @NotNull final String dateEnd, @WebParam(name = "status") @NotNull final String status) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().merge(userId, projectId, taskId, newName, description, dateEnd, status);
        }
    }

    @WebMethod
    @Nullable
    public TaskDto createTask
            (@WebParam(name = "session") @NotNull final Session session, @NotNull @WebParam(name = "userId") final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "name") @NotNull final String name,
             @WebParam(name = "description") @NotNull final String description, @WebParam(name = "dateEnd") @NotNull final String dateEnd,
             @WebParam(name = "status") @NotNull final String status) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            Task task = serviceLocator.getTaskService().persist(userId, projectId, name, description, dateEnd, status);
            if (task != null) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                return taskDto;
            }
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<TaskDto> findAllTasks
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            List<Task> tasks = serviceLocator.getTaskService().findAll(userId);
            List<TaskDto> dtos = new LinkedList<>();

            for (Task task : Objects.requireNonNull(tasks)) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                dtos.add(taskDto);
            }
            return dtos;
        }
        return null;
    }

    @WebMethod
    public void removeAllTasksInProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAllInProject(userId, projectId);
        }
    }

    @WebMethod
    public void removeTaskById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "taskId") @NotNull final String taskId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().remove(userId, taskId);
        }
    }

    @WebMethod
    public void removeAllTasks
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAll(userId);
        }
    }

    @WebMethod
    @Nullable
    public List<TaskDto> findAllTasksSortByDateBegin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            List<Task> tasks = serviceLocator.getTaskService().findAllSortByDateBegin(userId);
            List<TaskDto> dtos = new LinkedList<>();

            for (Task task : Objects.requireNonNull(tasks)) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                dtos.add(taskDto);
            }
            return dtos;
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<TaskDto> findAllTasksSortByDateEnd
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            List<Task> tasks = serviceLocator.getTaskService().findAllSortByDateEnd(userId);
            List<TaskDto> dtos = new LinkedList<>();

            for (Task task : Objects.requireNonNull(tasks)) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                dtos.add(taskDto);
            }
            return dtos;
        }
        return null;
    }

    @Nullable
    public List<TaskDto> findAllTasksSortByStatus
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            List<Task> tasks = serviceLocator.getTaskService().findAllSortByStatus(userId);
            List<TaskDto> dtos = new LinkedList<>();

            for (Task task : Objects.requireNonNull(tasks)) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                dtos.add(taskDto);
            }
            return dtos;
        }
        return null;
    }

    @WebMethod
    @Nullable
    public TaskDto findTaskByName
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            Task task = serviceLocator.getTaskService().findOneByName(userId, name);
            if(task != null) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                return taskDto;
            }
        }
        return null;
    }

    @WebMethod
    @Nullable
    public TaskDto findTaskByDescription
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "description") @NotNull final String description) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            Task task = serviceLocator.getTaskService().findOneByDescription(userId, description);
            if(task != null) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                return taskDto;
            }
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<TaskDto> findAllTasksInProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            List<Task> tasks = serviceLocator.getTaskService().findAllInProject(userId, projectId);
            List<TaskDto> dtos = new LinkedList<>();

            for (Task task : Objects.requireNonNull(tasks)) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(Objects.requireNonNull(task).getId());
                taskDto.setName(Objects.requireNonNull(task).getName());
                taskDto.setDescription(Objects.requireNonNull(task).getDescription());
                taskDto.setDateBegin(Objects.requireNonNull(task).getDateBegin());
                taskDto.setDateEnd(Objects.requireNonNull(task).getDateEnd());
                taskDto.setStatus(Objects.requireNonNull(task).getStatus());
                taskDto.setProjectId(Objects.requireNonNull(task).getProjectId());
                dtos.add(taskDto);
            }
            return dtos;
        }
        return null;
    }
}
