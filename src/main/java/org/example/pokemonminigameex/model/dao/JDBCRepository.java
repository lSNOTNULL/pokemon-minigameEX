package org.example.pokemonminigameex.model.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public interface JDBCRepository {

    default void prepareClass(String className) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    default Connection getConnection(String url, Properties properties) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "%s%s:%s/%s"
                .formatted(url, properties.getProperty("host"),
                        properties.getProperty("port"),
                        properties.getProperty("database"));
        return DriverManager.getConnection(url, properties);
    }
}
