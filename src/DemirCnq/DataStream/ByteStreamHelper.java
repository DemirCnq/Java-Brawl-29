package DemirCnq.DataStream;

import java.util.Objects;

public class ByteStreamHelper {
    public void writeDataReference(ByteStream stream, int csv, int value) {
        stream.writeVInt(csv);
        stream.writeVInt(value);
    }

    public void encodeLogicLong(ByteStream stream, LogicLong logiclong) {
        if (Objects.isNull(logiclong)) {
            logiclong = new LogicLong(0,0);
        }
    }
}
