import java.awt.*;

/**
 * @author Oscar Baume & Dorian Gillioz
 */
public class Roman extends Analog {
    /**
     * Constructeur d'horloge à chiffre romains
     * @param subject = sujet de l'horloge
     */
    Roman(Chrono subject) {
        // on appelle le constructeur d'horloge analogue
        super(subject, "img/roman.jpg", Color.YELLOW, Color.GRAY, Color.BLACK);
    }
}
