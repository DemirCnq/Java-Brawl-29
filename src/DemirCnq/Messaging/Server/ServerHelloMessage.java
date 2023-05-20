package DemirCnq.Messaging.Server;

import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServerHelloMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public ServerHelloMessage(int type, DataOutputStream writer){
        super(new byte[50]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.bs.writeInt(24);

        for (int i = 0; i < 24; i++)
        {
            this.bs.writeByte((byte)255);
        }

        this.send(writer, this.type);
    }

}
