import javax.swing.*;
import java.awt.*;

/**
 * @author Oscar Baume & Dorain Gillioz
 */
public class Digital extends Clock{
    // label qui affiche l'heure
    JLabel label;

    /**
     * Constructeur de l'horloge digital
     * @param subject = sujet de l'horloge
     */
    Digital(Chrono subject) {
        // on appele le constructeur d'horloge
        super(subject);
        // on mets le background en gris
        setBackground(Color.gray);
        // on crée un Jlabel
        label = new JLabel();
        // on utilise un gridbaglayout pour que le label soit centré sans faire + de manip
        this.setLayout(new GridBagLayout());
        // on ajoute le label au JPanel
        add(label);
    }
    @Override
    public void update(){
        super.update();
        // on met à jour le contenu du label
        label.setText(subject.name() + " : " + hour +"h "+ minute +"m "+ second +"s");
    }
}
