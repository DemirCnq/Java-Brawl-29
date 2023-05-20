package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.LogicLong;
import DemirCnq.DataStream.LogicLong;
import DemirCnq.Logic.Battle.BattlePlayer;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartLoadingMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int mmTimer;
    public int PlayersFound;
    public int MaxPlayers;
    public List<BattlePlayer> players;
    public StartLoadingMessage(int type, DataOutputStream writer){
        super(new byte[1000]);
        this.type = type;
        this.writer = writer;
        players = new ArrayList<BattlePlayer>();
    }

    public void encode() throws IOException {
        this.bs.writeInt(1); // Game Mode Total Players
        this.bs.writeInt(0);
        this.bs.writeInt(0);

        // Logic Player Array
        this.bs.writeInt(1);// Players Count
        for (int i=0;i<1;i++) {
            new LogicLong(0, 1).encode(this.bs);

            this.bs.writeVInt(i); // Player Index

            this.bs.writeVInt(0); // Player Team
            this.bs.writeVInt(0);
            this.bs.writeInt(0); // ???

            this.helper.writeDataReference(this.bs, 16, 0); // Player Brawler

            this.helper.writeDataReference(this.bs, 29, 0); // Player Skin

            this.bs.writeBoolean(false); // Hero Upgrades

            this.bs.writeString("DemirCnq");
            this.bs.writeVInt(100); // exp
            this.bs.writeVInt(28000000);
            this.bs.writeVInt(43000000 + 1);
            this.bs.writeVInt(42000000 + 1); // bpnamecolor

            this.bs.writeBoolean(false);
            // Player Display Data End
            // Logic Player Array End
        }
        
        
        // Logic Vector Array
        this.bs.writeInt(0); // Array
        this.bs.writeInt(0); // Count

        this.bs.writeInt(0); // Unknown

        this.bs.writeVInt(0);
        this.bs.writeVInt(1);// Map Blocks
        this.bs.writeVInt(1); // Joystick Mode

        this.bs.writeBoolean(true); // Battle Hints

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.helper.writeDataReference(this.bs,15, 14); // Location ID

        this.bs.writeBoolean(false); // Battle Hints

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.send(this.writer,this.type);
    }

}
