package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observable;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextualUI extends Observable<Choice> implements Observer<TurnView, Turn.Event>, Runnable {

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
    public void update(TurnView model, Turn.Event arg) {
        switch (arg) {
            case CPU_CHOICE -> showChoices(model);
            case OUTCOME -> showOutcome(model);
            default -> System.err.println("Ignoring event from " + model + ": " + arg);
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