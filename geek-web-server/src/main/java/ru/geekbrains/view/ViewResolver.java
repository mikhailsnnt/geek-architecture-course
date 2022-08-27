package ru.geekbrains.view;

import java.util.Optional;

public interface ViewResolver {
    Optional<String> resolveViewByName(String name);
}
