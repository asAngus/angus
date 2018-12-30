package com.p2p.v2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * @author weipeng
 * @Date 2018/5/6 16:38
 */
public class P2pMain {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            s.joinGroup(group);
            String msg = "hello";
            DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
                    group, 6789);
            s.send(hi);
            byte[] buf = new byte[1000];
            DatagramPacket recv = new DatagramPacket(buf, buf.length);
            s.receive(recv);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
