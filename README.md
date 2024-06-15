# Beyond_Campus_GVS_3
An IT system to manage partner universities for International Exchange Programs.
It contains the Sutton Framework and a beyondcampus application that uses the framework.

Data Object Class Diagram
![Alt text](https://github.com/YashMehta11k/Beyond_Campus_GVS_3/blob/main/diagrams/BeyondCampus_DataObjectDiagram.png)

HATEOAS Flow Diagram
![Alt text](https://github.com/YashMehta11k/Beyond_Campus_GVS_3/blob/main/diagrams/BeyondDiagram_HATEOASDiagram.png)

# How to start the beyondcampus application

1.Use class Start

Execute method main in class Start. This will start the embedded Tomcat server and deploy the demo application. The demo application
is available at http://localhost:8080/demo/api.

2.Use Docker

- For manual testing
Use file src/main/docker/Dockerfile as an example how to create a Docker image for the demo application. You first need to build
the WAR file of the demo application using mvn package. Then execute $> docker build . (don't forget the point after build) to
create an image. Finally, execute $> docker run -p 8080:8080 --rm <IMAGE_ID> to start the container.

- For integration testing
Call mvn verify to start the integration tests. This will create a Docker image and start a container for the demo application.
Then the integration tests will be executed. Finally, the container will be stopped and removed. All integration tests must
be located in the src/test/java directory and must end with IT. See class TestDemoAppIT for an example.
