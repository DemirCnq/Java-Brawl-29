package DemirCnq.Logic.Battle;

import DemirCnq.DataStream.BitStream;
import DemirCnq.Logic.Player;
import DemirCnq.Messaging.Server.BattleEndMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogicGameObjectManagerServer {
    public BitStream bit;
    public  Player player;
    public List<BattlePlayer> players;
    public int tick;
    public LogicGameObjectManagerServer(BitStream bit, Player player, int tick) {
        this.bit = bit;
        this.player = player;
        this.tick = tick;
    }
    public void update() throws IOException {
        int[] xSpawns = new int[] {228, 5000, 5000, 228 };
        int[] ySpawns = new int[] {1337, 7000, 1337, 7000 };
        player.x = xSpawns[0];
        player.y = ySpawns[0];
        if (this.tick >= 1000){
            player.roundstate = 1;

            BattleEndMessage be = new BattleEndMessage(23456,this.player.os);
            be.gamemode = 2;
            //be.players = players;
            be.encode();
        }
    }
    public void encode(int idx) {
        this.bit.WritePositiveInt(1000000 + idx, 21); // а кто.
        this.bit.WritePositiveInt(0, 1);
        this.bit.WriteInt(this.player.roundstate, 4); // round state. -1 - round not finished yet. 0 - lost. 1 - win. 2 - draw
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);

        this.bit.WritePositiveInt(0, 6); // 9
        this.bit.WritePositiveInt(0, 6);
        this.bit.WritePositiveInt(0, 6); // 39
        this.bit.WritePositiveInt(0, 6); // 19

        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(1, 1);

        for (int i = 0; i < 1; i++)
        {
            this.bit.WriteBoolean(true);
            this.bit.WritePositiveInt(0, 4);
            this.bit.WritePositiveInt(0, 1);
            if (i == idx)
            {
                this.bit.WriteBoolean(this.player.usepin);
                if (this.player.usepin)
                {
                    this.bit.WriteInt(this.player.pin, 3);
                    this.bit.WritePositiveInt(tick, 14);
                }
                this.bit.WritePositiveInt(4000, 12);
                this.bit.WritePositiveInt(0, 1);
            }
            this.bit.WritePositiveInt(0, 1);
            this.bit.WritePositiveInt(1, 1);
        }

        this.bit.WritePositiveInt(1, 4); // players left

        for (int i = 0; i < 1; i++)
        {
            this.bit.WritePositiveInt(0, 1);
            this.bit.WritePositiveInt(0, 1);
        }

        this.bit.WritePositiveInt(1, 8); // GameObjects Count

        this.bit.WritePositiveInt(16, 5); //ilk karakter
        this.bit.WritePositiveInt(0, 9);

        this.bit.WritePositiveInt(0, 14);

        this.bit.WritePositiveVInt(this.player.x, 4);
        this.bit.WritePositiveVInt(this.player.y, 4);
        this.bit.WritePositiveVInt(0, 3);
        this.bit.WritePositiveVInt(0, 4);

        this.bit.WritePositiveInt(10, 4); //visiblity

        //control removed
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);

        this.bit.WritePositiveInt(0, 3);//attack state
        this.bit.WritePositiveInt(0, 1); //coctail used
        this.bit.WriteInt(63, 6);

        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);

        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 2);

        this.bit.WritePositiveVInt(3200, 4); //hit point
        this.bit.WritePositiveVInt(3200, 4); // max hp

        this.bit.WritePositiveVIntMax255OftenZero(99); //items collected

        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(0, 1);

        this.bit.WritePositiveInt(0, 1); //ExecutingCharge

        this.bit.WritePositiveInt(0, 1); //IsPlayerControlRemoved

        this.bit.WritePositiveInt(0, 4); //IsPlayerControlRemoved

        this.bit.WritePositiveInt(0, 2);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 9);

        //IsPlayerControlRemoved
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(0, 1);

        this.bit.WritePositiveInt(0, 5);

        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(3000, 12);
        this.bit.WritePositiveInt(1, 1);
        this.bit.WritePositiveInt(0, 1);
        this.bit.WritePositiveInt(1, 1);


    }
}
