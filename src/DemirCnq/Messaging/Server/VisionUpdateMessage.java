package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.BitStream;
import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class VisionUpdateMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int TicksGone;
    public int HandledInput;
    public int ViewersCount;
    public Player player;
    public BitStream visionbit;
    public VisionUpdateMessage(int type, DataOutputStream writer, Player player){
        super(new byte[50]);
        this.type = type;
        this.writer = writer;
        this.player = player;
    }
    public void encode() throws IOException {

        this.bs.writeVInt(TicksGone);
        this.bs.writeVInt(player.handledinput);
        this.bs.writeVInt(0);
        this.bs.writeVInt(TicksGone);

        this.bs.writeBoolean(true);


        this.bs.writeBytes(visionbit.buffer, visionbit.length);

        this.send(writer,this.type);
    }

}
