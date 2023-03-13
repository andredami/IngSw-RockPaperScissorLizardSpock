package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.controller;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.util.Observer;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view.TextualUI;

import java.util.Random;

public class Game implements Observer<TextualUI, Choice> {
    private final Turn model;
    private final TextualUI view;

    private final Random RAND = new Random();

    public Game(Turn model, TextualUI view) {
        this.model = model;
        this.view = view;
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

    @Override
    public void update(TextualUI o, Choice arg) {
        if (o != view) {
            System.err.println("Discarding notification from " + o);
            return;
        }
        model.setPlayerChoice(arg);
        play();
    }
}