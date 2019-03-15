package ru.burmistrov.tm.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebService
public class TestEndpoint {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/TestEndpoint?wsdl", new TestEndpoint());
    }

    @WebMethod
    public List<String> run(@WebParam(name = "name")String name) {
        return Arrays.asList("1", "2");
    }

    @WebMethod
    public Date test() {
        return new Date();
    }
}
