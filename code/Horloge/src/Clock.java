import javax.swing.*;
import java.awt.*;

/**
 * @author Oscar Baume & Dorian Gillioz
 */
public class Clock extends JPanel implements Observer{
    // sujet de l'horloge
    Chrono subject;
    // variable pour les valeurs des secondes, minutes et heures de l'horloge
    protected int second, minute, hour;
    // dimension de l'horloge
    private final int width=200, height= 200;

    /**
     * Constructeur d'horloges
     * @param subject : chrono pour lequel l'horloge doit afficher la valeur
     */
    Clock(Chrono subject){
        this.subject = subject;
        // on s'attache au sujet
        this.subject.attach(this);
        // on met à jour les valeurs de l'horloge
        update();
        // on définit la taille voulu pour l'horloge
        setPreferredSize(new Dimension(width,height));
    }
    @Override
    public void update(){
        // on met à jour les secondes, minutes et heures
        second = subject.getSecond();
        minute = subject.getMinute();
        hour = subject.getHour();
    }


}
