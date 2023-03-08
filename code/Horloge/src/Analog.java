import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.*;

public class Analog extends Clock {
    private Color secondHandColor,minuteHandColor,hourHandColor;
    private Image bgImage;

    Analog(Chrono subject,String filenameImageClock, Color secondHandColor, Color minuteHandColor, Color hourHandColor){
        super(subject);
        this.secondHandColor = secondHandColor;
        this.minuteHandColor = minuteHandColor;
        this.hourHandColor = hourHandColor;
        bgImage = Toolkit.getDefaultToolkit().getImage(filenameImageClock);
        bgImage.getScaledInstance(200,200,Image.SCALE_DEFAULT);
        panel.getGraphics().drawImage(bgImage,0,0,null);
        id.setBounds(110,80,20,10);
    }
    @Override
    public void update(){
        super.update();
        getGraphics().clearRect(0,0,200,200);
        // secondes
        drawHand(secondHandColor,360/second,80,1);
        // minutes
        drawHand(minuteHandColor,360/minute,60,2);
        // heures
        drawHand(hourHandColor,360/hour,40,3);
    }
    void drawHand(Color c, double angle, int length, int thickness){
        final int CENTRE_POSITION_X = 100,CENTRE_POSITION_Y = 100;
        double deltaX=0, deltaY=0;

        panel.getGraphics().setColor(c);
        ((Graphics2D)panel.getGraphics()).setStroke(new BasicStroke(thickness));


        if (angle > 0 && angle < 90){
            deltaX = Math.sin(angle);
            deltaY = Math.cos(angle);
        }
        if(angle > 90 && angle < 180){
            deltaX = Math.cos(angle-90);
            deltaY = -Math.sin(angle-90);
        }
        if(angle > 180 && angle < 270){
            deltaX = -Math.sin(angle-180);
            deltaY = -Math.cos(angle-180);
        }
        if(angle > 270 && angle < 360){
            deltaX = Math.cos(angle-270);
            deltaY = -Math.sin(angle-270);
        }
        deltaX *= length;
        deltaY *= length;

        panel.getGraphics().drawLine( CENTRE_POSITION_X,
                                CENTRE_POSITION_Y,
                            CENTRE_POSITION_X + (int)deltaX,
                            CENTRE_POSITION_Y + (int)deltaY);
    }
}
