package ru.geekbrains.http;

import ru.geekbrains.handler.RequestHandlerFactory;
import ru.geekbrains.session.Session;
import ru.geekbrains.session.SessionFactory;
import ru.geekbrains.view.ViewResolver;

import java.net.Socket;

public class HttpSessionFactory implements SessionFactory {

    private final ViewResolver viewResolver;

    public HttpSessionFactory(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    public Session newSession(Socket socket) {
        return new HttpSession(socket,RequestHandlerFactory.createRequestHandlers(viewResolver));
    }
}
