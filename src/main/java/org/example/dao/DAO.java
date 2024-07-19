package org.example.dao;

import org.example.utils.Filenames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO<T> {
    public static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/";
    public static final String JDBC_URL = POSTGRESQL_URL + "restdemo";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    protected String path;
    protected Connection connection;
    protected Logger logger;


    public DAO(String path) {
        this.path = path;
        logger = Logger.getLogger("DaoLogger");
    }


    public void connectToDatabase() {
        connection(JDBC_URL);
    }

    public void connectToPostgresql() {
        connection(POSTGRESQL_URL);
    }

    private void connection(String postgresqlUrl) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(postgresqlUrl, USER, PASSWORD);
            logger.info("Connected to database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't connect to database", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "No postgresql driver", e);
        }
    }

    protected String getSql(Filenames filename) {
        StringBuilder query = new StringBuilder();

        String filePath = String.format("%s/WEB-INF/sql/%s.sql", path, filename.toString());
        logger.info("executing: " + filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Something went wrong while reading file", e);
        }
        return query.toString();
    }

    protected ResultSet findById (Long id, Filenames sql) {
        String query = this.getSql(sql);
        try {
            this.connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(query);
            if (id != null) {
                statement.setLong(1, id);
            }
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet;

        } catch (NullPointerException nullPointerException) {
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while reading database", e);
        }
        return null;
    }

    public void createDatabase() {
        String query = getSql(Filenames.CreateDatabase);
        try {
            this.connectToPostgresql();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            }
        } catch (SQLException e) {
            if (e.getMessage() != null && !(e.getMessage().contains("already exists"))) {
                logger.log(Level.SEVERE, "Something went wrong while creating database", e);
            }
        }
    }

    public void createTables() {
        String query = getSql(Filenames.CreateTables);
        try {
            this.connectToDatabase();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while creating tables", e);
        }
    }

    public void dropTables() {
        String query = getSql(Filenames.DropDatabase);
        try {
            this.connectToDatabase();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while dropping tables", e);
        }
    }

    public void create(T dto) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void update(T dto, long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void delete(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public T read(long id) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
