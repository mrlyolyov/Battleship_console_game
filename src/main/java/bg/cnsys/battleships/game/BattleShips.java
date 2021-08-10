package bg.cnsys.battleships.game;

import bg.cnsys.battleships.layout.Player;

public class BattleShips {
    private Player[] players;

    public BattleShips() {
        players = new Player[]{new Player(1), new Player(2)};
    }

    public void start() {
        System.out.println("==================*BATTLESHIPS*==================");
        char p1Field[][] = players[0].putShipsForPlayer();
        char p2Field[][] = players[1].putShipsForPlayer();
        players[0].shoot(players[1],p1Field,p2Field);
        //TODO we can encapsulate this check in player
        //TODO here may be is more appropriate to invoke "shoot" method, getWinner is not appropriate name
    }
}
