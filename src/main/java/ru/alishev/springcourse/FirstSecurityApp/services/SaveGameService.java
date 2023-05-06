package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Saved;
import ru.alishev.springcourse.FirstSecurityApp.repositories.SavedRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaveGameService {

    final SavedRepository savedRepository;

    public void saveGame(String[][] board, int userId,int score,int level){

        String field = Arrays.stream(board)
                .map(row -> String.join("", row))
                .collect(Collectors.joining());

        Saved saved = new Saved();
        if(savedRepository.findByUserId(userId) != null) {
            saved = savedRepository.findByUserId(userId);
            saved.setField(field);
        }else{
            saved.setUserId(userId);
            saved.setField(field);
        }
        saved.setLevel(level);
        saved.setScores(score);
        saved.setRows(board.length);
        saved.setCols(board[0].length);
        savedRepository.save(saved);
    }

    public String[][] getLastSavedGame(int id){

        Saved saved = savedRepository.findByUserId(id);
        if(saved != null){
            String[][] field = new String[saved.getRows()][saved.getCols()];
            int k = 0;
            for(int i = 0; i < saved.getRows(); i++){
                for(int j = 0; j < saved.getCols(); j++)
                {
                    field[i][j] = String.valueOf(saved.getField().charAt(k));
                    k++;
                }
            }
            return field;
        }
        return null;
    }

    public void deleteGame(String id) {
        Saved saved = savedRepository.findByUserId(Integer.parseInt(id));
        savedRepository.delete(saved);
    }

    public Saved getSavedByUserId(String id){
        return savedRepository.findByUserId(Integer.parseInt(id));
    }

}
