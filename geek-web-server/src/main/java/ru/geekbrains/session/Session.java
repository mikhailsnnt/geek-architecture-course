package ru.geekbrains.session;

public abstract class Session {
    Thread thread;
    public Session(Runnable runnable){
        thread = new Thread(runnable);
    }

    public void start(){
        thread.start();
    }
}
