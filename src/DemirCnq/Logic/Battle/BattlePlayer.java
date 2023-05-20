package DemirCnq.Logic.Battle;

import java.net.Socket;

public class BattlePlayer {
    public int id;
    public String name;
    public Socket con;
    public BattlePlayer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
