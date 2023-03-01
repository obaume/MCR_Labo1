import javax.swing.*;

public class Clock extends JFrame implements Observer {
    Chrono subject;
    protected int second, minute, hour;
    Clock(Chrono subject){
        this.subject = subject;
        this.subject.attach(this);
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }
    @Override
    public void update(){
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }
}
