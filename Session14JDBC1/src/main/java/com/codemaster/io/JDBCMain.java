package com.codemaster.io;

import javax.sql.DataSource;
import java.sql.*;

public class JDBCMain {

    public static boolean loginByPlainStatement(String email, String password) throws SQLException {
        String sqlQuery = "select * from email_password where email = '"
                + email + "' and password = '" + password + "'";
        System.out.println("sqlQuery = " + sqlQuery);

        Statement statement = ConnectionManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        if (resultSet.next()) {
            System.out.println("Login Success");
            return true;
        } else {
            System.out.println("Login Failed");
        }
        return false;
    }

    public static boolean loginByPreparedStatement(String email, String password) throws SQLException {
        String sqlQuery = "select * from email_password where email = ? and password = ?";
        PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(sqlQuery);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Login Success");
            return true;
        } else {
            System.out.println("Login Failed");
        }
        return false;
    }

    public static void insertUserAndAuditData(User user) {
        try {
            DataSource dataSource = ConnectionManager.getDataSource();
            Connection connection = dataSource.getConnection();

            try {

                connection.setAutoCommit(false);

                String insertUserQuery = "insert into users (name, email, age, country) values (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery);

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getCountry());

                int cnt = preparedStatement.executeUpdate();
                if (cnt > 0) {
                    System.out.println("User inserted");
                }

                String auditQuery = "insert into audit (user_name, operation_type, timestamp) values (?, ?, ?)";

                preparedStatement = connection.prepareStatement(auditQuery);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, "INSERT");
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

                cnt = preparedStatement.executeUpdate();
                if (cnt > 0) {
                    System.out.println("Audit inserted");
                }

                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .name("user17")
                .email("user17@gmail.com")
                .age(32)
                .country("BD")
                .build();
        insertUserAndAuditData(user);

//        String email = "admin@gmail.com";
//        String password = "password123";
//        loginByPlainStatement(email, password);
//
//        loginByPreparedStatement(email, password);

//        User user = User.builder()
//                .name("user")
//                .email("user@email.com")
//                .age(32)
//                .country("USA")
//                .build();
//
//        // data insert
//        String insertSql = "insert into users (name, email, age, country) values ('"
//                + user.getName() + "', '"
//                + user.getEmail() + "', '"
//                + user.getAge() + "', '"
//                + user.getCountry() + "');";

//        System.out.println("insertSql = " + insertSql);
//
//        Statement statement = connection.createStatement();
//        // select query te executeQuery use kora better. execute/executeUpdate for insert/update
//        // execute returns boolean
//        int cnt = statement.executeUpdate(insertSql);
//        System.out.println("cnt = " + cnt);

//        User user = User.builder()
//                .name("user3")
//                .email("user3@email.com")
//                .age(32)
//                .country("USA")
//                .build();
//
//        String insertQuery = "insert into users (name, email, age, country) values (?, ?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getEmail());
//        preparedStatement.setInt(3, user.getAge());
//        preparedStatement.setString(4, user.getCountry());
//
//        int cnt = preparedStatement.executeUpdate();
//        System.out.println("cnt = " + cnt);
//
//        if (cnt > 0) {
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                user = user.toBuilder()
//                        .id(id)
//                        .build();
//                System.out.println("id = " + id);
//            }
//        }
//
//        user = user.toBuilder()
//                .name("user2Updated")
//                .build();
//
//        String updateQuery = "update users set name = ?, email = ?, age = ?, country = ? where id = ?";
//        preparedStatement = connection.prepareStatement(updateQuery);
//        int ind = 1;
//        preparedStatement.setString(ind++, user.getName());
//        preparedStatement.setString(ind++, user.getEmail());
//        preparedStatement.setInt(ind++, user.getAge());
//        preparedStatement.setString(ind++, user.getCountry());
//        preparedStatement.setInt(ind++, user.getId());
//
//        cnt = preparedStatement.executeUpdate();
//        System.out.println("cnt = " + cnt);


//        String sqlQuery = "select * from users";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(sqlQuery);
//
//        while (resultSet.next()) {
//            System.out.print(resultSet.getInt("id") + " ");
//            System.out.print(resultSet.getString("name") + " ");
//            System.out.print(resultSet.getString("email") + " ");
//            System.out.print(resultSet.getInt("age") + " ");
//            System.out.print(resultSet.getString("country"));
//            System.out.println();
//        }
    }
}
