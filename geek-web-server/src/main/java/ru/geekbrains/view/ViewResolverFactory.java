package ru.geekbrains.view;

import ru.geekbrains.config.Config;

public class ViewResolverFactory {
    public static ViewResolver newViewResolver(Config config){
        return new LocalViewResolverImpl(config.wwwPath());
    }
}
