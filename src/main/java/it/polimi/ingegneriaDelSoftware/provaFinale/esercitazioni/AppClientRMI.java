package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ClientImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.GraphicalUI;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.TextualUI;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.UI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class AppClientRMI
{
    public static void main( String[] args ) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        AppServer server = (AppServer) registry.lookup("server");

        UI ui;
        if (args.length > 0 && args[0].equals("text")) {
            ui = new TextualUI();
        } else {
            ui = new GraphicalUI();
        }

        ClientImpl client = new ClientImpl(server.connect(), ui);
        client.run();
    }
}
