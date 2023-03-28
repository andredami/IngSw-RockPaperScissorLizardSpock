package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ClientImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.socket.middleware.ServerStub;

import java.rmi.RemoteException;

/**
 * Hello world!
 *
 */
public class AppClientSocket
{
    public static void main( String[] args ) throws RemoteException {
        ServerStub serverStub = new ServerStub("localhost", 1234);
        ClientImpl client = new ClientImpl(serverStub);
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        serverStub.receive(client);
                    } catch (RemoteException e) {
                        System.err.println("Cannot receive from server. Stopping...");
                        try {
                            serverStub.close();
                        } catch (RemoteException ex) {
                            System.err.println("Cannot close connection with server. Halting...");
                        }
                        System.exit(1);
                    }
                }
            }
        }.start();

        client.run();
    }
}
