package states;

import main.ImagerManager;
import map.Map;
import map.MapLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Map select menu state
 */
public class MapMenu extends BasicGameState {

    private Image cursor;
    private Image cursorTail;
    private Image cursorMiddle;
    private Image test;

    private MouseOverArea prevBtn, nextBtn;

    private ArrayList<Map> mapList;

    private int selectedMap;

    private void selectPrevMap() {
        if (selectedMap > 0) {
            selectedMap--;
        } else {
            selectedMap = mapList.size() - 1;
        }
    }

    private void selectNextMap() {
        if (selectedMap < mapList.size() - 1) {
            selectedMap++;
        } else {
            selectedMap = 0;
        }
    }

    /**
     * Initialise the state. It should load any resources it needs at this stage
     *
     * @param container The container holding the game
     * @param game      The game holding this state
     * @throws SlickException Indicates a failure to initialise a resource for this state
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        test = ImagerManager.getImage(ImagerManager.TEST);

        mapList = MapLoader.loadMaps();

        cursor = new Image("res/cursor.png");
        cursorMiddle = new Image("res/cursorMiddle.png");
        cursorTail = new Image("res/cursorTail.png");
//        container.setDefaultMouseCursor();
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
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.setBackground(Color.red);

        if (mapList != null) {
            Map map = mapList.get(selectedMap);

        }

        float x = container.getWidth() / 2;
        float y = container.getHeight() / 2;
        test.drawCentered(x, y);

        int prevBtnX = container.getWidth() / 4 - ImagerManager.getImage(ImagerManager.BTN_ARROW_LEFT).getWidth() / 2;
        int prevBtnY = container.getHeight() / 2 - ImagerManager.getImage(ImagerManager.BTN_ARROW_LEFT).getHeight() / 2;

        prevBtn = new MouseOverArea(container, ImagerManager.getImage(ImagerManager.BTN_ARROW_LEFT), prevBtnX, prevBtnY);
        prevBtn.addListener(source -> selectPrevMap());

        int nextBtnX = container.getWidth() * 3 / 4 - ImagerManager.getImage(ImagerManager.BTN_ARROW_RIGHT).getWidth() / 2;
        int nextBtnY = container.getHeight() / 2 - ImagerManager.getImage(ImagerManager.BTN_ARROW_RIGHT).getHeight() / 2;

        nextBtn = new MouseOverArea(container, ImagerManager.getImage(ImagerManager.BTN_ARROW_RIGHT), nextBtnX, nextBtnY);
        nextBtn.addListener(source -> selectNextMap());

        prevBtn.render(container, g);
        nextBtn.render(container, g);

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
