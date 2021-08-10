package bg.cnsys.battleships.layout;

import java.util.Random;

public class WaterField {
    public final char[][] map = new char[10][10];

    //TODO why board letters field is here in Player this is responsibility of WaterField
    final char[] BOARD_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    /**
     * This method is used to create the ships objects which are then used to be placed on the board with random direction and spots.
     *
     * @return multidimensional array filled with char literals and 3 ship objects.
     */
    public char[][] place() {
        Ship[] ships = new Ship[3];
        Ship Battleship = new Ship("Battleship", 5);
        Ship Destroyer = new Ship("Destroyer", 4);
        ships[0] = Battleship;
        ships[1] = Destroyer;
        ships[2] = Destroyer;

        for (int j = 0; j < 10; j++)
            for (int k = 0; k < 10; k++)
                map[j][k] = '~';

        Random random = new Random();
        for (int i = ships.length - 1; i >= 0; i--) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                if (map[row][col] == '~') {
                    int direction = random.nextInt(4);
                    // 0 = North; 1 = East; 2 = South; 3 = West;
                    if (canPlace(ships[i], row, col, direction)) {
                        placeTheShips(ships[i], row, col, direction);
                        placed = true;
                    }
                }
            }
        }
        return map;
    }

    /**
     * This method is used to place three ships on the board.
     *
     * @param ship      is used to take the ship size to indicate the number of spots needed.
     * @param row       random integer value between(1-10)
     * @param col       random integer value between(1-10)
     * @param direction random value between(0-3) to indicate the direction for ship placement.
     * @return char[][] with 3 ships on the Grid.
     */
    public char[][] placeTheShips(Ship ship, int row, int col, int direction) {
        int size = ship.getSize();
        if (size == 5) {
            switch (direction) {
                case 0: // North
                    for (int i = row; i >= row - (size - 1); i--)
                        map[i][col] = 'B';
                    break;

                case 1: // East
                    for (int i = col; i <= col + (size - 1); i++)
                        map[row][i] = 'B';
                    break;

                case 2: // South
                    for (int i = row; i <= row + (size - 1); i++)
                        map[i][col] = 'B';
                    break;

                case 3: // West
                    for (int i = col; i >= col - (size - 1); i--)
                        map[row][i] = 'B';
                    break;
            }
        } else if (size == 4) {
            switch (direction) {
                case 0: // North
                    for (int i = row; i >= row - (size - 1); i--)
                        map[i][col] = 'D';
                    break;

                case 1: // East
                    for (int i = col; i <= col + (size - 1); i++)
                        map[row][i] = 'D';
                    break;

                case 2: // South
                    for (int i = row; i <= row + (size - 1); i++)
                        map[i][col] = 'D';
                    break;

                case 3: // West
                    for (int i = col; i >= col - (size - 1); i--)
                        map[row][i] = 'D';
                    break;
            }
        }
        return map;
    }

    /**
     * This method is used to determine the direction of the ship placement taking a random and making sure the ships can touch but they don't overlap.
     *
     * @param ship      Uses a ship object to check it's size and see if the spots based on direction and starting point.
     * @param row       Takes integer value treating it as a row in the multidimensional array-map.
     * @param col       Takes integer value treating it as a column in the multidimensional array-map
     * @param direction integer value between(0-3) based on which the direction of ship placement is decided if the spot is free.
     * @return boolean checking the Grid if there are enough free spots based on the given @param.
     */
    public boolean canPlace(Ship ship, int row, int col, int direction) {
        int size = ship.getSize();
        boolean thereIsRoom = true;
        switch (direction) {
            case 0: // North
                if (row - (size - 1) < 0)
                    thereIsRoom = false;
                else
                    for (int i = row; i >= row - (size - 1) && thereIsRoom; i--)
                        thereIsRoom = thereIsRoom & (map[i][col] == '~');
                break;

            case 1: // East
                if (col + (size - 1) >= 10)
                    thereIsRoom = false;
                else
                    for (int i = col; i <= col + (size - 1) && thereIsRoom; i++)
                        thereIsRoom = thereIsRoom & (map[row][i] == '~');
                break;

            case 2: // South
                if (row + (size - 1) >= 10)
                    thereIsRoom = false;
                else
                    for (int i = row; i <= row + (size - 1) && thereIsRoom; i++)
                        thereIsRoom = thereIsRoom & (map[i][col] == '~');
                break;

            case 3: // West
                if (col - (size - 1) < 0)
                    thereIsRoom = false;
                else
                    for (int i = col; i >= col - (size - 1) && thereIsRoom; i--)
                        thereIsRoom = thereIsRoom & (map[row][i] == '~');
                break;
        }
        return thereIsRoom;
    }
    //TODO this is responsibility of WaterField

    /**
     * @param chars drawing the Grid using the multidimensional char array.
     */
    public void drawBoard(char[][] chars) {
        System.out.print("\t");

        for (int i = 0; i < 10; i++) {
            System.out.print(BOARD_LETTERS[i] + "\t");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < 10; j++) {
                System.out.print(chars[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Simply fills a board with chars(water).
     *
     * @return filled char[10][10] array.
     */
    public char[][] getPlayerBoard() {
        char[][] board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
        return board;
    }
}
