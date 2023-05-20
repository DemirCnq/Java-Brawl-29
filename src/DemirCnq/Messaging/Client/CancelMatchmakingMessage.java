package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.MatchmakingCancelledMessage;
import DemirCnq.Messaging.Server.ServerHelloMessage;


import java.io.IOException;

public class CancelMatchmakingMessage extends PiranhaMessage {
    public Player player;
    public CancelMatchmakingMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {

    }
    public void exacute() throws IOException {
        new MatchmakingCancelledMessage(20406, this.player.os).encode();
    }
}
