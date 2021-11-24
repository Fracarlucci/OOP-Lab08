package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Applicazione utilissma 2");

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    /**
     * 
     * @param c
     */
    public SimpleGUIWithFileChooser(final Controller c) {

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        frame.setLocationByPlatform(true);

        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel upperPanel = new JPanel(new BorderLayout());
        final JTextArea text = new JTextArea();
        final JTextField textField = new JTextField(c.getCurrentFile().getName());
        textField.setEditable(false);
        final JButton browse = new JButton("Browse");
        final JButton save = new JButton("Save");

        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                    final JFileChooser chooser = new JFileChooser();
                    final int res = chooser.showSaveDialog(null);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        //???
                    } else {
                        JOptionPane.showMessageDialog(frame, new JLabel("an error has occurred"), "Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.writeFile(text.getText());
                } catch (IOException e1) {
                    System.out.println("Errore durante il salvataggio del file!!");
                    e1.printStackTrace();
                }
            }

        });

        panel.add(text, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);
        panel.add(upperPanel, BorderLayout.NORTH);
        upperPanel.add(textField, BorderLayout.CENTER);
        upperPanel.add(browse, BorderLayout.LINE_END);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Shows the GUI.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.show();
    }

}
