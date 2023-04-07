package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.logicModel.Board;
import ru.alishev.springcourse.FirstSecurityApp.util.Tile;

import java.util.Arrays;
import java.util.List;

@Service
@Data
public class GameService {

    private final Board board;

    public boolean handleInput(List<Tile> tiles){
        int startRow = tiles.get(0).getX();
        int startCol = tiles.get(0).getY();
        int endRow   = tiles.get(1).getX();
        int endCol   = tiles.get(1).getY();
        return board.handleInput(startRow,startCol,endRow,endCol);
    }

    public boolean addInBoard(List<Tile> tiles){
        Tile tile1 = tiles.get(0);
        Tile tile2 = tiles.get(1);
        return board.addInBoard(tile1,tile2);
    }
}
