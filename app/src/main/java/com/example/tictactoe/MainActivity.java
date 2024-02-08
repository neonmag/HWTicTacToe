package com.example.tictactoe;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
public class MainActivity extends AppCompatActivity {

    private static boolean player1Turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridVal);
        gridLayout.setRowCount(3);
        gridLayout.setColumnCount(3);


        Button [][] buttons = new Button[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.setMargins(10, 10, 10, 10);
                layoutParams.width = 300;
                layoutParams.height = 300;
                buttons[i][j] = new Button(this);
                buttons[i][j].setTextSize(18);
                buttons[i][j].setGravity(Gravity.CENTER); // Выравнивание по центру
                buttons[i][j].setOnClickListener(view -> {
                    if (!((Button) view).getText().toString().equals("")) {
                        return;
                    }

                    if (player1Turn) {
                        ((Button) view).setText("X");
                    } else {
                        ((Button) view).setText("O");
                    }
                        if (checkForWin(buttons)) {
                        if (player1Turn) {
                            Toast.makeText(MainActivity.this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
                        }
                        resetBoard(buttons);
                   } else if (checkForDraw(buttons)) {
                       Toast.makeText(MainActivity.this, "It's a draw!", Toast.LENGTH_SHORT).show();
                       resetBoard(buttons);
                  } else {
                        player1Turn = !player1Turn;
                    }
                });
                buttons[i][j].setLayoutParams(layoutParams);
                gridLayout.addView(buttons[i][j]);
            }
        }
    }

    private boolean checkForWin(Button[][] buttons) {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        return field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("");
    }

    private boolean checkForDraw(Button[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard(Button[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        player1Turn = true;
    }
}