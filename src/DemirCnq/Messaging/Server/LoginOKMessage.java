package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.LogicLong;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoginOKMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public LoginOKMessage(int type, DataOutputStream writer){
        super(new byte[250]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        new LogicLong(0,1).encode(this.bs);
        new LogicLong(0,1).encode(this.bs);

        this.bs.writeString("Token");
        this.bs.writeString("");
        this.bs.writeString("");

        this.bs.writeInt(0);
        this.bs.writeInt(0);
        this.bs.writeInt(0);

        this.bs.writeString("");

        this.bs.writeInt(0);
        this.bs.writeInt(0);
        this.bs.writeInt(0);

        this.bs.writeString("");
        this.bs.writeString("");
        this.bs.writeString("");

        this.bs.writeInt(0);

        this.bs.writeString("");
        this.bs.writeString("TR");
        this.bs.writeString("");

        this.bs.writeInt(1);

        this.bs.writeString("");
        this.bs.writeString("");
        this.bs.writeString("");

        this.bs.writeInt(0);
        this.bs.writeString("");
        this.bs.writeInt(1);

        this.send(writer,this.type);
    }

}
