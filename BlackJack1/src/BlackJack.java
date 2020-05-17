import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

import java.awt.Dimension;

public class BlackJack extends JFrame implements ActionListener {
    public static final int WIDTH = 400, HEIGHT = 300;

    public Deck deck;
    Draw draw = new Draw();
    public JLabel main = draw.getJLabel();

    public Player gambler = new Player("Gambler", 100,   75);
    public Player dealer  = new Player("Dealer",  100,  225);

    public JButton hitButton   = new JButton("Hit Me");
    public JButton stickButton = new JButton("Stick");
    public JButton dealButton  = new JButton("Deal");

    public JLabel status = new JLabel(" ", JLabel.CENTER);

    BlackJack() {
        setTitle("Blackjack 1.0");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // hit, stick, and deal buttons
        JPanel buttons = new JPanel();
        buttons.add(hitButton);
        buttons.add(stickButton);
        buttons.add(dealButton);
        hitButton.addActionListener(this);
        stickButton.addActionListener(this);
        dealButton.addActionListener(this);
        hitButton.setEnabled(false);
        stickButton.setEnabled(false);

        // add the dealer, gambler, buttons, and status bar
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(main);
        getContentPane().add(buttons);
        getContentPane().add(status);

        draw.setCanvasSize(WIDTH, HEIGHT);
        draw.setXscale(0, WIDTH);
        draw.setYscale(0, HEIGHT);
        pack();
        setVisible(true);
    }


    // deal one card to given player from the deck
    public void hit(Player player) {
        player.dealTo(deck.dealFrom());
        player.draw(draw);
        main.repaint();
    }

    // deal out two cards each, shuffling if too few cards
    public void deal() {
        draw.clear(Color.GRAY);
        gambler.reset();
        dealer.reset();
        if (deck == null || deck.size() < 15) {
            deck = new Deck();
            deck.shuffle();
            status.setText("Shuffling");
        }
        hit(gambler);
        hit(dealer);
        dealer.peak().conceal();     // hide the dealer's first card
        hit(gambler);
        hit(dealer);
    }

    // who won?
    public void checkWinner() {
        dealer.peak().reveal();        // time to reveal dealer's card
        dealer.draw(draw);
        main.repaint();
        if      (gambler.value() >  21)             status.setText("Gambler busts");
        else if (dealer.value()  >  21)             status.setText("Dealer busts");
        else if (gambler.value() == dealer.value()) status.setText("Push");
        else if (gambler.value() >  dealer.value()) status.setText("Gambler wins");
        else                                        status.setText("Dealer wins");
        StdOut.println(status.getText());
    }

    // process a button push
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hitButton) {
            hit(gambler);
            if(gambler.value() > 21) {
                checkWinner();
                hitButton.setEnabled(false);
                stickButton.setEnabled(false);
                dealButton.setEnabled(true);
            }
        }

        if (e.getSource() == stickButton) {
            while(dealer.value() < 17)
                hit(dealer);
            checkWinner();
            hitButton.setEnabled(false);
            stickButton.setEnabled(false);
            dealButton.setEnabled(true);
        }

        if (e.getSource() == dealButton) {
            deal();
            status.setText(" ");
            hitButton.setEnabled(true);
            stickButton.setEnabled(true);
            dealButton.setEnabled(false);
        }
    }


    // play Blackjack
    public static void main(String argv[]) {
        new BlackJack();
    }

}