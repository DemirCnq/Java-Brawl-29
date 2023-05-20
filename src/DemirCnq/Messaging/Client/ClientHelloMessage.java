package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.LoginOKMessage;
import DemirCnq.Messaging.Server.ServerHelloMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHelloMessage extends PiranhaMessage {
    public Player player;
    public ClientHelloMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {
        this.bs.readInt();
        this.bs.readInt(); // key version
        this.bs.readInt();
        this.bs.readString(); // sha
        this.bs.readInt();
        this.bs.readInt();
    }
    public void exacute() throws IOException {
        new ServerHelloMessage(20100, player.os).encode();
    }
}
