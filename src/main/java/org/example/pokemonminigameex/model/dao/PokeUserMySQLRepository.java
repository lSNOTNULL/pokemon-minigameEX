package org.example.pokemonminigameex.model.dao;

import org.example.pokemonminigameex.model.dto.PokeUser;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@Repository
public class PokeUserMySQLRepository extends MySQLRepository {
    Logger logger = Logger.getLogger(JDBCRepository.class.getName());

    public PokeUserMySQLRepository() throws Exception {
        super();
        logger.info("정상적으로 생성되었습니다");
    }

    public void createPokeUser(PokeUser pokeUser){
        try (Statement statement = connection.createStatement()){
//            statement.executeQuery XXX
            // INSERT 는 executeUpdate() 사용
             String query = "INSERT INTO poke_user (poke_user_id, username, password) VALUES ('%s', '%s', '%s')"
                     .formatted(pokeUser.id(), pokeUser.username(), pokeUser.password());
             int rowCount = statement.executeUpdate(query);
             if (rowCount == 0){ // 쿼리 INSERT가 진행되지 않았다면 에러발생
                 throw new RuntimeException("뭔가 잘못됨");
             }
             logger.info("정상 추가");
            } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
