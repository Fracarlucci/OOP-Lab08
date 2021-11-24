package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Controller implementation.
 *
 */
public class ControllerImpl implements Controller {

    private String string;
    private final List<String> list = new LinkedList<>();

    @Override
    public final void setNext(final String s) throws NullPointerException{
        if (s != null) {
            this.string = s;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public final String getNext() {
        return this.string;
    }

    @Override
    public final List<String> getHistory() {
        return this.list;
    }

    @Override
    public final void printCurr() {
        this.list.add(getNext());
        System.out.println(getNext());
    }

}
