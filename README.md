ACID
====

`Agile and Continuous Integration Development`

A Web application for agile methodologies and bugs report such as [Trello](https://trello.com/) or [Jira](https://www.atlassian.com/software/jira).


Required tools
--------------

- [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) (version >= 5)
- [Maven](http://maven.apache.org/) (version >= 3)
- [JOnAS](http://jonas.ow2.org/xwiki/bin/view/Main/) server (version 5.3.0 used for development)

Deployment
----------

If you are using Bash, you can use the Bash script `auto.sh` to build/clean/deploy the project on a local server.

Otherwise : 
- Compile the project using Maven : `mvn clean install`
- Copy the files `.jar`, `.ear` and `.war` in the folder `deploy` of your JOnAS server.

Application
-----------

To use ACID, just go on `JOnAS_server_address:port/ACID-web`.
For example : `http://localhost:9000/ACID-web/`
