package com.pridorozhnyp.app;

import com.pridorozhnyp.app.core.Window;
import com.pridorozhnyp.app.sandbox.Example;

import java.awt.*;

/**
 * Created by drake on 18/07/17.
 */
public class Main {

    public static void main(String[] args) {
        Window window = new Window("Window", 800, 480);
        window.addLayer(new Example("Layer1", Color.blue));
        window.addLayer(new Example("Layer2", Color.red));
    }
}
