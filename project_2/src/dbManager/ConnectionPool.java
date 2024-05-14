package dbManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {
    private static ConnectionPool instance=null;
    private static final String DOMAIN_STRING="localhost";//127.0.0.1
    private static final String DB_NAME="persons";
    private static final String USER_NAME="root";
    private static final String PASSWORD="123456";
    private static final String CONNECTION_STRING="jdbc:mysql://" + DOMAIN_STRING + "/"
            + DB_NAME + "?user=" + USER_NAME + "&password=" + PASSWORD;
    private final Queue<Connection> connections;

    private ConnectionPool() throws SQLException, ClassNotFoundException {
        connections=new LinkedList<>();
        connections.add(initDatabase());
    }
    public static ConnectionPool getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null){
            instance=new ConnectionPool();
        }
        return instance;
    }
    private static Connection initDatabase() throws SQLException ,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println ("Connection to database: " + CONNECTION_STRING+" done successfully");
        Connection connection = DriverManager.getConnection(CONNECTION_STRING);
        System.out.println("....");
        System.out.println("Connected to database " + DB_NAME);
        return connection;
    }

    public Connection getConnection() {
        return connections.remove();
    }
    private void restoreConnection(Connection connection){
        connections.add(connection);
    }
    public void closeAllConnection() throws SQLException {
        for(Connection connection:connections)
            connection.close();
    }

    public ResultSet runGetQuery(String sql) throws SQLException {
        Connection connection=this.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        System.out.println("runGetQuarry() done!");
        this.restoreConnection(connection);
        return resultSet;
    }
    public void runDeleteQuerry(String sql) throws SQLException {
        Connection connection=this.getConnection();
        System.out.println(sql+" is executing");
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        this.restoreConnection(connection);
    }
    public void runUpdateQuerry(String sql) throws SQLException {
        Connection connection=this.getConnection();
        System.out.println(sql+" is executing");
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        this.restoreConnection(connection);
    }
    public int runInsertQuerry(String sql) throws SQLException {
        Connection connection=this.getConnection();
        System.out.println(sql+" is executing");
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        int ret = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int newId = -1;
        while (resultSet.next()) {
            newId = resultSet.getInt(1);
            System.out.println("Recored added with id=" + newId);
        }
        System.out.println("Data inserted successfully!, added entries=" + ret);
        this.restoreConnection(connection);
        return newId;

    }
}
