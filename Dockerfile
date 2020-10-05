FROM openjdk:8
WORKDIR /
COPY ./* ./
RUN javac Main.java
