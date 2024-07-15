import ActiverseEngine.*;

/**
 * Represents a brick object in the game world.
 */
public class Brick extends Actor {
    private Level level; // Reference to the Level class

    // Constructor for Brick class
    public Brick(int x, int y, Level level) {
        this.level = level; // Store reference to Level class
        // Set initial position of the brick
        setLocation(x, y);
        // Load and set the image for the brick
        ActiverseImage brickImage = new ActiverseImage("brick.png");
        setImage(brickImage); // Set image for the brick
    }

    @Override
    public void act() {
        // Move the brick to the right
        move(1);

        // Remove the brick if it moves out of the world
        if (getX() > getWorld().getPreferredSize().width - 100) {
            getWorld().removeObject(this); // Remove brick from world
        }
    }
}