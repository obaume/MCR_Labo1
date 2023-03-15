import java.awt.*;

/**
 * @author Oscar Baume & Dorian Gillioz
 */
public class Arab extends Analog {
    /**
     * Constructeur d'horloges à chiffres arabes
     * @param subject = sujet de l'horloge
     */
    Arab(Chrono subject) {
        // on appelle le constructeur d'horloge analogue
        super(subject, "img/arab.jpg", Color.RED, Color.BLUE, Color.BLACK);
    }

}
