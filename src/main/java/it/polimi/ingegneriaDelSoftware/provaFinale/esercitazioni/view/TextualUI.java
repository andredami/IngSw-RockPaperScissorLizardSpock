package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextualUI extends Observable implements Observer, Runnable {

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
    public void update(Observable o, Object arg) {
        if (!(o instanceof TurnView model)){
            System.err.println("Ignoring updates from " + o);
            return;
        }

        if (arg instanceof Choice) {
            /* New choice available */
            showChoices(model);
        } else if(arg instanceof Outcome) {
            /* Outcome ready */
            showOutcome(model);
        } else {
            System.err.println("Ignoring event from " + o + ": " + arg);
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