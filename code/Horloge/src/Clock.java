import javax.swing.*;
import java.awt.*;

public class Clock extends JFrame implements Observer {
    protected JPanel panel;
    protected TextField id;
    Chrono subject;
    protected int second, minute, hour;
    Clock(Chrono subject){
        this.subject = subject;
        this.subject.attach(this);
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();

        panel.setBounds(0,0,200,200);
        id = new TextField();
        id.setText(subject.name());
        panel.add(id);
    }
    @Override
    public void update(){
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }

}
