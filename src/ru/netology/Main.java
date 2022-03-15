package ru.netology;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread server = new Thread(null, () -> new Server(22222), "Server");
        Thread client = new Thread(null, () -> new Client("127.0.0.1", 22222), "Client");
        server.start();
        client.start();
        client.join();
        server.interrupt();
    }
}
