package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.controller.Game;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.TextualUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Turn model = new Turn();
        TurnView modelView = new TurnView(model);
        TextualUI view = new TextualUI();
        Game controller = new Game(model, view);

        modelView.addObserver(view);
        view.addObserver(controller);

        view.run();
    }
}
