package DemirCnq.DataStream;

public class LogicLong {
    public int high;
    public int low;
    public LogicLong(int high, int low) {
        this.high = high;
        this.low = low;
    }
    public void decode(ByteStream bytestream) {
        this.high = bytestream.readInt();
        this.low = bytestream.readInt();
    }

    public void encode(ByteStream bytestream) {
        bytestream.writeInt(this.high);
        bytestream.writeInt(this.low);
    }

    public int getLowerInt() {
        return this.low;
    }

    public int getHigherInt() {
        return this.high;
    }
    public int toLong(int high,int low) {
        var lowerInt = low & 0x7FFFFFFF;
        if (low < 0)
            lowerInt = low | 0x80000000;
        return lowerInt | high << 32;
    }
}
