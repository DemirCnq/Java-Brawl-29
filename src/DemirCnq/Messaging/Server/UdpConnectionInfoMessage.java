package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.LogicLong;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class UdpConnectionInfoMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public UdpConnectionInfoMessage(int type, DataOutputStream writer){
        super(new byte[50]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.bs.writeVInt(9339);
        this.bs.writeString("192.168.1.103");

        this.bs.writeInt(10);
        new LogicLong(0,1).encode(this.bs);
        this.bs.writeShort(0);

        this.bs.writeInt(0);

        this.send(writer, this.type);
    }

}
