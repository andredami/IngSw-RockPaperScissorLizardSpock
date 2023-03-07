package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni;

public enum Outcome {
    WIN, LOSE, DRAW;

    public static Outcome testWin(boolean winCondition) {
        return winCondition ? WIN : LOSE;
    }
}
