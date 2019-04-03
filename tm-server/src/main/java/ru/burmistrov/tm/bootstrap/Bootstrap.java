package ru.burmistrov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.endpoint.*;
import ru.burmistrov.tm.api.loader.ServiceLocator;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
public final class Bootstrap implements ServiceLocator {

    @Inject
    private IProjectEndpoint projectEndpoint;

    @Inject
    private ITaskEndpoint taskEndpoint;

    @Inject
    private IAdminEndpoint adminEndpoint;

    @Inject
    private ISessionEndpoint sessionEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    private void initEndpoints() throws IOException {
        @NotNull final Properties property = new Properties();
        property.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        @NotNull final String host = property.getProperty("host");
        @NotNull final String port = property.getProperty("port");
        Endpoint.publish("http://" + host+":" + port + "/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://" + host+":" + port + "/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://" + host + ":" + port + "/UserEndpoint", userEndpoint);
        Endpoint.publish("http://" + host+":" + port + "/SessionEndpoint", sessionEndpoint);
        Endpoint.publish("http://" + host + ":" + port + "/AdminEndpoint", adminEndpoint);
    }

    @Override
    public void init() throws IOException {
        initEndpoints();
    }
}




