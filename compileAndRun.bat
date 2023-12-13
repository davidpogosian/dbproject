@ECHO OFF
for /r %%v in (src\*.java) do javac -cp "C:\Users\David\apache-tomcat-9.0.80\webapps\treecutting\WEB-INF\lib\servlet-api.jar;C:\Users\David\apache-tomcat-9.0.80\webapps\treecutting\WEB-INF\lib\mysql-connector-java-8.0.13.jar;C:\Users\David\apache-tomcat-9.0.80\webapps\treecutting\src" "%%v" -d ./WEB-INF/classes
ECHO src files compiled
C:\Users\David\apache-tomcat-9.0.80\bin\startup.bat
pause
@ECHO ON
