package GUI_Admin;

import java.awt.event.*;

public class MyListener implements ActionListener {
    private Frame frame;
    private Worker worker;

    public MyListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String nome = frame.nome.getText();
        String posti = frame.posti.getText();
        
        disableButtons();

        worker = new Worker(nome, posti,command, frame);
        worker.execute();
    }

    private void disableButtons(){
        frame.crea.setEnabled(false);
        frame.aggiungiPosti.setEnabled(false);
        frame.chiudi.setEnabled(false);
        frame.listaEventi.setEnabled(false);
    }
}