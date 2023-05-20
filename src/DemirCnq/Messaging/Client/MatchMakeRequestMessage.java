package DemirCnq.Messaging.Client;

import DemirCnq.Logic.Battle.Matchmaking;
import DemirCnq.Logic.Player;

import DemirCnq.Messaging.PiranhaMessage;
import DemirCnq.Messaging.Server.MatchMakingStatusMessage;

import java.io.IOException;
import java.net.Socket;

public class MatchMakeRequestMessage extends PiranhaMessage {
    public int selectedbrawler;
    public Player player;
    public MatchMakeRequestMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
    }
    public void  decode() {
        this.bs.readVInt();
        this.selectedbrawler = this.bs.readVInt();
        System.out.println("Selected Brawler: " + this.selectedbrawler);
    }
    public void exacute() throws IOException {
        //new UdpConnectionInfoMessage(24112,writer).encode();
        if (this.check()) {
            MatchMakingStatusMessage mms = new MatchMakingStatusMessage(20405, this.player.os);
            mms.mmTimer = 50;
            mms.PlayersFound = 1;
            mms.MaxPlayers = 1;
            mms.encode();

            new Matchmaking(player);
        }
    }

    public boolean check() {
        if (this.selectedbrawler < 0) return false;
        return true;
    }
}
