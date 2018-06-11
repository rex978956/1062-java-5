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
        FontSet.drawAkrobat("Demo58", 0, 100, 73);
        FontSet.drawAkrobat("Demo50", 0, 200, 58);
        FontSet.drawAkrobat("Demo42", 0, 300, 42);
        FontSet.drawAkrobat("Demo34", 0, 400, 34);
        FontSet.drawAkrobat("Demo16", 0, 500, 26);
        FontSet.drawAkrobat("Demo18", 0, 600, 18);

        FontSet.drawButterScotch("Demo16", 250, 500, 26);
        FontSet.drawButterScotch("Demo18", 250, 600, 18);

        FontSet.drawFontaine("Demo16", 500, 500, 26);
        FontSet.drawFontaine("Demo18", 500, 600, 18);

        FontSet.drawLucinda("Demo16", 750, 500, 26);
        FontSet.drawLucinda("Demo18", 750, 600, 18);


        FontSet.drawStylus("Demo16", 1000, 500, 26);
        FontSet.drawStylus("Demo18", 1000, 600, 18);


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

    }

    public int getID() {
        return 99;
    }
}
