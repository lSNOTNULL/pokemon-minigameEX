package org.example.pokemonminigameex.controller;

import org.example.pokemonminigameex.model.dao.PokeUserMySQLRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    private final PokeUserMySQLRepository pokeUserMySQLRepository;

    public IndexController(PokeUserMySQLRepository pokeUserMySQLRepository) {
        this.pokeUserMySQLRepository = pokeUserMySQLRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
