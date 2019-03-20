package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.endpoint.IAdminEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public class AdminEndpoint implements IAdminEndpoint {

    private ServiceLocator serviceLocator;

    public AdminEndpoint() {
    }

    public AdminEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Override
    public void saveDataByDefault(@NotNull Session session) throws IOException {
        serviceLocator.getAdminService().saveDataByDefault(session);
    }

    @WebMethod
    @Override
    public void saveDataByFasterXmlJson(@NotNull Session session) throws IOException {
        serviceLocator.getAdminService().saveDataByFasterXmlJson(session);
    }

    @WebMethod
    @Override
    public void saveDataByFasterXml(@NotNull Session session) throws IOException {
        serviceLocator.getAdminService().saveDataByFasterXml(session);
    }

    @WebMethod
    @Override
    public void saveDataByJaxbJson(@NotNull Session session) throws JAXBException, IOException {
        serviceLocator.getAdminService().saveDataByJaxbJson(session);
    }

    @WebMethod
    @Override
    public void saveDataByJaxbXml(@NotNull Session session) throws IOException, JAXBException {
        serviceLocator.getAdminService().saveDataByJaxbXml(session);
    }

    @WebMethod
    @Override
    public void loadDataByDefault(@NotNull Session session) throws IOException, ClassNotFoundException {
        serviceLocator.getAdminService().loadDataByDefault(session);
    }

    @WebMethod
    @Override
    public void loadDataByFasterXmlJson(@NotNull Session session) throws IOException {
        serviceLocator.getAdminService().loadDataByFasterXmlJson(session);
    }

    @WebMethod
    @Override
    public void loadDataByFasterXml(@NotNull Session session) throws IOException {
        serviceLocator.getAdminService().loadDataByFasterXml(session);
    }

    @WebMethod
    @Override
    public void loadDataByJaxbJson(@NotNull Session session) throws JAXBException {
        serviceLocator.getAdminService().loadDataByJaxbJson(session);
    }

    @WebMethod
    @Override
    public void loadDataByJaxbXml(@NotNull Session session) throws JAXBException {
        serviceLocator.getAdminService().loadDataByJaxbXml(session);
    }
}
