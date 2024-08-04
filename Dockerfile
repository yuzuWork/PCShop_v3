FROM tomcat:9.0

COPY ./PCShop_v3.war /usr/local/tomcat/webapps/your_project.war

CMD ["catalina.sh", "run"]