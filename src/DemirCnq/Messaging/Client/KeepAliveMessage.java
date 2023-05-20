package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.KeepAliveOKMessage;
import DemirCnq.Messaging.Server.LobbyInfoMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class KeepAliveMessage extends PiranhaMessage {
    public Player player;

    public KeepAliveMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {

    }
    public void exacute() throws IOException {
        new KeepAliveOKMessage(20108, this.player.os).encode();

        LobbyInfoMessage lim = new LobbyInfoMessage(23457, this.player.os);
        //lim.ping = this.latency;
        lim.encode();
    }
}
