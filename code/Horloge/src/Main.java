import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        int nbClocks = Integer.parseInt(args[0]);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createGUI(nbClocks);
            }
        });
    }

    private void createGUI(int nbClocks) {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        JPanel[] panels = new JPanel[nbClocks + 1];

        Chrono[] chronos = new Chrono[nbClocks];

        LinkedList<JButton> startBtns = new LinkedList<>();
        LinkedList<JButton> stopBtns = new LinkedList<>();
        LinkedList<JButton> resetBtns = new LinkedList<>();
        LinkedList<JButton> romanBtns = new LinkedList<>();
        LinkedList<JButton> arabicBtns = new LinkedList<>();
        LinkedList<JButton> digitalBtns = new LinkedList<>();

        JButton romanAll = new JButton("Cadran romain");
        JButton arabicAll = new JButton("Cadran arabe");
        JButton digitalAll = new JButton("Numérique");


        for (int i = 0; i < nbClocks; ++i) {
            // Création des boutons
            chronos[i] = new Chrono();
            startBtns.add(new JButton("Démarrer"));
            stopBtns.add(new JButton("Arrêter"));
            resetBtns.add(new JButton("Réinitialiser"));
            romanBtns.add(new JButton("Cadran romain"));
            arabicBtns.add(new JButton("Cadran arabe"));
            digitalBtns.add(new JButton("Numérique"));

            // Action sur les boutons
            int finalI = i;
            startBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].start();
                }
            });

            stopBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].stop();
                }
            });

            resetBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].init();
                }
            });

            romanBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Clock roman = new Analog(chronos[finalI], "romain.jpg",
                                             Color.GRAY, Color.YELLOW, Color.BLACK);
                }
            });

            arabicBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Clock arab = new Analog(chronos[finalI], "arab.jpg",
                            Color.BLUE, Color.RED, Color.BLACK);
                }
            });

            digitalBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Clock digital = new Digital(chronos[finalI]);
                }
            });

            // Ajout des boutons dans l'affichage
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1, 6, 5, 5));
            panels[i] = new JPanel();
            panels[i].add(new JLabel(chronos[i].name()));
            panels[i].add(startBtns.get(i));
            panels[i].add(stopBtns.get(i));
            panels[i].add(resetBtns.get(i));
            panels[i].add(romanBtns.get(i));
            panels[i].add(arabicBtns.get(i));
            panels[i].add(digitalBtns.get(i));
            contentPane.add(panels[i]);
        }

        // Bouton pour les actions sur tous les chronos
        romanAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        arabicAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        digitalAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Dernière ligne
        panels[nbClocks] = new JPanel();
        panels[nbClocks].add(new JLabel("Tous les chronos"));
        panels[nbClocks].add(romanAll);
        panels[nbClocks].add(arabicAll);
        panels[nbClocks].add(digitalAll);
        contentPane.add(panels[nbClocks]);

        // Ptits paramètres pour l'affichage de la fenêtre
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(700, 50 * (nbClocks + 1));
        setLocationByPlatform(true);
        setVisible(true);
    }
}