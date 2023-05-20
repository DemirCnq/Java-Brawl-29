package DemirCnq.Logic;

import java.io.DataOutputStream;
import java.net.Socket;

public class Player {
    public DataOutputStream os;
    public int handledinput;
    public int roundstate = -1;
    public int x = 5000;
    public int y = 7000;
    public boolean usepin;
    public int pin;
    public Socket client;
}
