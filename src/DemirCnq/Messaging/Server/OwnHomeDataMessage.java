package DemirCnq.Messaging.Server;

import DemirCnq.DataStream.LogicLong;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;


public class OwnHomeDataMessage extends PiranhaMessage {
    public int type;
    public DataOutputStream writer;
    public OwnHomeDataMessage(int type, DataOutputStream writer){
        super(new byte[1000]);
        this.type = type;
        this.writer = writer;
    }
    public void encode() throws IOException {

        this.bs.writeVInt(2023 * 1000 + 7);
        this.bs.writeVInt(75555);

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(122);
        this.bs.writeVInt(0);

        this.helper.writeDataReference(this.bs,28,0);
        this.helper.writeDataReference(this.bs,43,0);

        this.bs.writeVInt(0); //played gamemodes

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0); //token doublers
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0);
        this.bs.writeVInt(1);

        this.bs.writeVInt(1);
        this.bs.writeVInt(0);

        this.bs.writeVInt(4);
        this.bs.writeVInt(2);
        this.bs.writeVInt(2);
        this.bs.writeVInt(2);

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0); //shop array

        this.bs.writeVInt(0);

        this.bs.writeVInt(0); //battle tokens
        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0); //tiketiki
        this.bs.writeVInt(0);

        this.helper.writeDataReference(this.bs,16, 0);

        this.bs.writeString("TR");
        this.bs.writeString("github.com/DemirCnq");

        this.bs.writeVInt(0); // rewards

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);
            /*this.bs.writeVInt(2);
            this.bs.writeVInt(34500);
            this.bs.writeVInt(1);
            this.bs.writeVInt(0);

            this.bs.writeVInt(0);*/

        this.bs.writeVInt(0); // powerplay season data

        this.bs.writeBoolean(true);
        this.bs.writeVInt(0);//logic quests

        this.bs.writeBoolean(true);
        this.bs.writeVInt(0); //emotes and thumbnails

        this.bs.writeVInt(2021228);
        this.bs.writeVInt(100);
        this.bs.writeVInt(10);
        this.bs.writeVInt(30);
        this.bs.writeVInt(3);
        this.bs.writeVInt(80);
        this.bs.writeVInt(10);
        this.bs.writeVInt(40);
        this.bs.writeVInt(1000);
        this.bs.writeVInt(500);
        this.bs.writeVInt(50);
        this.bs.writeVInt(999900);

        this.bs.writeVInt(0);

        this.bs.writeVInt(16); //event
        this.bs.writeVInt(1);
        this.bs.writeVInt(2);
        this.bs.writeVInt(3);
        this.bs.writeVInt(4);
        this.bs.writeVInt(5);
        this.bs.writeVInt(6);
        this.bs.writeVInt(7);
        this.bs.writeVInt(8);
        this.bs.writeVInt(9);
        this.bs.writeVInt(10);
        this.bs.writeVInt(11);
        this.bs.writeVInt(20);
        this.bs.writeVInt(21);
        this.bs.writeVInt(22);
        this.bs.writeVInt(23);
        this.bs.writeVInt(24);


        this.bs.writeVInt(1); //event
        this.bs.writeVInt(1883183565) ; // timestamp
        this.bs.writeVInt(1);  // slot
        this.bs.writeVInt(0);
        this.bs.writeVInt(75992);  // time left
        this.bs.writeVInt(10);
        this.helper.writeDataReference(this.bs,15, 14);  // mapID
        this.bs.writeVInt(3); // state
        this.bs.writeInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(0); //event

        this.bs.writeVInt(8);
        this.bs.writeVInt(20);
        this.bs.writeVInt(35);
        this.bs.writeVInt(75);
        this.bs.writeVInt(140);
        this.bs.writeVInt(290);
        this.bs.writeVInt(480);
        this.bs.writeVInt(800);
        this.bs.writeVInt(1250);

        this.bs.writeVInt(8);
        this.bs.writeVInt(1);
        this.bs.writeVInt(2);
        this.bs.writeVInt(3);
        this.bs.writeVInt(4);
        this.bs.writeVInt(5);
        this.bs.writeVInt(10);
        this.bs.writeVInt(15);
        this.bs.writeVInt(20);

        this.bs.writeVInt(3);
        this.bs.writeVInt(1);
        this.bs.writeVInt(1);
        this.bs.writeVInt(1);

        this.bs.writeVInt(3);
        this.bs.writeVInt(1);
        this.bs.writeVInt(1);
        this.bs.writeVInt(1);

        this.bs.writeVInt(4);
        this.bs.writeVInt(20);
        this.bs.writeVInt(50);
        this.bs.writeVInt(140);
        this.bs.writeVInt(200);

        this.bs.writeVInt(4);
        this.bs.writeVInt(150);
        this.bs.writeVInt(400);
        this.bs.writeVInt(1200);
        this.bs.writeVInt(2600);

        this.bs.writeVInt(0);
        this.bs.writeVInt(200);
        this.bs.writeVInt(20);
        this.bs.writeVInt(8640);
        this.bs.writeVInt(10);
        this.bs.writeVInt(5);
        this.bs.writeVInt(6);
        this.bs.writeVInt(50);
        this.bs.writeVInt(604800);
        this.bs.writeVInt(1);

        this.bs.writeVInt(0); // ReleaseEntry

        this.bs.writeVInt(1); // IntEntry
        this.bs.writeInt(1);
        this.bs.writeInt(41000000 + 15); // Theme

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        new LogicLong(0,1).encode(this.bs);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVInt(0);

        this.bs.writeVLong(0,1);
        this.bs.writeVLong(0,1);
        this.bs.writeVLong(0,1);

        this.bs.writeString("DemirCnq");
        this.bs.writeBoolean(true);
        this.bs.writeInt(0);

        this.bs.writeVInt(8);

        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);

        this.bs.writeVInt(99999); // Diamonds
        this.bs.writeVInt(0); // CumulativePurchasedDiamonds
        this.bs.writeVInt(40);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(0);
        this.bs.writeVInt(2);
        this.bs.writeVInt(2);


        this.bs.writeVInt(10);
                
        this.send(writer,this.type);
    }

}
