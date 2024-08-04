
FROM tomcat:9.0

COPY C:\Users\Owner\Desktop\development /usr/local/tomcat/webapps/your_project.war

CMD ["catalina.sh", "run"]