package com.pridorozhnyp.app.events;


/**
 * Created by drake on 18/07/17.
 */
public class Event {

    private Type type;
    public boolean handled;

    protected  Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
