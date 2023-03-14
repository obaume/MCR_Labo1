import javax.swing.*;
import java.awt.*;

public class Digital extends Clock{
    JLabel label;
    Digital(Chrono subject) {
        super(subject);
        setBackground(Color.gray);
        label = new JLabel(subject.name() + ": 00h 00m 00s");
        this.setLayout(new GridBagLayout());
        add(label);
        label.setText(subject.name() + ": " + hour +"h "+ minute +"m "+ second +"s");
    }
    @Override
    public void update(){
        super.update();
        label.setText(subject.name() + " : " + hour +"h "+ minute +"m "+ second +"s");
    }
}
