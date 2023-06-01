package GUI_Admin;

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
                    case "crea": crea(nome, posti); break;
                    case "aggiungi": aggiungi(nome,posti); break;
                    case "lista": lista(); break;
                    case "chiudi": chiudi(nome); break;
                }
                return "ok";
    }

    @Override
    protected void done() { // chiamato dopo doInBackground ed eseguito dall’EDT
        enableButtons();
    }

    private void crea(String nome, String posti){
        try {
            api.crea(nome, posti);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void aggiungi(String nome, String posti){
        try {
            api.aggiungi(nome, posti);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void chiudi(String nome){
        try {
            api.chiudi(nome);
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
        frame.crea.setEnabled(true);
        frame.aggiungiPosti.setEnabled(true);
        frame.chiudi.setEnabled(true);
        frame.listaEventi.setEnabled(true);
    }
}
