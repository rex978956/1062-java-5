package states;

import misc.FontSet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FontDemo extends BasicGameState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        FontSet.drawAkrobat("Demo58", 550, 100, 73);
        FontSet.drawAkrobat("Demo50", 550, 200, 58);
        FontSet.drawAkrobat("Demo42", 550, 300, 42);
        FontSet.drawAkrobat("Demo34", 550, 400, 34);
        FontSet.drawAkrobat("Demo16", 550, 500, 26);
        FontSet.drawAkrobat("Demo18", 550, 600, 18);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

    }

    public int getID() {
        return 99;
    }
}
