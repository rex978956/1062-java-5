package test;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;


public class Circle1 {
    private Image circle1 = null;
    private Image hitcircleoverlay = null;
    private Image hitcircleselect = null;
    private Image approachcircle = null;
    private Color color = null;
    private Circle circlebg = null;
    private float x, y;
    public Circle1(float x,float y,int num,Color color) throws SlickException {
        this.x = x;
        this.y = y;
        this.color = color;
        circlebg = new Circle(0,0,100);
        circle1 = new Image("res/default-"+num+".png");
        hitcircleoverlay = new Image("res/hitcircleoverlay.png");
        hitcircleselect = new Image("res/hitcircleselect.png");
        approachcircle = new Image("res/asd.png");
    }
    void drawmap(){
        float cycleX = x+circle1.getWidth()/2;
        float cycleY = y+circle1.getHeight()/2;
        approachcircle.draw(cycleX-approachcircle.getWidth()/2,cycleY-approachcircle.getHeight()/2,
                approachcircle.getWidth(),approachcircle.getHeight(),color);
        hitcircleoverlay.draw(cycleX-hitcircleoverlay.getWidth()/2,cycleY-hitcircleoverlay.getHeight()/2,
                hitcircleoverlay.getWidth(),hitcircleoverlay.getHeight());
//        hitcircleselect.draw(cycleX-hitcircleselect.getWidth()/2,cycleY-hitcircleselect.getHeight()/2,
//                hitcircleselect.getWidth(),hitcircleselect.getHeight(),color);
        circle1.draw(x,y,circle1.getWidth(),circle1.getHeight());
    }


}
