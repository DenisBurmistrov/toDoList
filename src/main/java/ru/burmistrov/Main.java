package ru.burmistrov;

import ru.burmistrov.repository.MapOfProjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final String COMMAND_HELP = "-help";
    private static final String COMMAND_DELETE = "-delete";
    private static final String COMMAND_ADD = "-add";
    private static final String COMMAND_EXIT = "-exit";
    private static final String COMMAND_PRINT = "-print";
    private static final String COMMAND_DELETE_ALL = "-deleteAll";
    private static final String COMMAND_UPDATE_NAME_BY_ID = "-updateNameById";
    private static final String COMMAND_ADD_TASK_TO_PROJECT = "-addTaskToProject";
    private static final String COMMAND_PRINT_TASK_OF_PROJECT = "-printTasksOfProject";
    private static final String COMMAND_DELETE_TASK_FROM_PROJECT = "-deleteTaskFromProject";
    private static final String COMMAND_UPDATE_TASK_FROM_PROJECT = "-updateTaskFromProject";


    public static void main(String[] args) throws IOException {

        MapOfProjects mapOfProjects = MapOfProjects.getInstance();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Команды: \n" +
                "|| Вывести список проектов " + COMMAND_HELP + " " +
                "|| Завершить программу " + COMMAND_EXIT + " ||");

        while (true) {
            String firstInput = bufferedReader.readLine();

            if (COMMAND_HELP.equals(firstInput)) {
                mapOfProjects.addDefaultProjects();
                mapOfProjects.printProjects();

                System.out.println("Команды: \n" +
                        "|| Удалить проект " + COMMAND_DELETE + " idProject " +
                        "|| Добавить проект " + COMMAND_ADD + " " +
                        "|| Вывести список проектов " + COMMAND_PRINT + " " +
                        "|| Завершить программу " + COMMAND_EXIT + " " +
                        "|| Удалить все проекты " + COMMAND_DELETE_ALL + " " +
                        "|| Изменить имя по id проекта " + COMMAND_UPDATE_NAME_BY_ID + " projectId newName " +
                        "\n|| Добавить задачу к проекту " + COMMAND_ADD_TASK_TO_PROJECT + " projectId " +
                        "|| Вывести все задичи проекта " + COMMAND_PRINT_TASK_OF_PROJECT + " projectId " +
                        "|| Удалить задачу из проекта " + COMMAND_DELETE_TASK_FROM_PROJECT + " projectId " +
                        "|| Обновить задачу в проекте " + COMMAND_UPDATE_TASK_FROM_PROJECT + " projectId ||");


                while (true) {

                    String secondInput = bufferedReader.readLine();
                    String[] secondArrayOfInput = splitString(secondInput);

                    if (COMMAND_DELETE.equals(secondArrayOfInput[0])) {
                        if(secondArrayOfInput.length == 2)
                        try {
                            mapOfProjects.deleteProjectById(Long.valueOf(secondArrayOfInput[1]));
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Введите корректный id");
                        }
                        else {
                            System.out.println("Такой комманды не существует");
                        }

                    }
                    else if (COMMAND_ADD.equals(secondInput)) {
                        mapOfProjects.addProject();
                    }
                    else if (COMMAND_EXIT.equals(secondInput)) {
                        exit();
                    }
                    else if (COMMAND_PRINT.equals(secondInput)) {
                        mapOfProjects.printProjects();
                    }
                    else if (COMMAND_DELETE_ALL.equals(secondInput)) {
                        mapOfProjects.deleteAll();
                    }
                    else if (COMMAND_UPDATE_NAME_BY_ID.equals(secondArrayOfInput[0])) {
                        try {
                            Long id = Long.valueOf(secondArrayOfInput[1]);
                            StringBuilder builderName = new StringBuilder();
                            for (int i = 2; i < secondArrayOfInput.length; i++) {
                                builderName.append(secondArrayOfInput[i]);
                                builderName.append(" ");
                            }
                            System.out.println(Arrays.toString(secondArrayOfInput));
                            String newName = builderName.toString();
                            mapOfProjects.updateNameById(id, newName);
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Введите корректный id");
                        }
                    }
                    else if (COMMAND_ADD_TASK_TO_PROJECT.equals(secondArrayOfInput[0])) {
                        try {
                            if(secondArrayOfInput.length == 2) {
                                Long id = Long.valueOf(secondArrayOfInput[1]);
                                mapOfProjects.addTaskToProject(id);
                            }
                            else {
                                System.out.println("Некорректное значение id");
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Некорректное значение id");
                        }
                    }
                    else if (COMMAND_PRINT_TASK_OF_PROJECT.equals(secondArrayOfInput[0])) {
                        try {
                            if (secondArrayOfInput.length == 2) {
                                Long id = Long.valueOf(secondArrayOfInput[1]);
                                mapOfProjects.printTasksOfProject(id);
                            }
                            else {
                                System.out.println("Некорректное значение id");
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Некорректное значение id");
                        }
                    }
                    else if (COMMAND_DELETE_TASK_FROM_PROJECT.equals(secondArrayOfInput[0])) {
                        try {
                            if (secondArrayOfInput.length == 2) {
                                Long id = Long.valueOf(secondArrayOfInput[1]);
                                mapOfProjects.deleteTaskFromProject(id);
                            } else {
                                System.out.println("Некорректное значение id");
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Некорректное значение id");
                        }
                    }
                    else if (COMMAND_UPDATE_TASK_FROM_PROJECT.equals(secondArrayOfInput[0])) {
                        try {
                            if (secondArrayOfInput.length > 1) {
                                Long id = Long.valueOf(secondArrayOfInput[1]);
                                mapOfProjects.updateTaskFromProject(id);
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Неккоректное значение id");
                        }
                    }
                    else {
                        System.out.println("Такой комманды не существует");
                    }
                }

            } else if (COMMAND_EXIT.equals(firstInput)) {
                exit();
            }
            else {
                System.out.println("Такой комманды не существует");
            }
        }

    }

    private static void exit() {
        System.out.println("Выход из программы");
        System.exit(0);
    }

    private static String[] splitString(String string) {
        return string.split(" ");
    }
}
