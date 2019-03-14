package ru.burmistrov.tm.command.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeByDefaultCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-deserializeByDefault";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize domain object";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Domain domain = (Domain) objectInputStream.readObject();
        System.out.println(domain.getProjects());
        System.out.println(domain.getTasks());

    }

    @Override
    public boolean isSecure() {
        return true;
    }


}
