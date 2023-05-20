package DemirCnq.DataStream;

public class ChecksumEncoder {
    public int checksum;
    public int checksum2;
    public boolean checksumEnebled;
    public CPPDefs cppdefs;
    public ChecksumEncoder() {
        checksum = 0;
        checksum2 = 0;
        checksumEnebled = true;
        cppdefs = new CPPDefs();
    }
    public void destruct() {
        checksum = 0;
        checksum2 = 0;
        checksumEnebled = true;
    }

    public boolean writeBoolean(boolean value) {
        int integer;
        if (value) {
            integer = 13;
        } else integer = 7;
        this.checksum = integer + cppdefs.__ROR4__(this.checksum, 31);
        return value;
    }

    public void writeByte(byte value) {
        this.checksum = cppdefs.__ROR4__(this.checksum, 31) + value + 11;
    }

    public void writeBytes(byte[] value, int length) {
        int intager;
        if (value != null) intager = length + 38;
        else intager = 37;
        this.checksum = cppdefs.__ROR4__(this.checksum, 31);
    }

    public void writeInt(int value) {
        this.checksum = cppdefs.__ROR4__(this.checksum, 31) + value + 9;
    }

    public void writeShort(int value) {
        this.checksum = cppdefs.__ROR4__(this.checksum, 31) + value + 19;
    }
    public void writeVLong(int high, int low) {
        this.checksum = low + cppdefs.__ROR4__(high + cppdefs.__ROR4__(this.checksum, 31) + 65, 31) + 88;
    }

}
