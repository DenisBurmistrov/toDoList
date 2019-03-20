package ru.burmistrov.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand(){
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws ParseException, IOException, JAXBException, ClassNotFoundException, ParseException_Exception, ClassNotFoundException_Exception, IOException_Exception, JAXBException_Exception, CloneNotSupportedException_Exception;

    @NotNull
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public abstract boolean isSecure();

    public void setServiceLocator(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
