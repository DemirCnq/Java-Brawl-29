package DemirCnq.Messaging.Server;

import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class LobbyInfoMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int fps;
    public LobbyInfoMessage(int type, DataOutputStream writer){
        super(new byte[50]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.bs.writeVInt(9339);
        this.bs.writeString("");

        this.send(writer, this.type);
    }

}
