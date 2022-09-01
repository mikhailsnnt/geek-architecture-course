package ru.geekbrains.orm;

import java.io.ObjectInputFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Optional;

public class UserRepository {

    private final Connection conn;

    private final UserMapper mapper;

    private final UnitOfWork unitOfWork;
    private Savepoint savePoint;

    public UserRepository(Connection conn) {
        this.conn = conn;
        this.mapper = new UserMapper(conn);
        this.unitOfWork = new UnitOfWork(mapper);
    }

    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
            this.savePoint =  conn.setSavepoint();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(User user) {
        mapper.insert(user);
    }

    public void update(User user) {
        mapper.update(user);
    }

    public void delete(User user) {
        mapper.delete(user);
    }

    public void commitTransaction() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollbackTransaction() {
        if(savePoint == null)
            throw new IllegalStateException("Dude, no transaction active...");
        try {
            conn.rollback(savePoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
