package DemirCnq.Messaging.Client;

import DemirCnq.DataStream.BitStream;
import DemirCnq.Logic.Player;
import DemirCnq.Messaging.PiranhaMessage;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientInputMessage extends PiranhaMessage {
    public Player player;
    public byte[] payload;
    public ClientInputMessage(byte[] payload, Player player) {
        super(payload);
        this.player = player;
        this.payload = payload;
        this.player = player;
    }
    public void  decode() throws IOException {
        BitStream bit = new BitStream(this.bs.getByteArray());
        bit.ReadPositiveInt(14);
        bit.ReadPositiveInt(10);
        bit.ReadPositiveInt(13);
        bit.ReadPositiveInt(10);
        bit.ReadPositiveInt(10);

        var count = bit.ReadPositiveInt(5);
        System.out.println(count);
        for (int i=0; i <count; i++) {
            player.handledinput = bit.ReadPositiveInt(15);
            int id =bit.ReadPositiveInt(4);
            System.out.println(id);
            int x = bit.ReadInt(15);
            int y = bit.ReadInt(15);
            if (id == 2){
                this.player.x = x;
                this.player.y = y;
            }
            if (id == 9) {
                this.player.usepin = true;
                this.player.pin = bit.ReadPositiveInt(3);

            }


            bit.ReadBoolean();

        }
    }
    public void exacute() throws IOException {
        //VisionUpdateMessage vim = new VisionUpdateMessage(24109,player.os, this.player);
        //vim.encode();
    }

}
