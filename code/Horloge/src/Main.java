import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.*;

/**
 * @author Oscar Baume & Dorian Gillioz
 * Class Main
 *
 */
public class Main extends JFrame {
    Dimension dimension = new Dimension(220,240);
    public static void main(String[] args) {
        int nbChronos = Integer.parseInt(args[0]);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createGUI(nbChronos);
            }
        });
    }

    /**
     * @author Dorian Gillioz
     * @param nbChronos = nombre de chronomètre voulu
     * Cette méthode va créer l'affichage principale de l'app
     */
    private void createGUI(int nbChronos) {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        JPanel[] panels = new JPanel[nbChronos + 1];

        Chrono[] chronos = new Chrono[nbChronos];

        LinkedList<JButton> startBtns = new LinkedList<>();
        LinkedList<JButton> stopBtns = new LinkedList<>();
        LinkedList<JButton> resetBtns = new LinkedList<>();
        LinkedList<JButton> romanBtns = new LinkedList<>();
        LinkedList<JButton> arabicBtns = new LinkedList<>();
        LinkedList<JButton> digitalBtns = new LinkedList<>();

        JButton romanAll = new JButton("Cadran romain");
        JButton arabicAll = new JButton("Cadran arabe");
        JButton digitalAll = new JButton("Numérique");


        for (int i = 0; i < nbChronos; ++i) {
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
            // Ajout du Listener pour démarrer un chronos
            startBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].start();
                }
            });

            // Ajout du Listener pour arrêter un chronos
            stopBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].stop();
                }
            });

            // Ajout du Listener pour remettre à 0 le chronos
            resetBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chronos[finalI].init();
                }
            });

            // Ajout du Listener pour ajouter une horloge avec nombre romains
            romanBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Création de l'observer
                    Roman r = new Roman(chronos[finalI]);
                    JFrame frame = new JFrame();
                    frame.setSize(dimension);
                    frame.setLocationRelativeTo(null);
                    frame.add(r);
                    frame.setResizable(false);
                    frame.revalidate();
                    frame.setVisible(true);
                    // Ajout d'un Listener pour la fermeture de la frame
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            // quand on ferme la frame on detache l'observer du chrono
                            chronos[finalI].detach(r);
                        }
                    });
                }
            });

            // Ajout du Listener pour ajouter une horloge avec nombre arabe
            arabicBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Création de l'observeur
                    Arab a = new Arab(chronos[finalI]);
                    JFrame frame = new JFrame();
                    frame.setSize(dimension);
                    frame.setLocationRelativeTo(null);
                    frame.add(a);
                    frame.setResizable(false);
                    frame.revalidate();
                    frame.setVisible(true);
                    // Ajout du Listener pour la fermeture de la frame
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            // Quand on ferme la frame on détache l'observeur du chrono
                            chronos[finalI].detach(a);
                        }
                    });
                }
            });

            // Ajout du Listener pour ajouter une horloge digital
            digitalBtns.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Création de l'observeur
                    Digital d = new Digital(chronos[finalI]);
                    JFrame frame = new JFrame();
                    frame.setSize(dimension);
                    frame.setLocationRelativeTo(null);
                    frame.add(d);
                    frame.setResizable(false);
                    frame.revalidate();
                    frame.setVisible(true);
                    // Ajout du Listener pour la fermeture de la frame
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            // Quand on ferme la frame on détache l'observeur du chrono
                            chronos[finalI].detach(d);
                        }
                    });
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
        // Ajout d'un Listener pour ouvrir tout les horloges à chiffre romains
        romanAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Toutes les horloges romaines");
                Roman[] romans = new Roman[chronos.length];
                // on utilise flowlayout pour que l'affichage soit responsive
                frame.setLayout(new FlowLayout());
                // on ajoute les horloges romaines au frame
                for(int i = 0; i < chronos.length; ++i){
                    romans[i] = new Roman(chronos[i]);
                    frame.add(romans[i]);
                }
                frame.pack();
                frame.setSize(frame.getPreferredSize());
                frame.revalidate();
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        for(int i = 0; i < chronos.length; ++i){
                            chronos[i].detach(romans[i]);
                        }
                    }
                });
            }
        });


        // Ajout d'un Listener pour ouvrir tout les horloges à chiffre arabes
        arabicAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Toutes les horloges arabiques");
                Arab[] arabs = new Arab[chronos.length];
                // on utilise flowlayout pour que l'affichage soit responsive
                frame.setLayout(new FlowLayout());
                // on ajoute les les horloges arabes au frame
                for(int i = 0; i < chronos.length; ++i){
                    arabs[i] = new Arab(chronos[i]);
                    frame.add(arabs[i]);
                }
                frame.pack();
                frame.setSize(frame.getPreferredSize());
                frame.revalidate();
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        for (int i = 0; i < chronos.length; ++i) {
                            chronos[i].detach(arabs[i]);
                        }
                    }
                });
            }
        });


        // Ajout d'un Listener pour ouvrir tout les horloges digitale
        digitalAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Toutes les horloges numériques");
                Digital[] digitals = new Digital[chronos.length];
                // on utilise un flowlayout pour que l'affichage soit responsive
                frame.setLayout(new FlowLayout());
                // on ajout les horloges digitales
                for(int i = 0; i < chronos.length; ++i){
                    digitals[i] = new Digital(chronos[i]);
                    frame.add(digitals[i]);
                }
                frame.pack();
                frame.setSize(frame.getPreferredSize());
                frame.revalidate();
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        for(int i = 0; i < chronos.length; ++i){
                            chronos[i].detach(digitals[i]);
                        }
                    }
                });
            }
        });

        // Dernière ligne
        panels[nbChronos] = new JPanel();
        panels[nbChronos].add(new JLabel("Tous les chronos"));
        panels[nbChronos].add(romanAll);
        panels[nbChronos].add(arabicAll);
        panels[nbChronos].add(digitalAll);
        contentPane.add(panels[nbChronos]);

        // Ptits paramètres pour l'affichage de la fenêtre
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setSize(700, 50 * (nbChronos + 1));
        setLocationByPlatform(true);
        setVisible(true);
    }
}