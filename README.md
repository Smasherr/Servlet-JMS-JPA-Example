# Servlet-JMS-JPA-Example
An example for Java EE 7 that sends a random message from a servlet which is asynchronously received by a message driven bean.

This example was tested with TomEE Plume 7.0.2. The following actions must be performed in order to make this example work:

 1. Modify `<tom-ee-home>/conf/tomee.xml` to activate apps directory and add a datasource:
 
 ```
    <?xml version="1.0" encoding="UTF-8"?>
    <tomee>
      <Deployments dir="apps/" />
      <Resource id="loggerDatabase" type="DataSource">
        <--modify this to your needs-->
    	JdbcDriver org.h2.Driver
    	JdbcUrl jdbc:h2:~/loggerDatabase;AUTO_SERVER=TRUE
    	UserName sa
    	Password
      </Resource>
    </tomee>
 ```

 2. Dependent on which JDBC driver is now specified for the datasource in `tomee.xml`, the appropriate JAR containing this driver class should be added to `<tom-ee-home>/lib`

 3. After building the projects from this repository with maven the resulting archives can be deployed to TomEE.
    * `jms-web-sender-0.0.1-SNAPSHOT.war` to `<tom-ee-home>/webapps`
    * `jms-jpa-receiver-0.0.1-SNAPSHOT.jar` to `<tom-ee-home>/apps`

 4. To use the deployed servlet call [http://localhost:8080/jms-web-sender-0.0.1-SNAPSHOT/sendMessage](http://localhost:8080/jms-web-sender-0.0.1-SNAPSHOT/sendMessage). It will send a random message via JMS to the entity driven bean that will persist that message to the database using JPA and JTA.
 