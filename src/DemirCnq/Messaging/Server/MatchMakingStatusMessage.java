package DemirCnq.Messaging.Server;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MatchMakingStatusMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int mmTimer;
    public int PlayersFound;
    public int MaxPlayers;
    public MatchMakingStatusMessage(int type, DataOutputStream writer){
        super(new byte[250]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {
        this.bs.writeInt(this.mmTimer);
        this.bs.writeInt(this.PlayersFound);
        this.bs.writeInt(this.MaxPlayers);
        this.bs.writeInt(0);
        this.bs.writeInt(0);

        this.bs.writeBoolean(true); // use timer

        this.send(writer,this.type);
    }


}
