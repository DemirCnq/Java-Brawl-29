package DemirCnq.Messaging.Server;

import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class MatchmakingCancelledMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public MatchmakingCancelledMessage(int type, DataOutputStream writer){
        super(new byte[1]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.send(writer, this.type);
    }

}
