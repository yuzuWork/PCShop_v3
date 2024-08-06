FROM tomcat:9.0

COPY ./PCShop_v3.war /usr/local/tomcat/webapps/PCShop_v3.war

CMD ["catalina.sh", "run"]