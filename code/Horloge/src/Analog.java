import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.*;

public class Analog extends Clock {
    JLabel label;
    private Color secondHandColor,minuteHandColor,hourHandColor;
    private Image bgImage;
    private String filenameImageClock;

    Analog(Chrono subject,String filenameImageClock, Color secondHandColor, Color minuteHandColor, Color hourHandColor){
        super(subject);
        this.secondHandColor = secondHandColor;
        this.minuteHandColor = minuteHandColor;
        this.hourHandColor = hourHandColor;
        this.filenameImageClock = filenameImageClock;

        bgImage = Toolkit.getDefaultToolkit().getImage(filenameImageClock)
                .getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);

        label = new JLabel(subject.name());
        Dimension size = label.getPreferredSize();
        label.setBounds(80,120,size.width + 10,size.height);
        this.setLayout(null);
        add(label);

    }
    @Override
    public void update(){
        super.update();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        final int THICKNESS_SEC = 2,THICKNESS_MIN = 4,THICKNESS_HOUR = 6;
        final int LENGTH_SEC = 70,LENGTH_MIN = 50,LENGTH_HOUR = 30;
        double angle;

        g.drawImage(bgImage,0,0,this);

        // desine la second hand
        angle = second * 6;
        paintHand(g, secondHandColor,THICKNESS_SEC,LENGTH_SEC,angle);

        // dessine les minutes
        angle = minute * 6;
        paintHand(g,minuteHandColor,THICKNESS_MIN,LENGTH_MIN,angle);

        // dessine les heures
        angle = hour * 30 + 0.5 * minute;
        paintHand(g,hourHandColor,THICKNESS_HOUR,LENGTH_HOUR,angle);
    }

    private void paintHand(Graphics g, Color c, int thickness, int length, double angle){
        final int CENTRE_POSITION_X = 105,CENTRE_POSITION_Y = 105;
        double[] deltas;
        deltas = getDelta(angle);
        deltas[0] *= length;
        deltas[1] *= length;
        g.setColor(c);
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.drawLine(CENTRE_POSITION_X,CENTRE_POSITION_Y,
                CENTRE_POSITION_X + (int)deltas[0],CENTRE_POSITION_Y + (int)deltas[1]);
    }
    public double[] getDelta(double angle){
        angle %= 360;
        angle *= (Math.PI/180);
        return new double[]{Math.sin(angle), -Math.cos(angle)};
    }
}
