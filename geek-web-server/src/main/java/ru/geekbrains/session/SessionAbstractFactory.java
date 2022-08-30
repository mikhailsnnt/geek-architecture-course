package ru.geekbrains.session;


import ru.geekbrains.http.HttpSessionFactory;
import ru.geekbrains.view.ViewResolver;

public class SessionAbstractFactory {
    private final ViewResolver viewResolver;

    public SessionAbstractFactory(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public  SessionFactory newSessionFactory(SessionType sessionType){
        switch (sessionType){
            case HTTP:
                return new HttpSessionFactory(viewResolver);
            default:
                throw new IllegalArgumentException();
        }
    }
}
