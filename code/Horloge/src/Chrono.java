import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.util.TimerTask;


/**
 * @author Oscar Baume & Dorian Gillioz
 * Class Chrono héritant de la Class Subject
 */
public class Chrono implements Subject{
    // nombre de secondes dans une heure
    private final int SECOND_IN_HOUR = 3600;
    // nombre de secondes dans une minutes
    private final int SECOND_IN_MINUTE = 60;
    // Liste des observeurs du chronomètre
    private List<Observer> observers = new ArrayList<>();
    // Timer du chrono
    private Timer timer;
    private int second;
    // id du chrono
    private int id;
    private static int next_id = 1;

    /**
     * Constructeur de chronomètre
     */
    Chrono(){
        init();
        // création du timer avec comme action toute les secondes d'incrementer le total de secondes du chrono
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increment();
            }
        });
        // on donne un id unique au chrono
        id = next_id++;
    }

    /**
     * Méthode setSecond
     * @param s = nouvelle valeur de seconde
     */
    void setSecond(int s){
        second = s;
    }

    /**
     * @return le nombre de seconde entre [0-59]
     */
    int getSecond(){
        return second % SECOND_IN_MINUTE;
    }

    /**
     * @return le nombre de minute du chrono [0-59]
     */
    int getMinute(){
        return (second % SECOND_IN_HOUR) / SECOND_IN_MINUTE;
    }

    /**
     * @return le nombre d'heure du chrono
     */
    int getHour(){
        return second / SECOND_IN_HOUR;
    }

    /**
     * Méthode qui incrémente la valeur du chrono
     */
    void increment(){
        // incrémente
        second++;
        // notifies les observeurs
        notifyObservers();
    }

    /**
     * Méthode qui démarre le chrono
     */
    void start(){
        timer.start();
    }

    /**
     * Méthode qui arrête le chrono
     */
    void stop(){
        timer.stop();
    }

    /**
     * Méthode qui initialise le chronomètre
     */
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

    /**
     *  Méthode qui retourne le nom du chrono
     * @return le nom du chrono
     */
    public String name(){
        return "Chrono #" + id;
    }
}
