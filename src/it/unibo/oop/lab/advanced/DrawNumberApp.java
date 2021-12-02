package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    /**
     * Where the file is located.
     */
    public static final String FILE_NAME = "C:\\Users\\fraca\\Documents\\Università\\Programmazione ad Oggetti\\Laboratorio\\OOP-Lab08\\res\\config.yml";
    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @throws IOException 
     * 
     * @param views available
     */
    public DrawNumberApp(final DrawNumberView... views) throws IOException {
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        this.model = readSettings();
    }

    private DrawNumberImpl readSettings() throws IOException {
        try (
            BufferedReader r = new BufferedReader(new FileReader(FILE_NAME))
        ) {
              String str = r.readLine();
              while (str != null) {
                  final StringTokenizer st = new StringTokenizer(str);
                  final String next = st.nextToken();
                  if (next.contains("min")) {
                      this.min = Integer.parseInt(st.nextToken());
                  }
                  if (next.contains("max")) {
                      this.max = Integer.parseInt(st.nextToken());
                  }
                  if (next.contains("attempts")) {
                      this.attempts = Integer.parseInt(st.nextToken());
                  }
                  str = r.readLine();
             }
        }
        return new DrawNumberImpl(min, max, attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (Exception e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        try {
            new DrawNumberApp(
                    new DrawNumberViewImpl(),
                    new DrawNumberViewOut(System.out),
                    new DrawNumberViewOut("C:\\Users\\fraca\\Documents\\Università\\Programmazione ad Oggetti\\output.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
