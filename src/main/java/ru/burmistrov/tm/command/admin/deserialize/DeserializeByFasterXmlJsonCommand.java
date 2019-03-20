package ru.burmistrov.tm.command.admin.deserialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class DeserializeByFasterXmlJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-deserializeByFasterXmlJson";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain fron json";
    }

    @Override
    public void execute() throws ParseException, IOException, JAXBException, ClassNotFoundException {
        File file = new File("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        Domain domain = objectMapper.readValue(file, Domain.class);
        System.out.println(domain.getProjects());
        System.out.println(domain.getTasks());


    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
