package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Server;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.local.ClientImpl;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.local.ServerImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Server server = new ServerImpl();

        ClientImpl client = new ClientImpl(server);
        client.run();
    }
}
