import ActiverseEngine.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents the game world for the Dino game.
 */
public class Level extends World {
    private Player player;
    private int timeToSpawn = 4000;
    private final ActiverseSound sound1 = new ActiverseSound("spawn.wav"); // Use WAV file
    private final ActiverseSound sound2 = new ActiverseSound("theme.wav"); // Use WAV file
    private boolean played = false;
    private Timer timer;
    private int score = 0;

    /**
     * Constructs a new Level with the specified dimensions and cell size.
     *
     * @param width    The width of the level in pixels.
     * @param height   The height of the level in pixels.
     * @param cellSize The size of each cell in pixels.
     */
    public Level(int width, int height, int cellSize) {
        super(width, height, cellSize);
        setBackgroundImage("world.png");
        player = new Player(50, getHeight() - 100);
        addObject(player, player.getX(), player.getY());
        if (!played) {
            sound2.play();
            played = true;
        }
        showScore(); // Initial display of score
        timer = new Timer();
        scheduleBrickSpawn();
    }

    /**
     * Schedules a brick to spawn after the current timeToSpawn interval.
     */
    private void scheduleBrickSpawn() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnBrick();
                sound1.play();
                timeToSpawn = Math.max(800, timeToSpawn - 200);
                scheduleBrickSpawn();
            }
        }, timeToSpawn);
    }

    /**
     * Spawns a brick at the bottom left of the world.
     */
    private void spawnBrick() {
        Brick brick = new Brick(0, getHeight() - 50, this); // Pass `this` as Level reference
        addObject(brick, brick.getX(), brick.getY());
        if(player !=null){
            addScore();
        }
        timeToSpawn+=20;        
    }

    /**
     * Increments the score when a brick is removed and updates the displayed score.
     */
    public void addScore() {
        score++;
        showScore(); // Update the displayed score
    }

    /**
     * Displays the current score at the specified position.
     */
    private void showScore() {
        showText(50,50,"Score: " + score);
    }

    /**
     * Starts the game by starting the timer.
     */
    @Override
    public void start() {
        super.start(); // Start the game loop
    }

    /**
     * Stops the game by stopping the timer.
     */
    @Override
    public void stop() {
        showText(75,75,"You died! Score= " + score);
        super.stop(); // Stop the game loop
        timer.cancel(); // Stop the brick spawning timer
    }  
}