import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Graphic  extends JPanel implements ActionListener {
    static final int WIDTH = 700;
    static final int HEIGHT = 700;
    static final String X = "x";
    static final String O = "O ";
    boolean isFirstPlayer;
    final JButton[] cells = new JButton[9];

    public Graphic() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridLayout(3,3));

        for (int i = 0; i < 9 ; i++){
            cells[i] = new JButton();
            cells[i].setFont(new Font("Arial", Font.BOLD, 125));
            cells[i].setFocusable(false);
            cells[i].addActionListener(this);
            this.add(cells[i]);
        }

        isFirstPlayer = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == cells[i])
                if (cells[i].getText().isEmpty())
                    if (isFirstPlayer) {
                        cells[i].setForeground(Color.BLACK);
                        cells[i].setText(X);
                        isFirstPlayer = false;
                    } else {
                        cells[i].setForeground(Color.BLUE);
                        cells[i].setText(O);
                        isFirstPlayer = true;
                    }
        }

        checkState();
    }

    public void checkState(){
        if (checkMark(X))
            return;

        if (checkMark(O))
            return;

        if (checkDraw())
            return;

    }

    public boolean checkDraw(){
        int i = 0;
        while (!cells[i].getText().isEmpty()){
            if (i == cells.length - 1){
                Arrays.stream(cells)
                        .forEach(c -> c.setEnabled(false));
                break;
            }
            i++;
        }
        return i == cells.length - 1;
    }

    private boolean checkMark(String mark) {
        boolean isDone = false;

        //Horizontal
        isDone = checkDirection(0, 1, 2, mark);
        if (!isDone) isDone = checkDirection(3, 4, 5, mark);
        if (!isDone) isDone = checkDirection(6, 7, 8, mark);

        //Vertical
        if (!isDone) isDone = checkDirection(0, 3, 6, mark);
        if (!isDone) isDone = checkDirection(1, 4, 7, mark);
        if (!isDone) isDone = checkDirection(2, 5, 8, mark);

        //cross
        if (!isDone) isDone = checkDirection(0, 4, 8, mark);
        if (!isDone) isDone = checkDirection(2, 4, 6, mark);

        return isDone;
    }

    private boolean checkDirection(int posA, int posB, int posC, String mark) {
        if (cells[posA].getText().equals(mark) &&
            cells[posB].getText().equals(mark) &&
            cells[posC].getText().equals(mark)){
            setWinner(posA, posB, posC);
            return true;
        }

        return false;
    }

    private void setWinner(int posA, int posB, int posC) {
        cells[posA].setBackground(Color.GREEN);
        cells[posB].setBackground(Color.GREEN);
        cells[posC].setBackground(Color.GREEN);

        Arrays.stream(cells)
                .forEach(c -> c.setEnabled(false));
    }
}
