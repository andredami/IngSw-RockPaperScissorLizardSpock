package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game implements Runnable {
    private Choice playerChoice;
    private Choice cpuChoice;
    private Outcome outcome;

    private final Random RAND = new Random();

    @Override
    public void run() {
        while(true) {
            System.out.println("--- NEW TURN ---");
            /* Player chooses */
            playerChoice = askPlayer();
            /* CPU chooses */
            cpuChoice = askCpu();
            /* Show CPU's choice */
            System.out.println("CPU chose: " + cpuChoice);
            /* Compute Outcome */
            outcome = computeOutcome();
            /* Output Outcome */
            switch (outcome) {
                case WIN -> System.out.println("You win! :)");
                case DRAW -> System.out.println("Draw... -.-");
                case LOSE -> System.out.println("You lose! :(");
            }
        }
    }

    private Choice askPlayer() {
        Scanner s = new Scanner(System.in);
        System.out.println("Make your choice: ");
        System.out.println(
                "Signs: " +
                        Arrays.stream(Choice.values())
                                .map(Choice::name)
                                .collect(
                                        Collectors.joining(",", "[", "]")));
        while(true) {
            String input = s.next();
            try {
                return Choice.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.err.println("I don't know this sign: " + input);
                System.err.println("Try again...");
            }
        }
    }

    private Choice askCpu() {
        Choice[] choices = Choice.values();
        return choices[RAND.nextInt(choices.length)];
    }

    private Outcome computeOutcome() {
        if(playerChoice == cpuChoice){
            return Outcome.DRAW;
        }

        return switch (playerChoice) {
            case ROCK -> Outcome.testWin(cpuChoice == Choice.LIZARD || cpuChoice == Choice.SCISSORS);
            case PAPER -> Outcome.testWin(cpuChoice == Choice.ROCK || cpuChoice == Choice.SPOCK);
            case SCISSORS -> Outcome.testWin(cpuChoice == Choice.PAPER || cpuChoice == Choice.LIZARD);
            case LIZARD -> Outcome.testWin(cpuChoice == Choice.SPOCK || cpuChoice == Choice.PAPER);
            case SPOCK -> Outcome.testWin(cpuChoice == Choice.SCISSORS || cpuChoice == Choice.ROCK);
        };
    }
}
