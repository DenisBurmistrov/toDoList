package ru.burmistrov.tm.command.deserialize;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.IOException_Exception;

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
    public void execute() throws IOException_Exception {
        getServiceLocator().getAdminEndpoint().loadDataByFasterXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
