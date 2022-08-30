package ru.geekbrains.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UnitOfWork {

    private final UserMapper userMapper;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUsers = new ArrayList<>();
    private final List<User> deleteUsers = new ArrayList<>();

    public UnitOfWork(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    public void commit() {
        performAction(newUsers, userMapper::insert);
        performAction(updateUsers, userMapper::update);
        performAction(deleteUsers, userMapper::delete);
    }

    private <T> void performAction(List<T> list, Consumer<T> consumer) {
        list.forEach(consumer);
        list.clear();
    }
}
