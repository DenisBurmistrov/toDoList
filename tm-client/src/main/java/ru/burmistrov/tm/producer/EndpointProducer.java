package ru.burmistrov.tm.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.endpoint.*;

@Component
public class EndpointProducer {

    @Autowired
    private ProjectEndpointService projectEndpointService;

    @Autowired
    private TaskEndpointService taskEndpointService;

    @Autowired
    private UserEndpointService userEndpointService;

    @Autowired
    private SessionEndpointService sessionEndpointService;

    @Autowired
    private AdminEndpointService adminEndpointService;

    @Bean
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Bean
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Bean
    public AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }

    @Bean
    public UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Bean
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

}
