package ru.geekbrains.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ViewResolverImpl implements ViewResolver {
    Path baseDir;

    public ViewResolverImpl(String basePath) {
        baseDir = Path.of(basePath);
        if(Files.notExists(baseDir))
            throw new IllegalArgumentException("Specified directory does not exist "+basePath);
    }

    @Override
    public Optional<String> resolveViewByName(String name) {
        if(name.startsWith("/")) // For simplicity
        {
            if(name.equals("/"))
                return Optional.empty();
            name = name.substring(1);
        }
        if (Files.notExists(baseDir.resolve(name)))
            return Optional.empty();
        try{
            return Optional.of(Files.readString(baseDir.resolve(name)));
        }catch (IOException e){
            System.out.printf("Error resolving view: %s", name);
            throw new RuntimeException(e);
        }
    }
}
