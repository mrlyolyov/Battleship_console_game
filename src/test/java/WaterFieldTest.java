import bg.cnsys.battleships.layout.Ship;
import bg.cnsys.battleships.layout.WaterField;
import org.junit.Test;
import static org.junit.Assert.*;
    public class WaterFieldTest {

        @Test
        public void canPlace() {
            Ship ship = new Ship("Destroyer", 4);
            WaterField waterField = new WaterField();
            boolean can = waterField.canPlace(ship, 2, 4, 1);
            assertEquals(false, can);
        }

        @Test
        public void placeTheShips() {
            Ship ship = new Ship("Destroyer", 4);
            Ship ship2 = new Ship("Battleship", 5);
            WaterField waterField = new WaterField();
            char check[][];
            check = waterField.placeTheShips(ship, 2, 2, 2);
            assertEquals(check[2][2], 'D');
            assertEquals(check[3][2], 'D');
            assertEquals(check[4][2], 'D');
            assertEquals(check[5][2], 'D');
            assertEquals(check[1][2], 0);
            check = waterField.placeTheShips(ship2, 4, 4, 3);
            assertEquals(check[4][4], 'B');
            assertEquals(check[4][3], 'B');
            assertEquals(check[4][2], 'B');
            assertEquals(check[4][1], 'B');
            assertEquals(check[4][0], 'B');
        }

        @Test
        public void place() {
            WaterField game = new WaterField();
            char test[][] = game.place();
            int check = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (test[i][j] == 'B' || test[i][j] == 'D')
                        check++;
                }
            }
            assertEquals(13, check);
        }
    }