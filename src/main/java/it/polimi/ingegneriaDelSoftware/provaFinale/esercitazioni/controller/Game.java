package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.controller;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.distributed.Client;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;

import java.util.Random;

public class Game {
    private final Turn model;
    private final Client client;

    private final Random RAND = new Random();

    public Game(Turn model, Client client) {
        this.model = model;
        this.client = client;
    }

    private void play() {
        /* CPU chooses */
        model.setCpuChoice(askCpu());
        /* Compute Outcome */
        model.setOutcome(computeOutcome());
        /* Clean State */
        model.clear();
    }

    private Choice askCpu() {
        Choice[] choices = Choice.values();
        return choices[RAND.nextInt(choices.length)];
    }

    private Outcome computeOutcome() {
        if (model.getPlayerChoice() == model.getCpuChoice()) {
            return Outcome.DRAW;
        }

        return switch (model.getPlayerChoice()) {
            case ROCK -> Outcome.testWin(model.getCpuChoice() == Choice.LIZARD || model.getCpuChoice() == Choice.SCISSORS);
            case PAPER -> Outcome.testWin(model.getCpuChoice() == Choice.ROCK || model.getCpuChoice() == Choice.SPOCK);
            case SCISSORS -> Outcome.testWin(model.getCpuChoice() == Choice.PAPER || model.getCpuChoice() == Choice.LIZARD);
            case LIZARD -> Outcome.testWin(model.getCpuChoice() == Choice.SPOCK || model.getCpuChoice() == Choice.PAPER);
            case SPOCK -> Outcome.testWin(model.getCpuChoice() == Choice.SCISSORS || model.getCpuChoice() == Choice.ROCK);
        };
    }

    public void update(Client o, Choice arg) {
        if (!o.equals(client)) {
            System.err.println("Discarding notification from " + o);
            return;
        }
        model.setPlayerChoice(arg);
        play();
    }
}