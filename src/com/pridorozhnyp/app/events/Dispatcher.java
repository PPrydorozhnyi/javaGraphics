package com.pridorozhnyp.app.events;

/**
 * Created by drake on 18/07/17.
 */
public class Dispatcher {

    private Event event;

    public Dispatcher(Event event) {
        this.event = event;
    }

    public void dispatch(Type type, EventHandler handler) {

        if (event.handled)
            return;

        if (event.getType() == type)
            event.handled = handler.handle(event);

    }

}
