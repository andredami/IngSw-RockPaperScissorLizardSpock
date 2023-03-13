package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextualUI extends Observable<Choice> implements Observer<TurnView, String>, Runnable {

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println("--- NEW TURN ---");
            /* Player chooses */
            Choice c = askPlayer();
            setChanged();
            notifyObservers(c);
        }
    }

    public Choice askPlayer() {
        Scanner s = new Scanner(System.in);
        System.out.println("Make your choice: ");
        System.out.println(
                "Signs: " +
                        Arrays.stream(Choice.values())
                                .map(Choice::name)
                                .collect(
                                        Collectors.joining(",", "[", "]")));
        while (true) {
            String input = s.next();
            try {
                return Choice.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.err.println("I don't know this sign: " + input);
                System.err.println("Try again...");
            }
        }
    }

    @Override
    /* FIXME: This is still ugly.
     * We are still relying on String comparisons to determine the event that triggered the update.
     */
    public void update(TurnView model, String arg) {
        if (arg.equals(TurnView.FIELD_CPU_CHOICE)) {
            /* New choice available */
            showChoices(model);
        } else if(arg.equals(TurnView.FIELD_OUTCOME)) {
            /* Outcome ready */
            showOutcome(model);
        } else {
            System.err.println("Ignoring event from " + model + ": " + arg);
            return;
        }
    }

    private void showOutcome(TurnView model) {
        Outcome o = model.getOutcome();
        if (o == null) {
            return;
        }
        /* Output Outcome */
        switch (o) {
            case WIN -> System.out.println("You win! :)");
            case DRAW -> System.out.println("Draw... -.-");
            case LOSE -> System.out.println("You lose! :(");
        }
    }

    private void showChoices(TurnView model) {
        Choice cpuChoice = model.getCpuChoice();
        if (cpuChoice == null) {
            return;
        }
        /* Show CPU's choice */
        System.out.println("CPU chose: " + cpuChoice);
    }
}