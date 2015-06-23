/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colorextractor.utils;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TweakUI {

    private static Point initialClick;

    public static void centerParent(JDialog parent) {
        parent.setLocationRelativeTo(null);
    }

    public static void centerParent(JFrame parent) {
        parent.setLocationRelativeTo(null);
    }

    public static void onMouseDragged(JFrame parent, MouseEvent evt) {
        // get location of Window
        int thisX = parent.getLocation().x;
        int thisY = parent.getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        parent.setLocation(X, Y);
    }

    public static void onMousePressed(JFrame parent, MouseEvent evt) {
        initialClick = evt.getPoint();
        parent.getComponentAt(initialClick);
    }

    public static void onMouseDragged(JDialog parent, MouseEvent evt) {
        // get location of Window
        int thisX = parent.getLocation().x;
        int thisY = parent.getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        parent.setLocation(X, Y);
    }

    public static void onMousePressed(JDialog parent, MouseEvent evt) {
        initialClick = evt.getPoint();
        parent.getComponentAt(initialClick);
    }

    public static void onMouseDragged(JPanel parent, MouseEvent evt) {
        // get location of Window
        int thisX = parent.getLocation().x;
        int thisY = parent.getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        parent.setLocation(X, Y);
    }

    public static void onMousePressed(JPanel parent, MouseEvent evt) {
        initialClick = evt.getPoint();
        parent.getComponentAt(initialClick);
    }

    /**
     * Sets the whole UI to use a custom font
     *
     * @param f Font resource to use on the UI
     */
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void setGlobalFont(Font font) {
        Enumeration enume = UIManager.getDefaults().keys();
        while (enume.hasMoreElements()) {
            Object key
                    = enume.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }
}
