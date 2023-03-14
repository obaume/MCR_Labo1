import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.util.TimerTask;


public class Chrono implements Subject{
    private final int SECOND_IN_HOUR = 3600;
    private final int SECOND_IN_MINUTE = 60;
    private final int DELAY_IN_MS = 1000;
    private List<Observer> observers = new ArrayList<>();

    private Timer timer;
    private int second;
    private int id;
    private static int next_id = 1;

    Chrono(){
        init();
        timer = new Timer(DELAY_IN_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increment();
            }
        });
        id = next_id++;
    }

    void setSecond(int s){
        second = s;
    }
    int getSecond(){
        return second % SECOND_IN_MINUTE;
    }
    int getMinute(){
        return (second % SECOND_IN_HOUR) / SECOND_IN_MINUTE;
    }
    int getHour(){
        return second / SECOND_IN_HOUR;
    }

    void increment(){
        second++;
        notifyObservers();
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
        second = 0;
        notifyObservers();
    }
    void init(){
        second = 0;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for(Observer o : observers){
            o.update();
        }
    }

    public String name(){
        return "Chrono #" + id;
    }
}
