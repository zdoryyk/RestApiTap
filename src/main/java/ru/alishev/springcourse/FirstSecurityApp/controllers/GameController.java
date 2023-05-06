package ru.alishev.springcourse.FirstSecurityApp.controllers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.Saved;
import ru.alishev.springcourse.FirstSecurityApp.models.logicModel.Board;
import ru.alishev.springcourse.FirstSecurityApp.services.GameService;
import ru.alishev.springcourse.FirstSecurityApp.services.SaveGameService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;
import ru.alishev.springcourse.FirstSecurityApp.util.Size;
import ru.alishev.springcourse.FirstSecurityApp.util.StringArrayWrapper;
import ru.alishev.springcourse.FirstSecurityApp.util.Tile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

     final Board board;
     final GameService service;
     final SaveGameService saveGameService;


    @PostMapping("/generate")
    public String[][] generateBoard(@RequestBody Size size){
        return this.board.generateBoard(size.getColumns(),size.getRows());
    }

    @PostMapping("/handle-input")
    public ResponseEntity<?> handleInput(@RequestBody Tile[] tiles){
        if(service.handleInput(new ArrayList<>(List.of(tiles[0],tiles[1])))){
           return ResponseEntity.ok(new Response(HttpStatus.CONTINUE,"OK"));
        }
        return ResponseEntity.ok(new Response(HttpStatus.FORBIDDEN,"FORBIDDEN"));
    }

    @PutMapping("/return-tile")
    public ResponseEntity<?> returnTile(@RequestBody Tile[] tile){
        if(this.service.addInBoard(List.of(tile[0],tile[1]))){
            return ResponseEntity.ok(new Response(HttpStatus.CONTINUE,"OK"));
        }
        return ResponseEntity.ok(new Response(HttpStatus.FORBIDDEN,"FORBIDDEN"));
    }

    @PostMapping("/regenerate-field")
    public ResponseEntity<?> regenerateField(@RequestBody StringArrayWrapper stringArrayWrapper){
        board.reloadBoard(stringArrayWrapper.getArray());
        return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED,"ACCEPTED"));
    }

    @PutMapping("/save-game")
    public ResponseEntity<?> saveGame(@RequestBody savedGameRecord savedGame){
        saveGameService.saveGame(savedGame.array, savedGame.id,savedGame.score,savedGame.level);
        return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED,"ACCEPTED"));
    }

    @GetMapping("/saved-game-{id}")
    public returnLastGame getSavedGame(@PathVariable String id){

        Saved saved = saveGameService.getSavedByUserId(id);
        if(saved != null){
            return new returnLastGame(
                    saveGameService.getLastSavedGame(Integer.parseInt(id)),
                    saved.getScores(),
                    saved.getLevel());
        }
        return null;
    }

    @DeleteMapping("/delete-game-{id}")
    public ResponseEntity<?> deleteLastGame(@PathVariable String id){
        saveGameService.deleteGame(id);
        return ResponseEntity.ok().body(new Response(HttpStatus.ACCEPTED,"ACCEPTED"));
    }

    record savedGameRecord(String[][] array, int id,int score,int level){}


    record returnLastGame(String[][] array,int score,int level){}
}
