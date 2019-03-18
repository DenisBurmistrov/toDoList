package ru.burmistrov.tm.api.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-18T12:22:54.526+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://service.api.tm.burmistrov.ru/", name = "IUserService")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserService {

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/removeUserByIdRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/removeUserByIdResponse")
    @RequestWrapper(localName = "removeUserById", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.RemoveUserById")
    @ResponseWrapper(localName = "removeUserByIdResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.RemoveUserByIdResponse")
    public void removeUserById(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/logInRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/logInResponse")
    @RequestWrapper(localName = "logIn", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.LogIn")
    @ResponseWrapper(localName = "logInResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.LogInResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.burmistrov.tm.api.service.User logIn(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/createUserRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/createUserResponse")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.CreateUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.burmistrov.tm.api.service.User createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        ru.burmistrov.tm.api.service.Role arg6
    );

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/updatePasswordByIdRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/updatePasswordByIdResponse")
    @RequestWrapper(localName = "updatePasswordById", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.UpdatePasswordById")
    @ResponseWrapper(localName = "updatePasswordByIdResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.UpdatePasswordByIdResponse")
    public void updatePasswordById(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/updateUserByIdRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/updateUserByIdResponse")
    @RequestWrapper(localName = "updateUserById", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.UpdateUserById")
    @ResponseWrapper(localName = "updateUserByIdResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.UpdateUserByIdResponse")
    public void updateUserById(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        ru.burmistrov.tm.api.service.Role arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6
    );

    @WebMethod
    @Action(input = "http://service.api.tm.burmistrov.ru/IUserService/removeAllUsersRequest", output = "http://service.api.tm.burmistrov.ru/IUserService/removeAllUsersResponse")
    @RequestWrapper(localName = "removeAllUsers", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.RemoveAllUsers")
    @ResponseWrapper(localName = "removeAllUsersResponse", targetNamespace = "http://service.api.tm.burmistrov.ru/", className = "ru.burmistrov.tm.api.service.RemoveAllUsersResponse")
    public void removeAllUsers(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
