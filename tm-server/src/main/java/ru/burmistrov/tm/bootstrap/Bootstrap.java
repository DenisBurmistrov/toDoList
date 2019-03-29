package ru.burmistrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.*;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.*;
import ru.burmistrov.tm.utils.EntityManagerFactoryUtil;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    private void initEndpoints() throws IOException {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        @NotNull final String host = property.getProperty("host");
        @NotNull final String port = property.getProperty("port");
        Endpoint.publish("http://" + host+":" + port + "/ProjectEndpoint", new ProjectEndpoint());
        Endpoint.publish("http://" + host+":" + port + "/TaskEndpoint", new TaskEndpoint());
        Endpoint.publish("http://" + host + ":" + port + "/UserEndpoint", new UserEndpoint());
        Endpoint.publish("http://" + host+":" + port + "/SessionEndpoint", new SessionEndpoint());
        Endpoint.publish("http://" + host + ":" + port + "/AdminEndpoint", new AdminEndpoint());
    }

    @Override
    public void init() throws IOException {
        initEndpoints();
    }
}




