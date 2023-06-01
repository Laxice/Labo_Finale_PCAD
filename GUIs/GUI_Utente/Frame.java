package GUI_Utente;
import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    JTextField nome;
    JLabel labelNome;
    JTextField posti;
    JLabel labelPosti;
    JButton prenota;
    JButton listaEventi;
    JTextPane elenco;

    public Frame() {
        super("GUI_Utente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        labelNome = new JLabel("Nome Evento: ");
        nome = new JTextField(255);
        labelPosti = new JLabel("Numero Posti: ");
        posti = new JTextField(10);
        elenco = new JTextPane();
        JPanel pannelloSuperiore = new JPanel(new GridLayout(3, 1));
        
        JPanel pannelloMedio = new JPanel(new GridLayout(1, 1));
        pannelloSuperiore.add(labelNome);
        pannelloSuperiore.add(nome);
        pannelloSuperiore.add(labelPosti);
        pannelloSuperiore.add(posti);
        pannelloMedio.add(elenco);
        prenota = new JButton("Prenota");
        listaEventi = new JButton("Lista Eventi");

        MyListener listener = new MyListener(this);

        prenota.setActionCommand("prenota");
        prenota.addActionListener(listener);

        listaEventi.setActionCommand("lista");
        listaEventi.addActionListener(listener);

        JPanel pannelloBottoni = new JPanel(new GridLayout(1, 2));
        pannelloBottoni.add(prenota);
        pannelloBottoni.add(listaEventi);
        getContentPane().add(pannelloSuperiore, BorderLayout.PAGE_START);
        getContentPane().add(pannelloMedio, BorderLayout.CENTER);
        getContentPane().add(pannelloBottoni, BorderLayout.PAGE_END);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("main");
        Runnable init = new Runnable() {
            public void run() {
                new Frame();
            }
        };
        SwingUtilities.invokeLater(init);
    }
}