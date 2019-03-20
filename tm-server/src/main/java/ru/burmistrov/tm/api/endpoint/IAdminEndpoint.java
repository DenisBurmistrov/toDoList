package ru.burmistrov.tm.api.endpoint;


import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void saveDataByDefault(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByFasterXml(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException, IOException;

    @WebMethod
    void saveDataByJaxbXml(@WebParam @NotNull Session session) throws IOException, JAXBException;

    @WebMethod
    void loadDataByDefault(@WebParam @NotNull Session session) throws IOException, ClassNotFoundException;

    @WebMethod
    void loadDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void loadDataByFasterXml(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void loadDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException;

    @WebMethod
    void loadDataByJaxbXml(@WebParam @NotNull Session session) throws JAXBException;

}
