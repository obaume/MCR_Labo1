import javax.swing.*;
import java.awt.*;

public class Clock extends JPanel implements Observer{
    Chrono subject;
    protected int second, minute, hour;
    Clock(Chrono subject){
        this.subject = subject;
        this.subject.attach(this);
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
        setBounds(0,0,200,200);
    }
    @Override
    public void update(){
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }


}
