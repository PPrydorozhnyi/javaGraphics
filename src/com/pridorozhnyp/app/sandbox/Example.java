package com.pridorozhnyp.app.sandbox;

import com.pridorozhnyp.app.events.Dispatcher;
import com.pridorozhnyp.app.events.Event;
import com.pridorozhnyp.app.events.EventHandler;
import com.pridorozhnyp.app.events.Type;
import com.pridorozhnyp.app.events.types.MouseMotionEvent;
import com.pridorozhnyp.app.events.types.MousePressedEvent;
import com.pridorozhnyp.app.events.types.MouseReleasedEvent;
import com.pridorozhnyp.app.layers.Layer;

import java.awt.*;
import java.util.Random;

/**
 * Created by drake on 18/07/17.
 */
public class Example extends Layer {

    private String name;
    private Color color;
    private Rectangle rect;
    private int px, py;
    private boolean dragging = false;

    private static final Random random = new Random();

    public Example (String name, Color color) {

        this.name = name;
        this.color = color;

        rect = new Rectangle(random.nextInt(100) + 150, random.nextInt(100) + 250, 120, 240);

    }

    @Override
    public void onEvent(Event event) {
        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressedEvent)e));
        dispatcher.dispatch(Type.MOUSE_RELEASED, (Event e) -> onReleased((MouseReleasedEvent)e));
        dispatcher.dispatch(Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotionEvent)e));
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.white);
        g.drawString(name, rect.x + 10, rect.y + 30);
    }

    private boolean onPressed(MousePressedEvent event) {
        if (rect.contains(new Point(event.getX(), event.getY())))
            dragging = true;
        return dragging;
    }

    private boolean onReleased(MouseReleasedEvent event) {
        dragging = false;
        return dragging;
    }

    private boolean onMoved(MouseMotionEvent event) {
        if (dragging) {
            rect.x += event.getX() - px;
            rect.y += event.getY() - py;
        }

        px = event.getX();
        py = event.getY();

        return dragging;
    }
}
