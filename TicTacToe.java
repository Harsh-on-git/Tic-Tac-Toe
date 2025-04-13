import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {

    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textlabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardpanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameover = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        textlabel.setBackground(Color.darkGray);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Tic-Tac-Toe");
        textlabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textlabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardpanel.setLayout(new GridLayout(3, 3));
        boardpanel.setBackground(Color.darkGray);
        frame.add(boardpanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton title = new JButton();
                board[r][c] = title;
                boardpanel.add(title);

                title.setBackground(Color.darkGray);
                title.setForeground(Color.white);
                title.setFont(new Font("Arial", Font.BOLD, 120));
                title.setFocusable(false);

                title.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        if (gameover) {
                            return;
                        }
                        JButton title = (JButton) ae.getSource();
                        if (title.getText() == "") {

                            title.setText(currentPlayer);
                            turns++;
                            checkwinner();
                            if (!gameover) {

                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textlabel.setText(currentPlayer + " 's turn.");
                            }
                        }
                    }
                });

            }

        }

    }

    public static void main(String[] args) {
        TicTacToe tictak = new TicTacToe();
    }

    void checkwinner() {
        // horizontal

        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "")
                continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setwinner(board[r][i]);
                }

                gameover = true;
                return;

            }

        }

        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "")
                continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setwinner(board[i][c]);
                }

                gameover = true;
                return;

            }

        }

        // diagonal

        if (board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setwinner(board[i][i]);

            }
            gameover = true;
            return;
        }

        // anti diagonally

        if (board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() &&
                board[0][2].getText() != "") {

            setwinner(board[0][2]);
            setwinner(board[1][1]);
            setwinner(board[2][0]);
            gameover = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    settie(board[r][c]);
                }
            }
            gameover = true;
        }
    }

    void setwinner(JButton title) {
        title.setForeground(Color.green);
        title.setBackground(Color.GRAY);
        textlabel.setText(currentPlayer + " is the winner!");
    }

    void settie(JButton title) {
        title.setForeground(Color.ORANGE);
        title.setBackground(Color.gray);
        textlabel.setText("Tie!");
    }

}
