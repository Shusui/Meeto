Meeto
=====

Project for the Distributed Systems course

Dependências:
Tomcat 7 - Web Server
Java   7 - De preferência a última versão estável, e não Java8

Como correr a aplicação:
 → Compilar a base de dados com o comando: Meetoj7/src$ java rmiserver/*.java;
 → Iniciar o servidor de base de dados: Meetoj7/src$ java -cp .:mysql-connector-java-5.1.33-bin.jar rmiserver.Database;
 → Mover o ficheiro Meetoj7/War/Meetoj7.war para a pasta webapps do tomcat7;
 → Abrir um browser no URL: localhost:8080/Meetoj7;


