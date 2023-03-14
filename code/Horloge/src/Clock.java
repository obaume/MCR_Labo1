import javax.swing.*;
import java.awt.*;

public class Clock extends JPanel implements Observer{
    Chrono subject;
    protected int second, minute, hour;
    private final int width=200, height= 200;
    Clock(Chrono subject){
        this.subject = subject;
        this.subject.attach(this);
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
        setPreferredSize(new Dimension(width,height));
    }
    @Override
    public void update(){
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }


}
