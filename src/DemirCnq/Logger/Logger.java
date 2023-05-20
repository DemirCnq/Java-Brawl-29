package DemirCnq.Logger;

import java.net.Socket;

public class Logger {
    public void server(Socket socket, String str) {
        System.out.println("["+socket.getRemoteSocketAddress().toString()+"] " +str);
    }

    public void c2s(int str) {
        System.out.println("[c2s] " +str);
    }
}
