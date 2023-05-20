package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.UdpConnectionInfoMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientInfoMessage extends PiranhaMessage {
    public Player player;
    public ClientInfoMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {
        System.out.println(this.bs.readString());
    }
    public void exacute() throws IOException {
        new UdpConnectionInfoMessage(24112, this.player.os).encode();
    }
}
