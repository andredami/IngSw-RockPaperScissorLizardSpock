package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ClientImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.ServerImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.GraphicalUI;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.TextualUI;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.UI;

import java.rmi.RemoteException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException {
        Server server = new ServerImpl();

        UI ui;
        if (args.length > 0 && args[0].equals("text")) {
            ui = new TextualUI();
        } else {
            ui = new GraphicalUI();
        }

        ClientImpl client = new ClientImpl(server, ui);
        client.run();
    }
}
