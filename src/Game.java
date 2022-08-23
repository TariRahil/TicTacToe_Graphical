import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game(){
        this.add(new Graphic());
        this.setTitle("Tic Tac Toe");
        this .pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
