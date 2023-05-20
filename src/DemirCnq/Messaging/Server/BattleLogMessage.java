package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.LogicLong;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class BattleLogMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int fps;
    public BattleLogMessage(int type, DataOutputStream writer){
        super(new byte[1000]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.bs.writeBoolean(true);

        this.bs.writeVInt(1); // Count
        for (var y = 0; y < 1; y++) {
            //sub_AC0B0
            this.bs.writeVInt(0);
            this.bs.writeVInt(0);    // Time When Battle Log Entry Was Created
            this.bs.writeVInt(1);    // Battle Log Type (1 = Normal, 2 = Crash, 3 = Survived for <time>,
            this.bs.writeVInt(8);    // Trophies Result
            this.bs.writeVInt(120); // Battle Time
            this.bs.writeByte((byte) 0);   // Type [0 = Power Play, 1 = Friendly, 2 = Championship]
            this.helper.writeDataReference(this.bs, 15, 7); // Map SCID
            this.bs.writeVInt(0); // Victory/Defeat/Draw
            this.bs.writeVInt(0); // ???

            this.bs.writeInt(0);
            this.bs.writeInt(0);

            this.bs.writeVInt(0);
            this.bs.writeByte((byte) 0);

            this.bs.writeVInt(6);  // array sub_55641C
            for (var i = 0; i < 6; i++) {
                this.bs.writeVInt(i);
                new LogicLong(0, i).encode(this.bs);
                this.bs.writeVInt(0);
                this.bs.writeBoolean(true);
                this.helper.writeDataReference(this.bs, 16, 0); // Player Brawler
                this.bs.writeVInt(0);
                this.bs.writeVInt(0);
                this.bs.writeVInt(0);

                //sub_57FBC4
                this.bs.writeString("Cenk"); // Player Name
                this.bs.writeVInt(100);
                this.bs.writeVInt(28000000);// Player Profile Icon
                this.bs.writeVInt(43000000);// Player Name Color
                this.bs.writeVInt(-1);
                //player array end
            }


            this.bs.writeVInt(0);
            this.bs.writeByte((byte) 0);
            this.bs.writeVInt(0);
            this.bs.writeByte((byte) 0);
            this.bs.writeVInt(0);
        }

        this.send(writer, this.type);
    }

}
