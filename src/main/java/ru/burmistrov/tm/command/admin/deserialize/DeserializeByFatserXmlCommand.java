package ru.burmistrov.tm.command.admin.deserialize;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import java.io.File;
import java.io.IOException;

public class DeserializeByFatserXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-deserializeByFasterXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain from xml";
    }

    @Override
    public void execute() throws IOException {
        File file = new File("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".xml");
        XmlMapper xmlMapper = new XmlMapper();
        Domain domain = xmlMapper.readValue(file, Domain.class);
        System.out.println(domain.getProjects());
        System.out.println(domain.getTasks());

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
