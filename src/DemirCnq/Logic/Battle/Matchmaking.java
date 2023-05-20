package DemirCnq.Logic.Battle;

import DemirCnq.DataStream.BitStream;
import DemirCnq.Logic.Player;
import DemirCnq.Messaging.Server.MatchMakingStatusMessage;
import DemirCnq.Messaging.Server.StartLoadingMessage;
import DemirCnq.Messaging.Server.UdpConnectionInfoMessage;
import DemirCnq.Messaging.Server.VisionUpdateMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.server.LogStream;
import java.util.ArrayList;
import java.util.List;

public class Matchmaking {
    public static int tick = 0;
    public static boolean started = false;
    public List<BattlePlayer> players = new ArrayList<BattlePlayer>();
    List<Socket> connections = new ArrayList<Socket>();

    public Matchmaking(Player player) {
        new Thread(() -> {
            try {
                //if (connections.size() >= 1){
                StartBattle(player);
                //}
            } catch (IOException e) {
                //throw new RuntimeException(e);
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }).start();
    }

    public void StartBattle(Player player) throws IOException, InterruptedException {
        started = true;
        StartLoadingMessage sl = new StartLoadingMessage(20559,player.os);
        sl.encode();
        new UdpConnectionInfoMessage(24112,player.os).encode();
        player.roundstate = -1;
        Thread.sleep(6000);

        while (player.roundstate == -1 && started) {
            if (true) {
                tick++;
            }

            process(player);
            Thread.sleep(0045);

        }

    }

    public void process(Player player) throws IOException {
            BitStream bit = new BitStream(1024);
            LogicGameObjectManagerServer lgom = new LogicGameObjectManagerServer(bit, player, tick);
            //lgom.players = players;
            lgom.encode(0);
            lgom.update();

            VisionUpdateMessage vu = new VisionUpdateMessage(24109,player.os, player);
            vu.TicksGone = tick;
            vu.visionbit = bit;
            vu.encode();

    }
}
