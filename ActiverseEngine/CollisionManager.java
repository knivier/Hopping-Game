package ActiverseEngine;

import java.awt.*;

/**
 * Manages collision detection between actors in the world.
 * This class provides a method to check if two actors intersect.
 *
 * @author Knivier
 */
public class CollisionManager {

    /**
     * Checks if two actors intersect.
     *
     * @param a The first actor.
     * @param b The second actor.
     * @return true if the bounding boxes of the two actors intersect, false otherwise.
     */
    public static boolean intersects(Actor a, Actor b) {
        Rectangle r1 = new Rectangle(a.getX(), a.getY(), a.getImage().getImage().getWidth(null), a.getImage().getImage().getHeight(null));
        Rectangle r2 = new Rectangle(b.getX(), b.getY(), b.getImage().getImage().getWidth(null), b.getImage().getImage().getHeight(null));

        return r1.intersects(r2);
    }
}
