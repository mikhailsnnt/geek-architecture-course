package ru.geekbrains.config;

public class ConfigFactory {
    public static Config newConfig(){
        //Creating file config, if file found :)
        return new DummyConfig();
    }
}
