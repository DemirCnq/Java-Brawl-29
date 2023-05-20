package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.BattleLogMessage;
import DemirCnq.Messaging.Server.LoginOKMessage;
import DemirCnq.Messaging.Server.OwnHomeDataMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoginMessage extends PiranhaMessage {
    public Player player;
    public LoginMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {
        var id = this.bs.readLong();
        System.out.println(id.high);
        System.out.println(id.low);
        var token = this.bs.readString();
        System.out.println(token);
    }
    public void exacute() throws IOException {
        new LoginOKMessage(20104, this.player.os).encode();
        new OwnHomeDataMessage(24101, this.player.os).encode();
        //new BattleLogMessage(23458, this.player.os).encode();
    }
}
