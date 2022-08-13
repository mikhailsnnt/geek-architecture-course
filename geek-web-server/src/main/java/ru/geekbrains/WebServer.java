package ru.geekbrains;

import ru.geekbrains.http.HttpRequestHandler;
import ru.geekbrains.http.HttpSession;
import ru.geekbrains.view.ViewResolver;
import ru.geekbrains.view.ViewResolverImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    // Did not extract configs because lack of time
    private static final String WWW = "/Users/mihailnikitin/Desktop/GeekBrains/Architecture/1/www/";

    public static void main(String[] args) {
        ViewResolver viewResolver = new ViewResolverImpl(WWW);
        HttpRequestHandler requestHandler = new RequestHandler(viewResolver);
        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new HttpSession(socket, requestHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
