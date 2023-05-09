package it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.view;

import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Choice;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Outcome;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.Turn;
import it.polimi.ingegneriaDelSoftware.provaFinale.esercitazioni.model.TurnView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicalUI extends UI {

    private enum State {
        WAITING_FOR_PLAYER,
        WAITING_FOR_OUTCOME
    }

    private static class MainFrame extends JFrame {

        private static final BufferedImage[] signs = new BufferedImage[Choice.values().length];
        static {
            for (int i = 0; i < Choice.values().length; i++) {
                try {
                    signs[i] = ImageIO.read(new File("src/main/resources/" + Choice.values()[i].name().toLowerCase() + ".png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private final JLabel playerChoice = new JLabel();
        private final JLabel cpuChoice = new JLabel();
        private final JComboBox<Choice> action = new JComboBox<>(Choice.values());
        private final JButton play = new JButton("Play");
        private final JLabel outcome = new JLabel("");

        public MainFrame(ActionListener playActionListener) {
            super("Rock Paper Scissors Lizard Spock");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 400);
            setLayout(new GridLayout(2, 2));

            add(playerChoice);
            add(cpuChoice);
            JPanel selectAction = new JPanel();
            selectAction.add(action);
            action.addActionListener((e) -> {
                if (e.getSource() instanceof JComboBox<?> cb) {
                    if (cb.getSelectedItem() instanceof Choice c) {
                        playerChoice.setIcon(new ImageIcon(MainFrame.signs[c.ordinal()]));
                    }
                }
            });
            action.setSelectedIndex(0);
            action.setEnabled(false);
            selectAction.add(play);
            play.addActionListener(playActionListener);
            play.setEnabled(false);
            add(selectAction);
            add(outcome);
        }
    }

    private State state = State.WAITING_FOR_PLAYER;
    private final Object lock = new Object();
    private final ActionListener playActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (frame.action.getSelectedItem() instanceof Choice c) {
                setState(State.WAITING_FOR_OUTCOME);
                setChanged();
                notifyObservers(c);
            }
        }
    };
    private final MainFrame frame = new MainFrame(playActionListener);


    private State getState() {
        synchronized (lock) {
            return state;
        }
    }

    private void setState(State state) {
        synchronized (lock) {
            this.state = state;
            SwingUtilities.invokeLater(() -> {
                frame.action.setEnabled(state == State.WAITING_FOR_PLAYER);
                frame.play.setEnabled(state == State.WAITING_FOR_PLAYER);
            });
            lock.notifyAll();
        }
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() ->
            {
                frame.setVisible(true);
                setState(State.WAITING_FOR_PLAYER);
            });
    }

    @Override
    public void update(TurnView model, Turn.Event arg) {
        switch (arg) {
            case CPU_CHOICE -> showChoices(model);
            case OUTCOME -> {
                showOutcome(model);
                this.setState(State.WAITING_FOR_PLAYER);
            }
            default -> System.err.println("Ignoring event from " + model + ": " + arg);
        }
    }

    private void showOutcome(TurnView model) {
        Outcome o = model.getOutcome();
        if (o == null) {
            return;
        }
        /* Output Outcome */
        SwingUtilities.invokeLater(() -> {
            switch (o) {
                case WIN -> frame.outcome.setText("You win! :)");
                case DRAW -> frame.outcome.setText("Draw... -.-");
                case LOSE -> frame.outcome.setText("You lose! :(");
            }
        });
    }

    private void showChoices(TurnView model) {
        Choice c = model.getCpuChoice();
        if (c == null) {
            return;
        }
        /* Show CPU's choice */
        SwingUtilities.invokeLater(() -> {
            frame.cpuChoice.setIcon(new ImageIcon(MainFrame.signs[c.ordinal()]));
        });
    }
}
