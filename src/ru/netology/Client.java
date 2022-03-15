package ru.netology;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public Client(String host, int port) {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(host, port);
            final SocketChannel socketChannel = SocketChannel.open();
            try (socketChannel; Scanner scanner = new Scanner(System.in)) {
                socketChannel.connect(socketAddress);
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                String msg;
                while (true) {
                    System.out.println("Enter any string('end' fo exit): ");
                    msg = scanner.nextLine();
                    if ("end".equals(msg)) break;
                    socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                    Thread.sleep(1000);
                    int bytesCount = socketChannel.read(inputBuffer);
                    System.out.println(new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8));
                    inputBuffer.clear();
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
