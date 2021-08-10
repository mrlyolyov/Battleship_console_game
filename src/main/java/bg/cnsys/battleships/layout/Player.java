package bg.cnsys.battleships.layout;

import java.util.Scanner;

public class Player {
    private int id;
    private WaterField gameField;
    private Scanner scanner;
    private int totalLivesLeft = 13;
    //TODO think how not to hard code this value about how many lives are left
    //TODO also what lives mean this field is used for number of cells for ships now for player's lives
    //TODO think how to refactor this
    private int shots = 0;
    private char inputCol;
    private int colRight;
    private int rowRight;
    Ship Destroyer = new Ship("Destroyer", 4);
    Ship Battleship = new Ship("Battleship", 5);
    private char[][] playerBoard;

    public int getTotalLivesLeft() {
        return totalLivesLeft;
    }

    public Player(int id) {
        this.id = id;
        this.gameField = new WaterField();
        this.scanner = new Scanner(System.in);
        playerBoard = gameField.getPlayerBoard();
    }

    public int getId() {
        return id;
    }

    /**
     * @return char[][] with random placed ships.
     */
    public char[][] putShipsForPlayer() {
        return gameField.place();
    }

    /**
     * Updates the board of players based on the coordinates given.
     *
     * @param chars updates the array based on the given console numbers.
     */

    public char[][] shootResults(char chars[][]) {
        boolean shootUntil = false;

        while (!shootUntil && totalLivesLeft > 0) {

            String checkedInput = getValidInput(chars);

            if (chars[getRow(checkedInput) - 1][getCol(checkedInput)] == 'D') {
                Destroyer.hit();
                totalLivesLeft--;
                chars[getRow(checkedInput) - 1][getCol(checkedInput)] = 'X';
                shots++;
                System.out.println("Nice Shooting");
                playerBoard[getRow(checkedInput) - 1][getCol(checkedInput)] = 'X';
                System.out.println();
                gameField.drawBoard(playerBoard);

            } else if (chars[getRow(checkedInput) - 1][getCol(checkedInput)] == 'B') {
                Battleship.hit();
                chars[getRow(checkedInput) - 1][getCol(checkedInput)] = 'X';
                playerBoard[getRow(checkedInput) - 1][getCol(checkedInput)] = 'X';
                System.out.println();
                System.out.println("============*Player " + this.getId() + " Board *============");
                gameField.drawBoard(playerBoard);
                shots++;
                totalLivesLeft--;

            } else if (chars[getRow(checkedInput) - 1][getCol(checkedInput)] == '~') {
                chars[getRow(checkedInput) - 1][getCol(checkedInput)] = '-';
                System.out.println("MISS");
                playerBoard[getRow(checkedInput) - 1][getCol(checkedInput)] = '-';
                shootUntil = true;
                shots++;
            }
        }

        return playerBoard;
    }

    /**
     * @param chars modifies the multidimensional array based on the user input.
     */
    public char[][] fireAt(char chars[][]) {
        System.out.println();
        return shootResults(chars);
    }

    public int getShots() {
        return shots;
    }

    /**
     * Produces the game scenario and determine the winner of the game.
     *
     * @param playerField is the multidimensional char array with the ships in it.
     */
    public void shoot(Player player2, char playerField[][], char playerField2[][]) {
        System.out.println();
        //TODO text for a user is not very appropriate please use the same as requirements. With this text user cannot know how to enter right coordinates
        //TODO also please show WaterField for a user it will be easier to understand what coordinates to enter and what happens with the game this is wrote in requirements in the game
        char board[][] = gameField.getPlayerBoard();
        gameField.drawBoard(board);
        do {
            System.out.print(" Alright Player " + this.getId() + " Enter coordinates (row, col), e.g. A5 =   ");
            board = this.fireAt(playerField);
            System.out.println("============*Player " + this.getId() + " Board *============");
            gameField.drawBoard(board);
            if (this.getTotalLivesLeft() == 0) {
                break;
            }

            System.out.print(" Alright Player " + player2.getId() + " Enter coordinates (row, col), e.g. A5 =   ");
            board = player2.fireAt(playerField2);
            System.out.println("============*Player " + player2.getId() + " Board *============");
            gameField.drawBoard(board);
            if (player2.getTotalLivesLeft() == 0) {
                break;
            }
        } while (this.getTotalLivesLeft() > 0 && player2.getTotalLivesLeft() > 0);
        if (this.totalLivesLeft == 0) {
            System.out.println("Well done player " + this.getId() + "! You completed the game in " + this.getShots() + " shots! ");

        } else if (player2.totalLivesLeft == 0) {
            System.out.println("Well done player " + player2.getId() + "! You completed the game in " + player2.getShots() + " shots! ");
        }
    }

    /**
     * Extracts the char off the String inputted by user and find the char position and determine the column for the attack.
     *
     * @param stringInput taking a valid String and finding the column index matching.
     * @return the index matching with the Strings first literal.
     */
    public int getCol(String stringInput) {
        String newString = stringInput.toUpperCase();
        if (newString != null && newString.length() > 0) {
            inputCol = newString.charAt(0);
            Character.toUpperCase(inputCol);
        }
        for (int i = 0; i < 10; i++) {
            if (inputCol == gameField.BOARD_LETTERS[i]) {
                colRight = i;
                break;
            }
        }
        return colRight;
    }

    /**
     * Extracts the number off the String input and parsing it to integer.
     *
     * @param stringInput taking a valid String and parsing the number from it.
     * @return The valid number which is then used to determine the row for the attack.
     */
    public int getRow(String stringInput) {
        for (String s : stringInput.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")) {
            try {
                rowRight = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return rowRight;
    }

    /**
     * Validates the user input making sure the attack coordinates match the board and will return proper String.
     *
     * @param chars takes the multidimensional char array to draw it and is used as a cheat in the game.
     * @return A Validated string ready for use as attacking coordinates from user input
     */
    public String getValidInput(char chars[][]) {
        String input;
        boolean contains;
        do {
            input = scanner.nextLine().toUpperCase();
            if (input.trim().equals("SHOW")) {
                System.out.println("============*CHEAT Activated*============");
                gameField.drawBoard(chars);
                input = scanner.next().toUpperCase();
            } else if (getRow(input) > 10 || getRow(input) <= 0) {
                System.out.println(" Enter coordinates (row, col), e.g. A5 = ");
                input = scanner.next().toUpperCase();
            }
        }
        while (getRow(input) > 10 || getRow(input) < 1);
        do {
            String newInput = input.replaceAll("[^A-Za-z]+", "");
            if ((new String(gameField.BOARD_LETTERS).contains(newInput.toUpperCase()))) {
                contains = true;
            } else {
                contains = false;
                System.out.println(" Enter coordinates (row, col), e.g. A5 = ");
                input = scanner.next().toUpperCase();
            }
        } while (!contains);
        return input;
    }
}
