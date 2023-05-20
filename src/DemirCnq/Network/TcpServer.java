package DemirCnq.Network;

import DemirCnq.Logger.Logger;
import DemirCnq.Logic.Player;
import DemirCnq.Messaging.MessageFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public DataInputStream reader;
    public DataOutputStream writer;
    public Player player;
    public TcpServer()  {
        Logger log = new Logger();
        this.player = new Player();
        try {

            ServerSocket server = new ServerSocket(9339);

            System.out.println("ServerStarted");
            while (server.isBound()) {
                Socket client = server.accept();
                this.player.client = client;
                new Thread(() -> {
                    log.server(client, "Connected");

                    try {
                        this.reader = new DataInputStream(client.getInputStream());
                        this.player.os = new DataOutputStream(client.getOutputStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    while (client.isConnected()) {
                        byte[] header = new byte[7];
                        try {
                            this.reader.read(header);
                        } catch (IOException e) {
                            break;
                        }
                        int type = (((header[0] & 0xFF) << 8) | (header[1] & 0xFF));
                        int length = (((header[2] & 0xFF) << 16) | ((header[3] & 0xFF) << 8) | (header[4] & 0xFF));
                        byte[] payload = new byte[length];
                        try {
                            this.reader.read(payload);
                        } catch (IOException e) {
                            break;
                        }
                        if (type == 0) {
                            break;
                        }
                        log.server(client, "received message with type: " + type);
                        try {
                            new MessageFactory().createMessageByType(type, payload, this.player);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    log.server(client, "disconnected");
                }).start();

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
