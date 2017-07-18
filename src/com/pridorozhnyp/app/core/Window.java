package com.pridorozhnyp.app.core;

import com.pridorozhnyp.app.events.types.MouseMotionEvent;
import com.pridorozhnyp.app.events.types.MousePressedEvent;
import com.pridorozhnyp.app.events.types.MouseReleasedEvent;
import com.pridorozhnyp.app.layers.Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;
import com.pridorozhnyp.app.events.Event;

/**
 * Created by drake on 18/07/17.
 */
public class Window extends Canvas {

    private BufferStrategy bs;
    private Graphics g;
    private JFrame frame;
    private List<Layer> layers = new ArrayList<Layer>();

    public Window (String name, int width, int height) {

        setPreferredSize(new Dimension(width, height));
        init(name);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), true);
                onEvent(event);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MouseMotionEvent event = new MouseMotionEvent(e.getX(), e.getY(), false);
                onEvent(event);
            }
        });

        render();
    }

    private void init(String name) {

        frame = new JFrame(name);

        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void render() {

        if (bs == null)
            createBufferStrategy(3);
        bs = getBufferStrategy();

        g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        onRender(g);
        g.dispose();

        bs.show();

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {

        }

        EventQueue.invokeLater(()-> render());

    }

    private void onRender(Graphics g) {
        for (Layer l : layers)
            l.onRender(g);
    }

    private void onEvent(Event event) {
        for (int i = layers.size() - 1; i >= 0; --i)
            layers.get(i).onEvent(event);
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

}
