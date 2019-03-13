package ru.burmistrov.tm.command.serialize;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Status;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.repository.ProjectRepository;

import java.text.ParseException;

public class SerializeCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "name";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "desc";
    }

    @Override
    public void execute() {
        ProjectRepository.class.getResource("projects");
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    public static void main(String[] args) {
        try {
            System.out.println(ProjectRepository.class.getDeclaredField("projects"));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        ProjectRepository.class.getResource("projects");
       /* try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat")))
        {
            Project p = new Project();
            p.setName("sad");
            p.setDescription("asd");
            p.setUserId("asdda");
            p.setDateEnd(new Date());
            p.setDateBegin(new Date());
            p.setStatus(Status.SHEDULED);
            oos.writeObject(p);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            Project p = (Project) ois.readObject();
            System.out.print(p);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }*/
    }
}
