package org.example.pokemonminigameex.model.dao;

import org.example.pokemonminigameex.model.dto.PokeUser;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class PokeUserMySQLRepository extends MySQLRepository {
    Logger logger = Logger.getLogger(JDBCRepository.class.getName());

    public PokeUserMySQLRepository() throws Exception {
        super();
        logger.info("정상적으로 생성되었습니다");
    }

    public void createPokeUser(PokeUser pokeUser) {

    }
}
