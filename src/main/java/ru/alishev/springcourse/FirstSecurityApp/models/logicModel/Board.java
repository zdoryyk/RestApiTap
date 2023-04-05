package ru.alishev.springcourse.FirstSecurityApp.models.logicModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.util.Tile;

import java.util.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {


    private String[][] array;

    public String[][] generateBoard(int columns, int rows) {

        List<Character> symbols = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            symbols.add((char) ('0' + i));
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if(c == 'I'){
                symbols.add('i');
            }else{
                symbols.add(c);
            }
        }


        this.array = new String[columns][rows];
        int symbolIndex = 0;
        int counter = 0;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                array[i][j] = "0";
            }
        }

        int row, col;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                do {
                    row = new Random().nextInt(100) % columns;
                    col = new Random().nextInt(100) % rows;
                } while (!array[row][col].equals("0"));
                 if(rows > 7) {
                     array[row][col] = String.valueOf(symbols.get(symbolIndex));
                     counter++;
                     if (counter >= 4) {
                         counter = 0;
                         symbols.remove(symbols.get(symbolIndex));
                         symbolIndex = new Random().nextInt(symbols.size());
                     }
                 }else{
                     array[row][col] = String.valueOf(symbols.get(symbolIndex));
                     counter++;
                     if(counter >=4){
                         counter = 0;
                         symbolIndex++;
                     }
                 }
            }
        }

        return array;
    }


    public boolean addInBoard(List<Tile> tiles){
        Tile tile1 = tiles.get(0);
        Tile tile2 = tiles.get(1);
        if(Objects.equals(this.array[tile1.getX()][tile1.getY()], " ")
        && Objects.equals(this.array[tile2.getX()][tile2.getY()], " "))
        {
            this.array[tile1.getX()][tile1.getY()] = tile1.getValue();
            this.array[tile2.getX()][tile2.getY()] = tile2.getValue();
            return true;
        }
        return false;
    }


    public boolean handleInput(String[][] array, List<Tile> tiles) {


        int size = array.length;
        int startRow = tiles.get(0).getX();
        int startCol = tiles.get(0).getY();
        int endRow   = tiles.get(1).getX();
        int endCol   = tiles.get(1).getY();

        String temp1 = array[startRow][startCol], temp2 = array[endRow][endCol];

        if(array[startRow][startCol].equals(array[endRow][endCol])) {
            array[startRow][startCol] = " ";
            array[endRow][endCol] = " ";
            if (isReachable(startRow, startCol, endRow, endCol)) {
                array[startRow][startCol] = " ";
                array[endRow][endCol] = " ";
                return true;
            } else {
                array[startRow][startCol] = temp1;
                array[endRow][endCol] = temp2;
            }
            if(startCol == endCol && (startCol == 0 || startCol == size -1)){
                array[startRow][startCol] = " ";
                array[endRow][endCol] = " ";
                return true;
            }else if(startRow == endRow && (startRow == 0 || startRow == size -1)){
                array[startRow][startCol] = " ";
                array[endRow][endCol] = " ";
                return true;
            }
        }
        return false;
    }


    private boolean isReachable(int startRow, int startCol, int endRow, int endCol) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startRow, startCol});

        boolean[][] visited = new boolean[array.length][array[0].length];
        visited[startRow][startCol] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            if (currRow == endRow && currCol == endCol) {
                return true;
            }

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                if (nextRow >= 0 && nextRow < array.length && nextCol >= 0 && nextCol < array[0].length
                        && !visited[nextRow][nextCol] && array[nextRow][nextCol].equals(" ")) {

                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return false;
    }

}
