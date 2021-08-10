package bg.cnsys.battleships.layout;

public class Ship {
    private String name;
    private int size;
    private int lives;
    private int dLives = 8;
    private int bLives = 5;
     int shipsOnField=3;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.lives = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    /**
     * Field dLives,bLives counts the ships lives.
     */
    public void hit() {
        if (name == "Destroyer") {
            System.out.print(" Good shot! The " + getName() + " was hit ");
            bLives--;
        } else if (name == "Battleship") {
            System.out.print(" Good shot! The " + getName() + " was hit ");
            bLives--;
        }
        if (dLives == 4) {
            System.out.println("and was sunken to the bottom.");
            shipsOnField--;
        } else if (dLives == 0) {
            System.out.println("All Destroyer ships ware sunk! ");
            shipsOnField--;
        }
        if (bLives == 0) {
            System.out.println("and was sunken to the bottom.");
            shipsOnField--;
        }
    }

    public void hitUs(){
        if(this.name.equals("Destroyer") ){
            System.out.print(" Good shot! The " + getName() + " was hit ");
            lives--;
            if (lives==0){
                System.out.println("and was sunken to the bottom.");
            }
            else if (lives==-1){
                lives=4-1;
            }
        }
        else if (this.name.equals("Battleship")){
            System.out.print(" Good shot! The " + getName() + " was hit ");
            lives--;
            if (lives==0){
                System.out.println("and was sunken to the bottom.");
            }
        }
    }
}