import ActiverseEngine.*;

/**
 * Represents the player-controlled dino in the game.
 */
public class Player extends Actor {

    private static final double JUMP_STRENGTH = 7; // Adjusted jump strength for higher jump
    private static final double GRAVITY = 0.2; // Adjusted gravity for slower fall
    private boolean isJumping;
    private double ySpeed;

    /**
     * Constructs a new Player object at the specified position.
     *
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     */
    public Player(int x, int y) {
        setLocation(x, y);
        setImage(new ActiverseImage("mainplayer.png")); // Set image for the player
        ySpeed = 0;
        isJumping = false;
    }

    /**
     * Performs the player's actions.
     * Handles movement and jumping based on player input.
     */
    @Override
    public void act() {
        handleMovement();
        handleJumping();
        checkIntersections();
    }

    /**
     * Handles movement based on player input.
     */
    private void handleMovement() {
        if (keyIsDown('A')) { // Check for 'A' key press
            move(-2); // Move left
        }
        if (keyIsDown('D')) { // Check for 'D' key press
            move(2); // Move right
        }
    }

    /**
     * Handles jumping based on player input.
     */
    private void handleJumping() {
        if (keyIsDown('W') && !isJumping) { // Check for 'W' key press and not already jumping
            ySpeed = -JUMP_STRENGTH;
            isJumping = true;
        }

        // Apply gravity and update position
        ySpeed += GRAVITY;
        setLocation(getX(), (int) (getY() + ySpeed));

        // Check for ground (bottom of screen) collision
        if (getY() >= getWorld().getHeight() - getImage().getImage().getHeight(null)) {
            ySpeed = 0;
            setLocation(getX(), getWorld().getHeight() - getImage().getImage().getHeight(null));
            isJumping = false; // Player has landed
        }
    }

    /**
     * Checks intersections with other actors (specifically Brick).
     * Removes the player if it intersects with a brick.
     */
    private void checkIntersections() {
        for (Actor actor : getWorld().getActors()) {
            if (actor instanceof Brick && actor.intersects(this)) {
                setImage(new ActiverseImage("deadplayer.png"));
                getWorld().removeObject(this);
                getWorld().stop(); // Remove the player if it intersects with a brick
                break;
            }
        }
    }
}
