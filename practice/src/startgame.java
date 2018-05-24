import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class startgame extends StateBasedGame {

    private startgame(String title){
        super(title);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new gameState1());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new startgame("Game Title"));
        app.setDisplayMode(800,600,false);
        app.setTargetFrameRate(60);
        app.setAlwaysRender(true);
        app.start();
    }
}