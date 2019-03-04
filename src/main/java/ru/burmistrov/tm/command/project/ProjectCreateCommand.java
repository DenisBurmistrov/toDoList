package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectCreateCommand extends AbstractCommand {


    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-createProject";
    }

    @Override
    public String description() {
        return "Create new project";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("Введите имя:");
                String name = bufferedReader.readLine();
                if (name.length() == 0) {
                    System.out.println("Нельзя использовать пустое имя");
                } else {
                    System.out.println("Введите описание:");
                    String description = bufferedReader.readLine();
                    System.out.println(super.getBootstrap().getProjectService().merge(name, description));
                    break;
                }
            }


        } catch (IOException e) {
            System.out.println("Некорректные данные");
        }
    }
}
