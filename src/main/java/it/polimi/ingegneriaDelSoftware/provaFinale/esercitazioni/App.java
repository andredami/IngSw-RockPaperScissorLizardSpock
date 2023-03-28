package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ClientImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ServerImpl;

import java.rmi.RemoteException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException {
        Server server = new ServerImpl();

        ClientImpl client = new ClientImpl(server);
        client.run();
    }
}
