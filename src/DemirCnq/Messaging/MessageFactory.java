package DemirCnq.Messaging;

import DemirCnq.Logic.Player;
import DemirCnq.Messaging.Client.*;
import DemirCnq.Messaging.Server.LobbyInfoMessage;

import java.io.IOException;

public class MessageFactory {
    public void createMessageByType(int type, byte[] payload, Player player) throws IOException {
        switch(type) {
            case 10100:
                ClientHelloMessage ch = new ClientHelloMessage(payload,player);
                ch.decode();
                ch.exacute();
                break;
            case 10101:
                LoginMessage l = new LoginMessage(payload, player);
                l.decode();
                l.exacute();
                break;
            case 10108:
                KeepAliveMessage ke = new KeepAliveMessage(payload,player);
                ke.decode();
                ke.exacute();
                break;
            case 10177:
                ClientInfoMessage ci = new ClientInfoMessage(payload, player);
                ci.decode();
                ci.exacute();
                break;
            case 10555:
                ClientInputMessage cim = new ClientInputMessage(payload, player);
                cim.decode();
                cim.exacute();
                break;
            case 14103:
                MatchMakeRequestMessage mm = new MatchMakeRequestMessage(payload, player);
                mm.decode();
                mm.exacute();
                break;
            case 14106:
                CancelMatchmakingMessage cmm = new CancelMatchmakingMessage(payload, player);
                cmm.decode();
                cmm.exacute();
                break;
            default:
                System.out.println("Unexpected Message: " + type);
                LobbyInfoMessage lim = new LobbyInfoMessage(23457, player.os);
        }
    }
}
