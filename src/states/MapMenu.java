package states;

import main.ImageManager;
import misc.Map;
import misc.MapLoader;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Map select menu state
 */
public class MapMenu extends BasicGameState {

    private Image cursor;
    private Image cursorTail;
    private Image cursorMiddle;

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
//        test = ImagerManager.getImage(ImagerManager.TEST);

        mapList = MapLoader.loadMaps();

        cursor = new Image("res/cursor.png");
        cursorMiddle = new Image("res/cursorMiddle.png");
        cursorTail = new Image("res/cursorTail.png");
//        container.setDefaultMouseCursor();
//        container.setMouseGrabbed(true);
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

//        if (mapList != null) {
//            Map map = mapList.get(selectedMap);
//
//        }

//        float x = container.getWidth() / 2;
//        float y = container.getHeight() / 2;
//        test.drawCentered(x, y);

        int prevBtnX = container.getWidth() / 4 - ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW).getWidth() / 2;
        int prevBtnY = container.getHeight() / 2 - ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW).getHeight() / 2;

        prevBtn = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW), prevBtnX, prevBtnY);
        prevBtn.addListener(source -> selectPrevMap());

        int nextBtnX = container.getWidth() * 3 / 4 - ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW).getWidth() / 2;
        int nextBtnY = container.getHeight() / 2 - ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW).getHeight() / 2;

        nextBtn = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW), nextBtnX, nextBtnY);
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
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            selectPrevMap();
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            selectNextMap();
        }

        if (input.isMousePressed(0)) {
            int x = Mouse.getX();
            int y = 800 - Mouse.getY();

            if (x > 466 && x < 814 && y > 255 && y < 497) {
                Game game2 = new Game(mapList.get(selectedMap));
                game2.init(container, game);
                game.addState(game2);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }

    /**
     * @see GameState#getID()
     */
    @Override
    public int getID() {
        return 2;
    }
}
