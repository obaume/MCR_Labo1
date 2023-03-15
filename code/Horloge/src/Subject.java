/**
 * @author Oscar Baume & Dorian Gillioz
 */
public interface Subject {
    /**
     * Méthode qui attache un observeur au sujet
     * @param o = observeur à attacher au sujet
     */
    void attach(Observer o);

    /**
     * Méthode qui détache un observeur du sujet
     * @param o = observeur à détacher du sujet
     */
    void detach(Observer o);

    /**
     * Méthode qui notifie tous les observeurs qu'ils doivent se mettre à jour
     */
    void notifyObservers();
}
