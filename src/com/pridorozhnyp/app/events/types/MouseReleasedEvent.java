package com.pridorozhnyp.app.events.types;

import com.pridorozhnyp.app.events.Type;
import com.pridorozhnyp.app.events.types.MouseButtonEvent;

/**
 * Created by drake on 18/07/17.
 */
public class MouseReleasedEvent extends MouseButtonEvent {
    public MouseReleasedEvent(int keyCode, int x, int y) {
        super(Type.MOUSE_RELEASED, keyCode, x, y);
    }
}
