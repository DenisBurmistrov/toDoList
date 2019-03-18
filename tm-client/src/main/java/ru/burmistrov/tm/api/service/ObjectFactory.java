
package ru.burmistrov.tm.api.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.burmistrov.tm.api.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateUser_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "createUserResponse");
    private final static QName _LogIn_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "logIn");
    private final static QName _LogInResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "logInResponse");
    private final static QName _RemoveAllUsers_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "removeAllUsers");
    private final static QName _RemoveAllUsersResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "removeAllUsersResponse");
    private final static QName _RemoveUserById_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "removeUserById");
    private final static QName _RemoveUserByIdResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "removeUserByIdResponse");
    private final static QName _UpdatePasswordById_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "updatePasswordById");
    private final static QName _UpdatePasswordByIdResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "updatePasswordByIdResponse");
    private final static QName _UpdateUserById_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "updateUserById");
    private final static QName _UpdateUserByIdResponse_QNAME = new QName("http://service.api.tm.burmistrov.ru/", "updateUserByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.burmistrov.tm.api.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link LogIn }
     * 
     */
    public LogIn createLogIn() {
        return new LogIn();
    }

    /**
     * Create an instance of {@link LogInResponse }
     * 
     */
    public LogInResponse createLogInResponse() {
        return new LogInResponse();
    }

    /**
     * Create an instance of {@link RemoveAllUsers }
     * 
     */
    public RemoveAllUsers createRemoveAllUsers() {
        return new RemoveAllUsers();
    }

    /**
     * Create an instance of {@link RemoveAllUsersResponse }
     * 
     */
    public RemoveAllUsersResponse createRemoveAllUsersResponse() {
        return new RemoveAllUsersResponse();
    }

    /**
     * Create an instance of {@link RemoveUserById }
     * 
     */
    public RemoveUserById createRemoveUserById() {
        return new RemoveUserById();
    }

    /**
     * Create an instance of {@link RemoveUserByIdResponse }
     * 
     */
    public RemoveUserByIdResponse createRemoveUserByIdResponse() {
        return new RemoveUserByIdResponse();
    }

    /**
     * Create an instance of {@link UpdatePasswordById }
     * 
     */
    public UpdatePasswordById createUpdatePasswordById() {
        return new UpdatePasswordById();
    }

    /**
     * Create an instance of {@link UpdatePasswordByIdResponse }
     * 
     */
    public UpdatePasswordByIdResponse createUpdatePasswordByIdResponse() {
        return new UpdatePasswordByIdResponse();
    }

    /**
     * Create an instance of {@link UpdateUserById }
     * 
     */
    public UpdateUserById createUpdateUserById() {
        return new UpdateUserById();
    }

    /**
     * Create an instance of {@link UpdateUserByIdResponse }
     * 
     */
    public UpdateUserByIdResponse createUpdateUserByIdResponse() {
        return new UpdateUserByIdResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "logIn")
    public JAXBElement<LogIn> createLogIn(LogIn value) {
        return new JAXBElement<LogIn>(_LogIn_QNAME, LogIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogInResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "logInResponse")
    public JAXBElement<LogInResponse> createLogInResponse(LogInResponse value) {
        return new JAXBElement<LogInResponse>(_LogInResponse_QNAME, LogInResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "removeAllUsers")
    public JAXBElement<RemoveAllUsers> createRemoveAllUsers(RemoveAllUsers value) {
        return new JAXBElement<RemoveAllUsers>(_RemoveAllUsers_QNAME, RemoveAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "removeAllUsersResponse")
    public JAXBElement<RemoveAllUsersResponse> createRemoveAllUsersResponse(RemoveAllUsersResponse value) {
        return new JAXBElement<RemoveAllUsersResponse>(_RemoveAllUsersResponse_QNAME, RemoveAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "removeUserById")
    public JAXBElement<RemoveUserById> createRemoveUserById(RemoveUserById value) {
        return new JAXBElement<RemoveUserById>(_RemoveUserById_QNAME, RemoveUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "removeUserByIdResponse")
    public JAXBElement<RemoveUserByIdResponse> createRemoveUserByIdResponse(RemoveUserByIdResponse value) {
        return new JAXBElement<RemoveUserByIdResponse>(_RemoveUserByIdResponse_QNAME, RemoveUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "updatePasswordById")
    public JAXBElement<UpdatePasswordById> createUpdatePasswordById(UpdatePasswordById value) {
        return new JAXBElement<UpdatePasswordById>(_UpdatePasswordById_QNAME, UpdatePasswordById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePasswordByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "updatePasswordByIdResponse")
    public JAXBElement<UpdatePasswordByIdResponse> createUpdatePasswordByIdResponse(UpdatePasswordByIdResponse value) {
        return new JAXBElement<UpdatePasswordByIdResponse>(_UpdatePasswordByIdResponse_QNAME, UpdatePasswordByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "updateUserById")
    public JAXBElement<UpdateUserById> createUpdateUserById(UpdateUserById value) {
        return new JAXBElement<UpdateUserById>(_UpdateUserById_QNAME, UpdateUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.api.tm.burmistrov.ru/", name = "updateUserByIdResponse")
    public JAXBElement<UpdateUserByIdResponse> createUpdateUserByIdResponse(UpdateUserByIdResponse value) {
        return new JAXBElement<UpdateUserByIdResponse>(_UpdateUserByIdResponse_QNAME, UpdateUserByIdResponse.class, null, value);
    }

}
