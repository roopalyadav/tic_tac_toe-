package tictactoe;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }

        char char_X = 'X';
        char char_O = 'O';
        char currentPlayer = char_X;

        String resultOfGame = "";

        while (true) {

            showField(field);
            System.out.print("Enter the coordinates: ");

            String userInput = scanner.nextLine();
            userInput = userInput.replace(" ", "");
            String str1 = userInput.substring(0, 1);
            String str2 = userInput.substring(1, 2);

            System.out.printf("%s %s\n", str1, str2);

            if (!isInteger(str1, str2)) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x = Integer.parseInt(str1);
            int y = Integer.parseInt(str2);

            if (!isValidRange(x, y)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int i = 3 - y;
            int j = x - 1;

            if (isEmptyCell(field, i, j)) {
                field[i][j] = currentPlayer;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            resultOfGame = checkGame(field);
            if (resultOfGame.length() != 0) {
                showField(field);
                System.out.println(resultOfGame);
                break;
            } else {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
    }

    public static char switchPlayer(char currentPlayer) {
        if (currentPlayer == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    public static String checkGame(char[][] field) {

        String result = "";

        int countOfX = countOfLetter(field, 'X');
        int countOfO = countOfLetter(field, 'O');
        boolean check_X = checkWin(field, 'X');
        boolean check_O = checkWin(field, 'O');
        boolean tableIsFull = isTableFull(field);

        if (check_X && check_O || (countOfX - countOfO >= 2 || countOfO - countOfX >= 2)) {
            result = "Impossible";
        } else if (!check_X && !check_O && tableIsFull) {
            result = "Draw";
        } else if (check_X && !check_O) {
            result = "X wins";
        } else if (!check_X && check_O) {
            result = "O wins";
//        } else if (!check_X && !check_O && !tableIsFull) {
//            result = "Game not finished";
        }
        return result;
    }

    public static boolean isInteger(String str1, String str2) {

        if (!(str1.contains("1") || str1.contains("2") || str1.contains("3")) ||
                !(str2.contains("1") || str2.contains("2") || str2.contains("3"))) {
            System.out.println("You should enter numbers!");
            return false;
        }
        return true;
    }

    public static boolean isValidRange(int x, int y) {

        if (x < 0 || y < 0 || x > 3 || y > 3) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyCell(char[][] field, int x, int y) {

        if (field[x][y] != ' ') {
            return false;
        }
        return true;
    }

    public static void showField (char[][] field) {

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c %c %c |\n", field[i][0], field[i][1], field[i][2]);
        }
        System.out.println("---------");
    }

    public static boolean checkWin(char[][] field, char letter) {
        for (int i = 0; i < 3; i++) {
            if ((field[i][0] == letter && field[i][1] == letter && field[i][2] == letter) ||
                    (field[0][i] == letter && field[1][i] == letter && field[2][i] == letter) ||
                    (field[0][0] == letter && field[1][1] == letter && field[2][2] == letter) ||
                    (field[2][0] == letter && field[1][1] == letter && field[0][2] == letter)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTableFull(char[][] field) {

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (field[i][j] == ' ') {
                    return false;
                }
        return true;
    }

    public static int countOfLetter(char[][] field, char letter) {

        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (field[i][j] == letter) {
                    count++;
                }
        return count;
    }
}
