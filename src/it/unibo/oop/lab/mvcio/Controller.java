package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
 
    private String path = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "output.txt";
    private File file = new File(path);

    /**
     * 
     * @param file to set as current
     * @throws IOException 
     */
    public void setCurrentFile(final File file) throws IOException {
        this.path  = file.getAbsolutePath();
        this.file = new File(path);
    }

    /**
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return file;
    }

    /**
     * 
     * @return the file path
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param string to print
     * @throws IOException
     */
    public void writeFile(final String string) throws IOException {
        try (PrintStream p = new PrintStream(file)) {
            p.println(string);
        } catch (IOException e) {
            System.out.println("Errore durante la write!");
            e.printStackTrace();
        }
    }

}
