package ru.geekbrains.config;

class DummyConfig implements Config {
    @Override
    public String wwwPath() {
        return "/Users/mihailnikitin/Desktop/GeekBrains/Architecture/1/www/";
    }

    @Override
    public int port() {
        return 8088;
    }
}
