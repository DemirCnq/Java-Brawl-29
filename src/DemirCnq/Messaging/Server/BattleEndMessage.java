package DemirCnq.Messaging.Server;

import DemirCnq.Logic.Battle.BattlePlayer;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class BattleEndMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public int gamemode;
    public List<BattlePlayer> players;
    public BattleEndMessage(int type, DataOutputStream writer){
        super(new byte[1000]);
        this.type = type;
        this.writer = writer;
        //players.add(new BattlePlayer(1,"DemirCnq"));
    }
    public void encode() throws IOException {
        //thx to lwitchy for his some parts from his batteEnd
        this.bs.writeVInt(gamemode); //2 = Showdown, 3 = Robo Rumble, 4 = Big Game, 5 = Duo Showdown, 6 = Boss Fight/Super City Rampage. Else is 3vs3
        this.bs.writeVInt(1); //# Result (Victory/Defeat/Draw/Rank Score)
        this.bs.writeVInt(999); // Tokens Gained
        this.bs.writeVInt(999); //Trophies Result
        this.bs.writeVInt(0); //Power Play Points Gained
        this.bs.writeVInt(0); //Doubled Tokens
        this.bs.writeVInt(0); //Double Token Event
        this.bs.writeVInt(0); //Token Doubler Remaining
        this.bs.writeVInt(0); //Robo Rumble/Boss Fight/Super City Rampage Level Passed
        this.bs.writeVInt(0); //Epic Win Power Play Points Gained
        this.bs.writeVInt(0); //Championship Level Passed
        this.bs.writeVInt(0); //Challenge Reward Type (0 = Star Points, 1 = Star Tokens)
        this.bs.writeVInt(0); //Challenge Reward Ammount
        this.bs.writeVInt(0); //Championship Losses Left
        this.bs.writeVInt(0); //Championship Maximun Losses
        this.bs.writeVInt(0); //Coin Shower Event
        this.bs.writeVInt(0); //Underdog Trophies
        this.bs.writeByte((byte)16); // Battle Result Type ((-16)-(-1) = Power Play Battle End, 0-15 = Practice and Championship Battle End, 16-31 = Matchmaking Battle End, 32-47 = Friendly Game Battle End, 48-63  = Spectate and Replay Battle End, 64-79 = Championship Battle End)
        this.bs.writeVInt(-1); // Championship Challenge Type
        this.bs.writeVInt(1); // Championship Cleared

        this.bs.writeVInt(1);
        for(int i=0;i<1;i++){
            this.bs.writeVInt(i); //Player Team and Star Player Type

            this.helper.writeDataReference(this.bs,16,0);

            this.bs.writeVInt(0); // Skin DataRef

            this.bs.writeVInt(0); // brawler trophies
            this.bs.writeVInt(0); // power play related
            this.bs.writeVInt(1); // power level brawler

            this.bs.writeBoolean(true);
            this.bs.writeInt(0);
            this.bs.writeInt(1);

            this.bs.writeString("DemirCnq");
            this.bs.writeVInt(100); // exp
            this.bs.writeVInt(28000000);
            this.bs.writeVInt(43000000 + 1);
            this.bs.writeVInt(42000000 + 1); // bpnamecolor
        }

        this.bs.writeVInt(2); // exp array
        {
            this.bs.writeVInt(0);
            this.bs.writeVInt(999);
            this.bs.writeVInt(8);
            this.bs.writeVInt(0);
        }

        this.bs.writeVInt(0); // bonus array

        this.bs.writeVInt(2); // bars array
        {
            this.bs.writeVInt(1);
            this.bs.writeVInt(999);
            this.bs.writeVInt(999);

            this.bs.writeVInt(5);
            this.bs.writeVInt(999);
            this.bs.writeVInt(999);
        }

        this.bs.writeVInt(28);
        this.bs.writeVInt(0);

        this.bs.writeBoolean(true); // Play Again

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.send(writer, this.type);
    }

}
