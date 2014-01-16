package client;

import Utils.Console;
import datapacket.DataPacket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

    private int mID;
    private int mToID;

    private int mPort;
    private String mIPAdress;
    private Socket mSocket;

    private volatile boolean mRunning;

    private ObjectInputStream mObjectInputStream;
    private ObjectOutputStream mObjectOutputStream;

    private ExecutorService mExecutorService;

    public Client() {
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public void connectToServer(int port, String IPAdress) {
        Console.info("New client");
        mPort = port;
        mIPAdress = IPAdress;
        try {
            mSocket = new Socket(mIPAdress, mPort);
            DataPacket dataPacket = new DataPacket();
            dataPacket.setFromClientID(mID);
            dataPacket.setRequestType(DataPacket.RequestType.CONNECT_SERVER);
            sendDataPacket(dataPacket);
        } catch (IOException | NullPointerException e) {
            Console.error(e.getMessage());
        }
        mRunning = true;
        mExecutorService.execute(this);
    }

    public void disconnectFromServer() {
        try {
            DataPacket dataPacket = new DataPacket();
            dataPacket.setFromClientID(mID);
            dataPacket.setRequestType(DataPacket.RequestType.DISCONNECT_SERVER);
            sendDataPacket(dataPacket);
            if (mObjectInputStream != null) mObjectInputStream.close();
            if (mObjectOutputStream != null) mObjectOutputStream.close();
            if (mSocket != null) mSocket.close();
        } catch (IOException | NullPointerException e) {
            Console.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (mRunning) {
            try {
                DataPacket dataPacketIn = receiveDataPacket();

                if (!mRunning) {
                    break;
                }

                switch (dataPacketIn.getRequestType()) {
                    case NONE:
                        // don't do anything...
                        break;
                    case PING:
                        String temp = (dataPacketIn.getFromClientID() == 0) ? "server" : dataPacketIn.getFromClientID() + "";
                        Console.info("request type: none, from client: " + temp);
                        Console.info(temp + ": " + dataPacketIn.getMessage());
                        break;
                    case CONNECT_CLIENT:

                        break;
                    case DISCONNECT_CLIENT:

                        break;
                    case DISCONNECT_SERVER:

                        break;
                }
            } catch (ClassNotFoundException e) {
                Console.error(e.getMessage());
            } catch (IOException e) {
                Console.error(e.getMessage());
            }
        }
        Console.info("client " + mID + " has shut down...");
    }

    public DataPacket receiveDataPacket() throws IOException, ClassNotFoundException {
        mObjectInputStream = new ObjectInputStream(new BufferedInputStream(mSocket.getInputStream()));
        DataPacket dataPacket = (DataPacket) mObjectInputStream.readObject();
        return dataPacket;
    }

    public void sendDataPacket(DataPacket dataPacket) throws IOException {
        mObjectOutputStream = new ObjectOutputStream(new BufferedOutputStream(mSocket.getOutputStream()));
        mObjectOutputStream.writeObject(dataPacket);
        mObjectOutputStream.flush();
    }

    public void shutDown() {
        Console.info("shutting down client: " + mID);
        disconnectFromServer();
        if (mRunning) {
            mRunning = false;
        }
        mExecutorService.shutdown();
        try {
            if (!mExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                mExecutorService.shutdownNow();
                if (!mExecutorService.awaitTermination(5, TimeUnit.SECONDS))
                    Console.error("ExecutorService did not terminate");
            }
        } catch (InterruptedException e) {
            mExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
            Console.error(e.getMessage());
        }
    }

    public int getPort() {
        return mPort;
    }

    public String getIPAdress() {
        return mIPAdress;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public int getID() {
        return mID;
    }

    public void setToID(int toID) {
        mToID = toID;
    }

    public int getToID() {
        return mToID;
    }
}
