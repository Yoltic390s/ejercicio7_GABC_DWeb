# 1. Traemos la imagen oficial moderna (Eclipse Temurin) para Java 25
FROM eclipse-temurin:25-jdk

# 2. Creamos la carpeta de trabajo
WORKDIR /app

# 3. Copiamos todo tu código a la caja
COPY . .

# 4. Le damos permiso al script de Maven para ejecutarse
RUN chmod +x ./mvnw

# 5. La máquina de la nube compila el proyecto usando Maven
RUN ./mvnw clean package -DskipTests

# 6. Exponemos el puerto
EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]