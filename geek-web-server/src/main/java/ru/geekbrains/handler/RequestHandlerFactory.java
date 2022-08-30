package ru.geekbrains.handler;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class RequestHandlerFactory {
    public static RequestHandler createRequestHandlers(Object... handlerDependencies) {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        LoggingRequestHandler rootHandler = new LoggingRequestHandler();
        reflections
                .getTypesAnnotatedWith(Handler.class)
                .stream()
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(Handler.class).order()))
                .map(clazz -> {
                    try {
                        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
                        Object[] constructorDependencies = Arrays.stream(constructor.getParameterTypes())
                                .map(param -> Arrays.stream(handlerDependencies)
                                        .filter(param::isInstance)
                                        .findAny()
                                        .orElseThrow(() -> new IllegalArgumentException(String.format("Dependency %s not provided", param.getCanonicalName()))))
                                .toArray();
                        return (RequestHandler) constructor.newInstance(
                                constructorDependencies
                                // Optionally could just ignore not created handler
                        );
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .reduce(rootHandler, (old, nw) -> {
                    try {
                        Field nextHandler;
                        if (old instanceof HttpRequestHandler)
                            nextHandler = HttpRequestHandler.class.getDeclaredField("nextHandler");
                        else
                            nextHandler = old.getClass()
                                    .getDeclaredField("nextHandler");
                        setField(nextHandler,old,nw);
                        Field supportedMethods = HttpRequestHandler.class.getDeclaredField("supportedMethods");
                        setField(supportedMethods,nw,Set.of(nw.getClass().getAnnotation(Handler.class).methods()));
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    return nw;
                });
        return rootHandler;
    }

    private static void setField(Field field, Object target, Object value){
        try {
            field.setAccessible(true);
            field.set(target, value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
