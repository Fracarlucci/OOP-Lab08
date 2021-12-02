package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 
 * Print log on standard output.
 *
 */
public class DrawNumberViewOut implements DrawNumberView {

    private final PrintStream out;

    /**
     * 
     * @param stream to write on
     */
    public DrawNumberViewOut(final PrintStream stream) {
        out = stream;
    }

    /**
     * 
     * @param path of the file
     * @throws FileNotFoundException
     */
    public DrawNumberViewOut(final String path) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream(new File(path)));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void start() {
    }

    @Override
    public final void numberIncorrect() {
        out.println("Incorrect Number.. try again");
    }

    @Override
    public final void result(final DrawResult res) {
        out.println(res.getDescription());
    }

    @Override
    public final void limitsReached() {
        out.println("You Lost");
    }

    @Override
    public final void displayError(final String message) {
        out.println("Error: " + message);

    }

}
