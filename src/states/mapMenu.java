package states;

import main.imageManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Map select menu state
 */
public class mapMenu extends BasicGameState {

    private Image cursor;
    private Image cursorTail;
    private Image cursorMiddle;
    private Image test;

    /**
     * Initialise the state. It should load any resources it needs at this stage
     *
     * @param container The container holding the game
     * @param game      The game holding this state
     * @throws SlickException Indicates a failure to initialise a resource for this state
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        test = imageManager.getImage(imageManager.TEST);

        cursor = new Image("res/cursor.png");
        cursorMiddle = new Image("res/cursorMiddle.png");
        cursorTail = new Image("res/cursorTail.png");
        container.setMouseCursor(cursor, 0, 0);
        container.setMouseGrabbed(true);
    }

    /**
     * Render this state to the game's graphics context
     *
     * @param container The container holding the game
     * @param game      The game holding this state
     * @param g         The graphics context to render to
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        float x = container.getWidth() / 2;
        float y = container.getHeight() / 2;
        test.drawCentered(x, y);

        float MouseX = container.getInput().getMouseX();
        float MouseY = container.getInput().getMouseY();
        cursor.rotate(1);
        cursor.drawCentered(MouseX, MouseY);
        cursorMiddle.drawCentered(MouseX, MouseY);
        cursorTail.drawCentered(MouseX, MouseY);
    }

    /**
     * Update the state's logic based on the amount of time that's passed
     *
     * @param container The container holding the game
     * @param game      The game holding this state
     * @param delta     The amount of time that's passed in millisecond since last update
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    /**
     * @see GameState#getID()
     */
    @Override
    public int getID() {
        return 1;
    }
}
