package DemirCnq.Messaging;

import DemirCnq.DataStream.BitStream;
import DemirCnq.DataStream.ByteStream;
import DemirCnq.DataStream.ByteStreamHelper;
import DemirCnq.Logger.Logger;

import java.io.DataOutputStream;
import java.io.IOException;

public class PiranhaMessage {

    public ByteStream bs;
    public ByteStreamHelper helper;
    public BitStream bit;
    public int type;

    public PiranhaMessage(byte[] payload){
        //System.out.println(payload);
        this.bs = new ByteStream(payload);
        this.bit = new BitStream(1024);
        this.helper = new ByteStreamHelper();
    }

    public void send(DataOutputStream writer,int typee) throws IOException {
        this.type = typee;
        int length = this.bs.messagePayload.length;
        byte[] header = new byte[7];
        header[0] = (byte)(this.type >> 8 & 0xFF);
        header[1] = (byte)(this.type & 0xFF);
        header[2] = (byte)(length >> 16 & 0xFF);
        header[3] = (byte)(length >> 8 & 0xFF);
        header[4] = (byte)(length & 0xFF);
        header[5] = (byte)(1 >> 8 & 0xFF);
        header[6] = (byte)(1 & 0xFF);
        writer.write(header);
        writer.write(this.bs.messagePayload);

        new Logger().c2s(this.type);
        //System.out.println(new String(this.bs.messagePayload));
    }

}
