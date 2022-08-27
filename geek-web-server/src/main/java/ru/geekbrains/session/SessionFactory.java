package ru.geekbrains.session;

import java.net.Socket;

public interface SessionFactory {
    Session newSession(Socket socket);
}
