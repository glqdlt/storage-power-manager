package com.glqdlt.myhome.storagepowermanager.executer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Slf4j
public class WakeOnLanExecutor {

    public void execute(String port, String host, String macAddress) {
        DatagramSocket socket = null;
        try {
            byte[] macBytes = getMacBytes(macAddress);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, Integer.parseInt(port));
            socket = new DatagramSocket();
            socket.send(packet);

            log.debug("Send packet.");
        } catch (Exception e) {
            log.error("Some Exception", e);
            throw new RuntimeException();
//            System.exit(1);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private byte[] getMacBytes(String macStr) {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e) {
            log.error("Wrong Mac Address", e);
//            System.exit(1);
        }
        return bytes;
    }
}
