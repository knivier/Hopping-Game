package ActiverseEngine;

import javax.swing.*;
import java.awt.*;

/**
 * Provides utility methods to start and stop the Activerse application.
 * This class contains static methods to manage the application lifecycle.
 */
public class Activerse {
    private static World currentWorld;
    private static JFrame frame;
    private static GameLoop gameLoop;
    /**
     * Starts the Activerse application with the specified world.
     *
     * @param world The world to start the application with.
     */
    public static void start(World world) {
        currentWorld = world;

        gameLoop = new GameLoop(currentWorld);
        new Thread(gameLoop).start();
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Activerse Instance v1.2.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(currentWorld, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            currentWorld.start(); // Start the world
        });
    }

    /**
     * Sets the world to the inputted world.
     *
     * @param world The new world to be set.
     */
    public static void setWorld(World world) {
        if (currentWorld != null) {
            stop(currentWorld);
            frame.getContentPane().remove(currentWorld);
        }
        currentWorld = world;
        frame.getContentPane().add(currentWorld, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        if (gameLoop != null) {
            gameLoop = new GameLoop(currentWorld);
            new Thread(gameLoop).start();
        }
        currentWorld.start();
    }

    /**
     * Stops the Activerse application by stopping the specified world.
     *
     * @param world The world to stop.
     */
    public static void stop(World world) {
        if (world != null) {
            world.stop();
        }
    }
}