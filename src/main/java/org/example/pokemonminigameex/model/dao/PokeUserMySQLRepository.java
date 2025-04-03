package org.example.pokemonminigameex.model.dao;

import org.example.pokemonminigameex.model.dto.PokeUser;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class PokeUserMySQLRepository extends MySQLRepository {
    Logger logger = Logger.getLogger(JDBCRepository.class.getName());

    public void createPokeUser(PokeUser pokeUser) throws SQLException {
        logger.info("회원가입 시 username: [" + pokeUser.username() + "], password: [" + pokeUser.password() + "]");
//        try (Statement statement = connection.createStatement()){
////            statement.executeQuery XXX
//            // INSERT 는 executeUpdate() 사용
//             String query = "INSERT INTO poke_user (poke_user_id, username, password) VALUES ('%s', '%s', '%s')"
//                     .formatted(pokeUser.id(), pokeUser.username(), pokeUser.password());
        String query = "INSERT INTO poke_user (poke_user_id, username, password) VALUES (?,?,?)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, String.valueOf(pokeUser.id()));
        pstmt.setString(2, pokeUser.username());
        pstmt.setString(3, pokeUser.password());
        pstmt.execute();

        logger.info("정상 추가");

    }

    public PokeUserMySQLRepository() throws Exception {
        super();
        logger.info("정상적으로 생성되었습니다");
    }

    public String getOnePokeUser(String username, String password) throws SQLException {
        logger.info("로그인 시 username: [" + username + "], password: [" + password + "]");
        String query = "SELECT * FROM poke_user WHERE username=? and password=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            //try-with-resources 구문 1번
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 파라미터 설정은 쿼리 실행 전에 이뤄저야함.
            try (ResultSet rs = pstmt.executeQuery()) {
                // 파라미터 설정 된 후 따로 한번 더 해야함
                //try-with-resources 구문 2번
                if (rs.next()) { // boolean 값으로 0 1 으로 나온다.
                    return rs.getString("poke_user_id");
                } else {
                    logger.info("없는 유저");
                    return null;
                }
            } catch (SQLException e) {
                logger.severe(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}