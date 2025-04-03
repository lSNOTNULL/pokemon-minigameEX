package org.example.pokemonminigameex.controller;

import jakarta.servlet.http.HttpSession;
import org.example.pokemonminigameex.model.dao.PokeUserMySQLRepository;
import org.example.pokemonminigameex.model.dto.PokeUser;
import org.example.pokemonminigameex.model.dto.PokeUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class IndexController {
    private final PokeUserMySQLRepository pokeUserMySQLRepository;
    private final Logger logger = Logger.getLogger(IndexController.class.getName());

    public IndexController(PokeUserMySQLRepository pokeUserMySQLRepository) {
        this.pokeUserMySQLRepository = pokeUserMySQLRepository;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute PokeUserDTO pokeUserDTO, HttpSession session, Model model) throws SQLException {
        // session : 사용자-웹브라우저 간 연결상태 유지(세션객체), 이후 요청에서도 로그인 유지
        // model : controller -> view로 데이터 전달 위해 사용
        logger.info("login");
        String pokeUserID = pokeUserMySQLRepository.getOnePokeUser(pokeUserDTO.username(),pokeUserDTO.password());
        if(pokeUserID != null) {
            session.setAttribute("pokeUserID", pokeUserID);
            model.addAttribute("pokeUserID", pokeUserID);
            logger.info("로그인한 계정 ID : " + pokeUserID);
            return "index";
        }else{
            model.getAttribute("아이디 또는 비밀번호 일치하지 않습니다");
            return "index";
        }
    }


    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute PokeUserDTO pokeUserDTO) throws SQLException {
        pokeUserMySQLRepository.createPokeUser(new PokeUser(
                UUID.randomUUID(),
                pokeUserDTO.username(),
                pokeUserDTO.password()
        ));
        return "redirect:/";
    }
}
