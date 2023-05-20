package DemirCnq.DataStream;

//ByteStream from bsds. i rewrite it in java

public class ByteStream {
    public byte[] messagePayload;
    public int bitoffset;
    public int offset;
    public int length;
    public LogicLong logicLong;
    public ChecksumEncoder checksumencoder;
    public ByteStream(byte[] payload) {
        logicLong = new LogicLong(0,0);
        checksumencoder = new ChecksumEncoder();
        this.messagePayload = payload;
        this.bitoffset = 0;
        this.offset = 0;
        this.length = messagePayload.length;
    }

    public void clear() {
        if (this.messagePayload != null){

            this.messagePayload = new byte[0];
        }
        this.bitoffset = 0;
        this.offset = 0;
    }
    public void destroy() {
        this.messagePayload = null;
        this.bitoffset = 0;
        this.offset = 0;
        this.length = 0;
    }

    public void ensureCapacity(int len) {
        var offset = this.offset;
        if (this.messagePayload.length < offset + len){
            var buffer_copy = this.messagePayload;
            var buf_len = len;
            this.length = buf_len;
            //:skull:
            byte[] combined = new byte[this.messagePayload.length + buf_len];

            System.arraycopy(this.messagePayload,0,combined,0,this.messagePayload.length);
            System.arraycopy(new byte[buf_len],0,combined,this.messagePayload.length,buf_len);
            this.messagePayload = combined;
        }

    }
    public void writeHexa() {
        //todo
    }
    public int getBitOffset() {
        return this.bitoffset;
    }
    public byte[] getByteArray() {
        return this.messagePayload;
    }
    public int getCapacityIncrement() {
        return 100;
    }

    public int getDataPointer() {
        return this.messagePayload[this.offset];
    }

    public int getLength() {
        var length = this.length;
        if (this.length <= this.offset) length = this.offset;
        return length;
    }

    public int getOffset() {
        return this.offset;
    }


    public boolean readBoolean() {
        var bitoffset = this.bitoffset;
        var offset = this.offset + (8 - bitoffset >> 3);
        this.offset = offset;
        this.bitoffset = bitoffset + 1 & 7;
        return (1 << (bitoffset & 31) & this.messagePayload[offset - 1]) != 0;
    }

    public byte readByte() {
        this.bitoffset = 0;
        var result = this.messagePayload[this.offset];
        this.offset += 1;
        return result;

    }
    public byte[] readBytes(int size) { //from haccercat (s.b)
        byte[] result = new byte[size];
        for (var index = 0; index < size; index++) {
            result[index] = this.readByte();
        }
        return result;
    }

    public int readInt() {
        this.bitoffset = 0;
        var result = (this.messagePayload[this.offset] << 24);
        result += (this.messagePayload[this.offset + 1] << 16);
        result += (this.messagePayload[this.offset + 2] << 8);
        result += (this.messagePayload[this.offset + 3]);
        this.offset += 4;
        return result;
    }

    public LogicLong readLong(){
        logicLong.decode(this);
        return logicLong;
    }

    public int readLongLong() {
        this.bitoffset = 0;
        var high = (this.messagePayload[this.offset] << 24);
        high += (this.messagePayload[this.offset + 1] << 16);
        high += (this.messagePayload[this.offset + 2] << 8);
        high += (this.messagePayload[this.offset + 3]);
        this.offset += 4;
        var low = (this.messagePayload[this.offset] << 24);
        low += (this.messagePayload[this.offset + 1] << 16);
        low += (this.messagePayload[this.offset + 2] << 8);
        low += (this.messagePayload[this.offset + 3]);
        this.offset += 4;
        return logicLong.toLong(high, low);
    }

    public String readString() {
        var max = 900000;
        this.bitoffset = 0;
        var length = (this.messagePayload[this.offset] << 24);
        length += (this.messagePayload[this.offset + 1] << 16);
        length += (this.messagePayload[this.offset + 2] << 8);
        length += (this.messagePayload[this.offset + 3]);
        this.offset += 4;
        if (length <= -1){
            if (length != -1) {
                System.out.println("Negative String length encountered.");
            }
            return "";
        }

        else if (length > max) {
            System.out.println("Too long String encountered, length {length}, max {max}");
            return "";
        }
        var result = new String(this.readBytes(length));
        this.offset += length;
        return result;
    }

    public int readVInt() {
        int offset = this.offset;
        this.bitoffset = 0;
        int v4 = offset +1;
        this.offset = offset+1;
        var result = this.messagePayload[offset] & 0x3F;
        if ((this.messagePayload[offset] & 0x40) != 0) {
            if ((this.messagePayload[offset] & 0x80) != 0) {
                this.offset = offset + 2;
                var v7 = this.messagePayload[v4];
                var v8 = result & 0xFFFFE03F | ((v7 & 0x7F) << 6);
                if ((v7 & 0x80) != 0) {
                    this.offset = offset + 3;
                    var v9 = v8 & 0xFFF01FFF | ((this.messagePayload[offset + 2] & 0x7F) << 13);
                    if ((this.messagePayload[offset + 2] & 0x80) != 0) {
                        this.offset = offset + 4;
                        var v10 = v9 & 0xF80FFFFF | ((this.messagePayload[offset + 3] & 0x7F) << 20);
                        if ((this.messagePayload[offset + 3] & 0x80) != 0) {
                            this.offset = offset + 5;
                            return v10 & 0x7FFFFFF | (this.messagePayload[offset + 4] << 27) | 0x80000000;
                        }
                        else {
                            return v10 | 0xF8000000;
                        }
                    }
                    else {
                        return v9 | 0xFFF00000;
                    }

                }
                else {
                    return v8 | 0xFFFFE000;
                }
            }
            else {
                return this.messagePayload[offset] | 0xFFFFFFC0;
            }
        }
        else if ((this.messagePayload[offset] & 0x80) != 0) {
            this.offset = offset + 2;
            var v6 = this.messagePayload[v4];
            result = result & 0xFFFFE03F | ((v6 & 0x7F) << 6);
            if ((v6 & 0x80) != 0) {
                this.offset = offset + 3;
                result = result & 0xFFF01FFF | ((this.messagePayload[offset + 2] & 0x7F) << 13);
                if ((this.messagePayload[offset + 2] & 0x80) != 0) {
                    this.offset = offset + 4;
                    result = result & 0xF80FFFFF | ((this.messagePayload[offset + 3] & 0x7F) << 20);
                    if ((this.messagePayload[offset + 3] & 0x80) != 0) {
                        this.offset = offset + 5;
                        return result & 0x7FFFFFF | (this.messagePayload[offset + 4] << 27);
                    }

                }
            }
        }
        return result;
    }

    public void writeBoolean(boolean value){ //:skull:
        if (value == true){
            this.writeVInt(1);
        }
        else {
            this.writeVInt(0);
        }
    }

    public void writeByte(byte value){
        checksumencoder.writeByte(value);
        this.bitoffset = 0;
        this.ensureCapacity(1);
        this.messagePayload[this.offset] = value;
        this.offset +=1;
    }

    public void writeBytes(byte[] value, int length){
        checksumencoder.writeBytes(value,length);
        this.bitoffset = 0;
        if (value != null) {
            //writeIntToByteArray
            this.writeIntToByteArray(length);

            this.ensureCapacity(value.length);
            System.arraycopy(value,0,this.messagePayload,this.offset,length);
            this.offset += length;
        }
        else {
            this.writeIntToByteArray(-1);
        }
    }

    public void writeInt(int value) {
        checksumencoder.writeInt(value);
        this.writeIntToByteArray(value);
    }

    public void writeIntToByteArray(int value) {
        this.bitoffset = 0;
        this.messagePayload[this.offset] = (byte) ((byte)(value >> 24) & 0xFF);
        this.messagePayload[this.offset +1] = (byte) ((byte)(value >> 16) & 0xFF);
        this.messagePayload[this.offset +2] = (byte) ((byte)(value >> 8) & 0xFF);
        this.messagePayload[this.offset +3] = (byte) ((byte)value & 0xFF);

        this.offset += 4;
    }

    public void writeShort(int value){
        checksumencoder.writeShort(value);
        this.bitoffset = 0;
        this.messagePayload[this.offset] = (byte) ((byte)(value >> 8) & 0xFF);
        this.messagePayload[this.offset +1] = (byte) ((byte)value & 0xFF);

        this.offset += 2;

    }

    public void writeString(String value) {
        this.writeIntToByteArray(value.length());
        this.ensureCapacity(value.length());
        System.arraycopy(value.getBytes(),0,this.messagePayload,this.offset,value.length());
        this.offset += value.length();
    }

    public void writeVInt(int value) //i get this from somewhere but i forgot.
    {
        int tmp = (value >> 25) & 0x40;
        int flipped = value ^ (value >> 31);

        tmp |= value & 0x3F;
        value >>= 6;

        if ((flipped >>= 6) == 0)
        {
            this.writeByte((byte)tmp);
            return;
        }

        this.writeByte((byte)(tmp | 0x80));

        do
        {
            this.writeByte((byte)((value & 0x7F) | ((flipped >>= 7) != 0 ? 0x80 : 0)));
            value >>= 7;
        } while (flipped != 0);
    }

    public void writeVLong(int high, int low) {
        checksumencoder.writeVLong(high,low);
        this.bitoffset = 0;
        this.writeVInt(high);
        this.writeVInt(low);

    }


}
