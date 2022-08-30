package ru.geekbrains;

import ru.geekbrains.config.Config;
import ru.geekbrains.config.ConfigFactory;
import ru.geekbrains.session.SessionAbstractFactory;
import ru.geekbrains.session.SessionType;
import ru.geekbrains.view.ViewResolver;
import ru.geekbrains.view.ViewResolverFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) {
        Config config = ConfigFactory.newConfig();
        ViewResolver viewResolver = ViewResolverFactory.newViewResolver(config);
        SessionAbstractFactory sessionAbstractFactory = new SessionAbstractFactory(viewResolver);
        try (ServerSocket serverSocket = new ServerSocket(config.port())) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                sessionAbstractFactory
                        .newSessionFactory(SessionType.HTTP)
                        .newSession(socket)
                        .start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
