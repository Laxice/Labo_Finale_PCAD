package GUI_Utente;

import java.io.IOException;
import javax.swing.*;

public class Worker extends SwingWorker<String, Void> {
    private String nome;
    private String posti;
    private String mode;
    private Frame frame;

    private Api api = new Api();

    public Worker(String nome, String posti, String mode, Frame frame) {
        this.nome = nome;
        this.posti = posti;
        this.mode = mode;
        this.frame = frame;
    }

    public Worker(String nome, String mode, Frame frame) {
        this.nome = nome;
        this.mode = mode;
        this.frame = frame;
    }

    @Override
    protected String doInBackground() throws Exception {
        System.out.println("Ciao, sono " + Thread.currentThread().getName()
                + " ed eseguo doInBackground()");
        
                switch(mode)
                {
                    case "prenota": prenota(nome,posti); break;
                    case "lista": lista(); break;
                }
                return "ok";
    }

    @Override
    protected void done() { // chiamato dopo doInBackground ed eseguito dall’EDT
        enableButtons();
    }


    private void prenota(String nome, String posti){
        try {
            System.out.println("Prenotazione in corso");
            api.prenota(nome, posti);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void lista(){
        String lista = "";
        try {
            lista = api.listaEventi();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.elenco.setText(lista);
    }

    private void enableButtons(){
        frame.prenota.setEnabled(true);
        frame.listaEventi.setEnabled(true);
    }
}
