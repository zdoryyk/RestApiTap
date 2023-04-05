package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.logicModel.Board;
import ru.alishev.springcourse.FirstSecurityApp.util.Tile;

import java.util.List;

@Service
@Data
public class GameService {

    private final Board board;

    private String[][] array;


    public boolean handleInput(String[][] array, List<Tile> tiles){
        return board.handleInput(array, tiles);
    }

}
