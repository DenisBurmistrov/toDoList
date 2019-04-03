package ru.burmistrov.tm.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-03T10:43:15.884+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "AdminEndpointService",
                  wsdlLocation = "http://localhost:8080/AdminEndpoint?wsdl",
                  targetNamespace = "http://endpoint.tm.burmistrov.ru/")
public class AdminEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.burmistrov.ru/", "AdminEndpointService");
    public final static QName AdminEndpointPort = new QName("http://endpoint.tm.burmistrov.ru/", "AdminEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/AdminEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AdminEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/AdminEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AdminEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AdminEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AdminEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public AdminEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AdminEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AdminEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns AdminEndpoint
     */
    @WebEndpoint(name = "AdminEndpointPort")
    public AdminEndpoint getAdminEndpointPort() {
        return super.getPort(AdminEndpointPort, AdminEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdminEndpoint
     */
    @WebEndpoint(name = "AdminEndpointPort")
    public AdminEndpoint getAdminEndpointPort(WebServiceFeature... features) {
        return super.getPort(AdminEndpointPort, AdminEndpoint.class, features);
    }

}
