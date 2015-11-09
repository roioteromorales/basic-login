# BasicLoginServer
Maven Commands:
--------------------------------------
Clean Install without Integration Tests:
--------------------------------------
clean install 


Clean Install with Integration Tests:
--------------------------------------
clean install -DskipIT=false


Run embedded tomcat with the war deployed:
--------------------------------------
clean install tomcat7:run
