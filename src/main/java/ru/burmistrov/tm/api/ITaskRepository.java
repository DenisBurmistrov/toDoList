package ru.burmistrov.tm.api;


public interface ITaskRepository {

    String persist(Long projectId, String oldName, String newName, String description, Integer priority);

    String merge(Long projectId, String name, String description, Integer priority);

    String remove(Long projectId, String name);

    String findAll(Long projectId);

    String removeAll(Long projectId);

    String findOne(Long projectId, String name);
}
