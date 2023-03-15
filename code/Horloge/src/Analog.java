import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.*;

/**
 * @author Oscar Baume & Dorian Gillioz
 */
public class Analog extends Clock {
    // Couleur des aiguilles de l'horloges
    private Color secondHandColor,minuteHandColor,hourHandColor;
    // image de l'horloge
    private final Image bgImage;

    /**
     * Constructeur d'horloge analogues
     * @param subject = sujet de l'horloge
     * @param filenameImageClock = nom de fichier de l'image de l'horloge
     * @param secondHandColor = couleur de l'aiguille des secondes
     * @param minuteHandColor = couleur de l'aiguille des minutes
     * @param hourHandColor = couleur de l'aiguille des heures
     */
    Analog(Chrono subject,String filenameImageClock, Color secondHandColor, Color minuteHandColor, Color hourHandColor){
        super(subject);
        this.secondHandColor = secondHandColor;
        this.minuteHandColor = minuteHandColor;
        this.hourHandColor = hourHandColor;

        // on charge et redimensionne l'image
        bgImage = Toolkit.getDefaultToolkit().getImage(filenameImageClock)
                .getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);
    }
    @Override
    public void update(){
        super.update();
        // on redessine le JPanel
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        // largeur des aiguilles de l'horloge
        final int THICKNESS_SEC = 2,THICKNESS_MIN = 4,THICKNESS_HOUR = 6;
        // longeur des aiguilles de l'horloges
        final int LENGTH_SEC = 70,LENGTH_MIN = 50,LENGTH_HOUR = 30;
        // angle des aiguilles
        double angle;

        // dessine l'image
        g.drawImage(bgImage,0,0,this);

        // dessine le nom du chrono
        g.drawString(subject.name(), 80, 120);
        // chaque seconde déplace l'aiguille de 6°
        angle = second * 6;
        // desine la second hand
        paintHand(g, secondHandColor,THICKNESS_SEC,LENGTH_SEC,angle);

        // chaque minute déplace l'aiguille de 6°
        angle = minute * 6;
        // dessine les minutes
        paintHand(g,minuteHandColor,THICKNESS_MIN,LENGTH_MIN,angle);

        // chaque heure déplace l'aiguille de 30° et chaque minute déplace l'aiguille d'un demi dégré
        angle = hour * 30 + 0.5 * minute;
        // dessine les heures
        paintHand(g,hourHandColor,THICKNESS_HOUR,LENGTH_HOUR,angle);
    }

    private void paintHand(Graphics g, Color c, int thickness, int length, double angle){
        // Coordonnées du centre de l'horloge
        final int CENTRE_POSITION_X = 100,CENTRE_POSITION_Y = 100;
        double[] deltas;
        // on récupère le déplacement en x et y
        deltas = getDelta(angle);
        // on allonge le déplacement selon la longueur voulu
        deltas[0] *= length;
        deltas[1] *= length;
        // on donne la couleur à l'aiguille
        g.setColor(c);
        // on défini la largeur de l'aiguille
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        // on déssine l'aiguille
        g.drawLine(CENTRE_POSITION_X,CENTRE_POSITION_Y,
                CENTRE_POSITION_X + (int)deltas[0],CENTRE_POSITION_Y + (int)deltas[1]);
    }

    /**
     * Méthode qui retourne le distance que le bout de l'aiguille fait en x et y
     * @param angle
     * @return
     */
    public double[] getDelta(double angle){
        double a = angle;
        // on formate l'angle
        a %= 360;
        // on passe l'angle en radian
        a *= (Math.PI/180);
        // on retourne les distances
        return new double[]{Math.sin(a), -Math.cos(a)};
    }
}
