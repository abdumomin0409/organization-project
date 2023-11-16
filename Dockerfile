# Asosiy Docker obraz
FROM amazoncorretto:17.0.7-alpine
# Ishga tushirish katalogi
RUN apk update && apk add tzdata
ENV TZ=Asia/Tashkent
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
LABEL authors="abdumomin"
ENV JAVA_HOME=/opt/openjdk-17
# Ilova jar fayli va mavjudlikni katalogga nusxalash
COPY target/organization-service.jar /app/organization-service.jar
# Ilova porti
EXPOSE 8095
# Ilovani ishga tushirish
CMD ["java", "-jar", "organization-service.jar"]
