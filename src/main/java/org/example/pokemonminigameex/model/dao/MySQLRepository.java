package org.example.pokemonminigameex.model.dao;

import org.example.pokemonminigameex.util.DotenvMixin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

abstract public class MySQLRepository implements JDBCRepository, DotenvMixin {
    Logger logger = Logger.getLogger(MySQLRepository.class.getName());

    protected Connection connection;

    public MySQLRepository() throws Exception {
        logger.info("생성 시도");
        prepareClass("com.mysql.cj.jdbc.Driver");
        String url = dotenv.get("MySQL_URL");
        Properties properties = new Properties();
        properties.put("user", dotenv.get("MySQL_USER"));
        properties.put("password", dotenv.get("MySQL_PASSWORD"));
        properties.put("database", dotenv.get("MySQL_DATABASE"));
        properties.put("host", dotenv.get("MySQL_HOST"));
        properties.put("port", dotenv.get("MySQL_PORT"));
        properties.put("databaseName", dotenv.get("MySQL_DATABASE"));
        connection = getConnection(url,properties);
        testConnection();
        logger.info("정상적으로 생성");
    }

    public void testConnection() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute("SELECT 1+1");
    }
}
