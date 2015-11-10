# BasicLoginServer
You need to have Firefox installed in order to run the Selenium tests.

Binary .war ready to deploy is in the /bin/ folder.

Available instance online at: (might not be up since is a free server online)

http://thawing-beyond-8990.herokuapp.com/login.html 

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
