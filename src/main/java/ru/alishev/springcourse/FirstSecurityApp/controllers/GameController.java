package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.logicModel.Board;
import ru.alishev.springcourse.FirstSecurityApp.services.GameService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;
import ru.alishev.springcourse.FirstSecurityApp.util.Size;
import ru.alishev.springcourse.FirstSecurityApp.util.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    private final Board board;
    private String[][] field;

    private final GameService service;
    private Map<String, Tile> tiles;

    @Autowired
    public GameController(Board board, GameService service) {
        this.board = board;
        this.service = service;
    }


    @PostMapping("/generate")
    public String[][] generateBoard(@RequestBody Size size){
        field = this.board.generateBoard(size.getColumns(),size.getRows());
        return field;
    }

    @PostMapping("/handle-input")
    public ResponseEntity<?> handleInput(@RequestBody Tile[] tiles){
        if(service.handleInput(this.field, new ArrayList<>(List.of(tiles[0],tiles[1])))){
           return ResponseEntity.ok(new Response(HttpStatus.CONTINUE,"OK"));
        }
        return ResponseEntity.ok(new Response(HttpStatus.FORBIDDEN,"FORBIDDEN"));
    }

    @PutMapping("/return-tile")
    public ResponseEntity<?> returnTile(@RequestBody Tile[] tile){
        if(this.board.addInBoard(List.of(tile[0],tile[1]))){
            return ResponseEntity.ok(new Response(HttpStatus.CONTINUE,"OK"));
        }
        return ResponseEntity.ok(new Response(HttpStatus.FORBIDDEN,"FORBIDDEN"));
    }



}
