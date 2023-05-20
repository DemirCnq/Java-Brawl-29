package DemirCnq.DataStream;

public class CPPDefs {
    public int rotl8(int value, int count) {
        return value << count | value >> (8 - count);
    }

    public int rotl16(int value, int count) {
        return value << count | value >> (16 - count);
    }

    public int rotl32(int value, int count) {
        return value << count | value >> (32 - count);
    }

    public int rotl64(int value, int count) {
        return value << count | value >> (64 - count);
    }

    public int __ROL1__(int value, int count) {
        return rotl8(value,count);
    }
    public int __ROL2__(int value, int count) {
        return rotl16(value,count);
    }
    public int __ROL4__(int value, int count) {
        return rotl32(value,count);
    }
    public int __ROL8__(int value, int count) {
        return rotl64(value,count);
    }
    public int rotr8(int value, int count) {
        return value >> count | value << (8 - count);
    }
    public int rotr16(int value, int count) {
        return value >> count | value << (16 - count);
    }
    public int rotr32(int value, int count) {
        return value >> count | value << (32 - count);
    }
    public int rotr64(int value, int count) {
        return value >> count | value << (64 - count);
    }

    public int __ROR1__(int value, int count) {
        return rotr8(value,count);
    }
    public int __ROR2__(int value, int count) {
        return rotr16(value,count);
    }
    public int __ROR4__(int value, int count) {
        return rotr32(value,count);
    }
    public int __ROR8__(int value, int count) {
        return rotr64(value,count);
    }


}
