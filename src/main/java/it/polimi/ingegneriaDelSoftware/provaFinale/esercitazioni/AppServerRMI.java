package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.rmi.ServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Hello world!
 *
 */
public class AppServerRMI
{
    public static void main( String[] args ) throws RemoteException {
        Server server = new ServerImpl();

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("server", server);
    }
}
