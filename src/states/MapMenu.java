package states;

import main.ImageManager;
import misc.FontSet;
import misc.Map;
import misc.MapLoader;
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

//    private Image cursor;
//    private Image cursorTail;
//    private Image cursorMiddle;
    // TODO: Add Choose Map Image

    private Image title, frame;

    private MouseOverArea prevBtn, nextBtn;
    private MouseOverArea mapPreview;

    private ArrayList<Map> mapList;

    private int selectedMap;

    private Music sevenMusic;
    private Image background;

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

    private void clickButtons(GameContainer container) {
        int prevBtnX = container.getWidth() / 4 - ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW).getWidth() / 2;
        int prevBtnY = container.getHeight() / 2 - ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW).getHeight() / 2;

        prevBtn = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_LARROW), prevBtnX, prevBtnY);
        prevBtn.addListener(source -> selectPrevMap());

        int nextBtnX = container.getWidth() * 3 / 4 - ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW).getWidth() / 2;
        int nextBtnY = container.getHeight() / 2 - ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW).getHeight() / 2;

        nextBtn = new MouseOverArea(container, ImageManager.getImage(ImageManager.MENU_BUTTON_RARROW), nextBtnX, nextBtnY);
        nextBtn.addListener(source -> selectNextMap());
    }

    private void clickSelectGame(GameContainer container, StateBasedGame game) {
        int previewX = container.getWidth() / 2 - mapList.get(selectedMap).getPreview().getWidth() / 2;
        int previewY = container.getHeight() / 2 - mapList.get(selectedMap).getPreview().getHeight() / 2;
        mapPreview = new MouseOverArea(container, mapList.get(selectedMap).getPreview(), previewX, previewY);
        mapPreview.addListener(source -> {
            Game game2 = new Game(mapList.get(selectedMap));
            try {
                game2.init(container, game);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            game.addState(game2);
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            sevenMusic.stop();
        });
    }

    /**
     * Initialise the state. It should load any resources it needs at this stage
     *
     * @param container The container holding the game
     * @param game      The game holding this state
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        title = ImageManager.getImage(ImageManager.MENU_TEXT_CHOOSEMAP);
        frame = ImageManager.getImage(ImageManager.MAP_MENU_FRAME);

        background = ImageManager.getImage(ImageManager.MENU_BACKGROUND);
        mapList = MapLoader.loadMaps();
        sevenMusic = new Music("res/sound/Seven.ogg");

//        cursor = new Image("res/cursor.png");
//        cursorMiddle = new Image("res/cursorMiddle.png");
//        cursorTail = new Image("res/cursorTail.png");
//
//        container.setDefaultMouseCursor();
//        container.setMouseGrabbed(true);
        clickButtons(container);
        clickSelectGame(container, game);
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
        background.draw(0, 0);

        title.draw(640 - title.getWidth() / 2, 150);

//        int frameX = container.getWidth() / 2 ;
//        int frameY = container.getHeight() / 2;
        frame.drawCentered(container.getWidth() / 2, container.getHeight() / 2);
        frame.draw((0 - frame.getHeight() * 3 / 4) - 75, 195);
        frame.draw((container.getWidth() - frame.getWidth() / 4) - 10, 195);

        mapPreview.render(container, g);
        mapList.get(selectedMap).getPreview().drawCentered(container.getWidth() / 2, container.getHeight() / 2);

        if (selectedMap > 0) {
            mapList.get(selectedMap - 1).getPreview().draw(0 - mapList.get(selectedMap).getPreview().getWidth() * 3 / 4, 225);
        }

        if (selectedMap < mapList.size() - 1) {
            mapList.get(selectedMap + 1).getPreview().draw(container.getWidth() - mapList.get(selectedMap).getPreview().getWidth() / 4, 225);
        }

        Map map2 = mapList.get(selectedMap);
        FontSet.drawStylus(map2.getName(), 480, 490, 22, Color.decode("#FFAA00"));

        prevBtn.render(container, g);
        nextBtn.render(container, g);

//        float MouseX = container.getInput().getMouseX();
//        float MouseY = container.getInput().getMouseY();
//        cursor.rotate(1);
//        cursor.drawCentered(MouseX, MouseY);
//        cursorMiddle.drawCentered(MouseX, MouseY);
//        cursorTail.drawCentered(MouseX, MouseY);
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
        Input input = container.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
            sevenMusic.stop();
        }

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            selectPrevMap();
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            selectNextMap();
        }

        if (input.isKeyPressed(Input.KEY_F11)) {
            clickButtons(container);
            clickSelectGame(container, game);
        }

        while (!sevenMusic.playing()) {
            sevenMusic.play();
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