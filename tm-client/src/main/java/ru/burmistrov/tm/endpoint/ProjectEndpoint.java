package ru.burmistrov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-21T11:57:19.827+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.burmistrov.ru/", name = "ProjectEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndpoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeProjectByIdRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeProjectByIdResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeProjectById/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "removeProjectById", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.RemoveProjectById")
    @ResponseWrapper(localName = "removeProjectByIdResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.RemoveProjectByIdResponse")
    public void removeProjectById(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/createProjectRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/createProjectResponse", fault = {@FaultAction(className = ParseException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/createProject/Fault/ParseException"), @FaultAction(className = IOException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/createProject/Fault/IOException"), @FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/createProject/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "createProject", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.CreateProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.burmistrov.tm.endpoint.Project createProject(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    ) throws ParseException_Exception, IOException_Exception, CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateEndRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateEndResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateEnd/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findAllProjectsSortByDateEnd", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByDateEnd")
    @ResponseWrapper(localName = "findAllProjectsSortByDateEndResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByDateEndResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.burmistrov.tm.endpoint.Project> findAllProjectsSortByDateEnd(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByDescriptionRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByDescriptionResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByDescription/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findProjectByDescription", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindProjectByDescription")
    @ResponseWrapper(localName = "findProjectByDescriptionResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindProjectByDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.burmistrov.tm.endpoint.Project findProjectByDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateBeginRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateBeginResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByDateBegin/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findAllProjectsSortByDateBegin", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByDateBegin")
    @ResponseWrapper(localName = "findAllProjectsSortByDateBeginResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByDateBeginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.burmistrov.tm.endpoint.Project> findAllProjectsSortByDateBegin(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByNameRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByNameResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findProjectByName/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findProjectByName", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindProjectByName")
    @ResponseWrapper(localName = "findProjectByNameResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindProjectByNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.burmistrov.tm.endpoint.Project findProjectByName(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeAllProjectsRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeAllProjectsResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/removeAllProjects/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "removeAllProjects", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.RemoveAllProjects")
    @ResponseWrapper(localName = "removeAllProjectsResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.RemoveAllProjectsResponse")
    public void removeAllProjects(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByStatusRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByStatusResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsSortByStatus/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findAllProjectsSortByStatus", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByStatus")
    @ResponseWrapper(localName = "findAllProjectsSortByStatusResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsSortByStatusResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.burmistrov.tm.endpoint.Project> findAllProjectsSortByStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/updateProjectByIdRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/updateProjectByIdResponse", fault = {@FaultAction(className = ParseException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/updateProjectById/Fault/ParseException"), @FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/updateProjectById/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "updateProjectById", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.UpdateProjectById")
    @ResponseWrapper(localName = "updateProjectByIdResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.UpdateProjectByIdResponse")
    public void updateProjectById(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5
    ) throws ParseException_Exception, CloneNotSupportedException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsRequest", output = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjectsResponse", fault = {@FaultAction(className = CloneNotSupportedException_Exception.class, value = "http://endpoint.tm.burmistrov.ru/ProjectEndpoint/findAllProjects/Fault/CloneNotSupportedException")})
    @RequestWrapper(localName = "findAllProjects", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjects")
    @ResponseWrapper(localName = "findAllProjectsResponse", targetNamespace = "http://endpoint.tm.burmistrov.ru/", className = "ru.burmistrov.tm.endpoint.FindAllProjectsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.burmistrov.tm.endpoint.Project> findAllProjects(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.burmistrov.tm.endpoint.Session arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws CloneNotSupportedException_Exception;
}
