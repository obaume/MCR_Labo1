import javax.swing.*;
import java.awt.*;

public class Analog extends Clock {
    private Color secondHandColor,minuteHandColor,hourHandColor;
    private Rectangle secondHand,minuteHand,hourHand;
    private Image bgImage;

    Analog(Chrono subject,String filenameImageClock, Color secondHandColor, Color minuteHandColor, Color hourHandColor){
        super(subject);
        this.secondHandColor = secondHandColor;
        this.minuteHandColor = minuteHandColor;
        this.hourHandColor = hourHandColor;
        secondHand = new Rectangle();
        minuteHand = new Rectangle();
        hourHand = new Rectangle();
        bgImage = Toolkit.getDefaultToolkit().getImage(filenameImageClock);
        bgImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        getGraphics().drawImage(bgImage,0,0,null);
    }
    @Override
    public void update(){
        super.update();

    }
    void drawHand(Rectangle r, Color c, int angle, int length){
        final int CENTRE_POSITION_X = 100,CENTRE_POSITION_Y = 100;
        int deltaX, deltaY;

        getGraphics().drawLine(CENTRE_POSITION_X,CENTRE_POSITION_Y,);
    }
}
