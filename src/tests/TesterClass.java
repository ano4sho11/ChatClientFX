package tests;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class TesterClass {

    public static void main(String... args) {

        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            client.setID(i + 1);
            client.connectToServer(8080, "localhost");
            clients.add(client);
        }
    }
}
