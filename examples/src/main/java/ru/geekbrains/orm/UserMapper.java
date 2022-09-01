package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement selectUser;

    private final Map<Long, User> identityMap = new HashMap<>();
    private final PreparedStatement deleteUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement insertUser;

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id = ?");
            this.insertUser = conn.prepareStatement("insert into users values (?,?,?)"); //Very dummy impl))
            this.deleteUser = conn.prepareStatement("update users set username = ? , password = ? where id =  ?");
            this.updateUser = conn.prepareStatement("delete from users where id = ?");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        try {
            updateUser.setString(1, user.getLogin());
            updateUser.setString(2, user.getPassword());
            updateUser.setLong(3, user.getId());
            updateUser.executeQuery();
            identityMap.put(user.getId(), user); //Dummy impl
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        try{
            insertUser.setLong(1, user.getId()); //:( Out of time
            insertUser.setString(2, user.getLogin());
            insertUser.setString(3, user.getPassword());
            identityMap.put(user.getId(),user);
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean delete(User user) {
        try{
            deleteUser.setLong(1, user.getId()); //:( Out of time
            boolean successfully = deleteUser.executeUpdate() == 1;
            identityMap.remove(user.getId());
            return successfully;
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
