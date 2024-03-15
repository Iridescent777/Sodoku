package sudoku;

import javax.swing.*;
import java.awt.*;

public class UnfilledCounter extends JPanel{
    private static final long serialVersionUID = 1L; // to prevent serial warning

    private JLabel label;
    private int count;

    public UnfilledCounter() {
        label = new JLabel(String.format("Unfilled Count: %d", count));
        add(label);
        label.setFont(new Font("Verdana", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
    }

    public void increment() {
        count = 0;
        for(int i = 0;i<GameBoardPanel.GRID_SIZE;i++){
            for(int j =0;j<GameBoardPanel.GRID_SIZE;j++){
                if(GameBoardPanel.cells[i][j].status == CellStatus.TO_GUESS){
                    count++;
                }
            }
        }
        /*if(count != chances_max) { //不要讓人家發現可以顯示錯誤次數高於上限
            count++;
        }*/
        //label.setText(String.format("Mistake Count: %d/%d", count, chances_max));
        label.setText(String.format("Unfilled Count: %d", count));


    }
}
