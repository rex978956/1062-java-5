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
        FontSet.draw("Demo58", 550, 100, 73);
        FontSet.draw("Demo50", 550, 200, 58);
        FontSet.draw("Demo42", 550, 300, 42);
        FontSet.draw("Demo34", 550, 400, 34);
        FontSet.draw("Demo16", 550, 500, 26);
        FontSet.draw("Demo18", 550, 600, 18);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

    }

    public int getID() {
        return 99;
    }
}
