package ru.burmistrov.tm.producer;

import ru.burmistrov.tm.endpoint.*;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class EndpointProducer {

    @Inject
    private ProjectEndpointService projectEndpointService;

    @Inject
    private TaskEndpointService taskEndpointService;

    @Inject
    private UserEndpointService userEndpointService;

    @Inject
    private SessionEndpointService sessionEndpointService;

    @Inject
    private AdminEndpointService adminEndpointService;

    @Produces
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Produces
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Produces
    public AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }

    @Produces
    public UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Produces
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

}
