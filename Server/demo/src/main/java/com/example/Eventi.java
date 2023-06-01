package com.example;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.javatuples.Pair;
public class Eventi {

    private static ConcurrentHashMap<String, Pair<Integer,Boolean>> mappaEventi = new ConcurrentHashMap<>();


    public static void Crea(String Nome, Integer Posti) {
      //creiamo un nuovo evento con tot posti
      mappaEventi.putIfAbsent(Nome, Pair.with(Posti,true));
    }

    public static void Aggiungi(String Nome, Integer Posti) {
      //Se è presente aggiungiamo tot posti 
      synchronized(mappaEventi){
        mappaEventi.computeIfPresent(Nome, (key,value) -> {
          {value = value.setAt0(value.getValue0()+Posti);}return value;});
        mappaEventi.notifyAll();
      }
    }

    public static void Prenota(String Nome, Integer Posti) {
      Boolean prenotazioneAvvenuta = false;
      //Verifichiamo che ci siano abbastanza posti per la nostra prenotazione
      while(!prenotazioneAvvenuta){
        Pair<Integer,Boolean> res=mappaEventi.computeIfPresent(Nome, (key,value) -> {
          if(value.getValue0()>=Posti){
            value = value.setAt0(value.getValue0()-Posti);
            value = value.setAt1(true);
          }else{
            value = value.setAt1(false);
          }
          return value;
        });
        if(res == null){
          //L'evento non esiste ancora o è stato annullato ci arrendiamo
          break;
        }
        prenotazioneAvvenuta=res.getValue1();
        if(!prenotazioneAvvenuta){
          try {
            mappaEventi.wait();
          } catch (InterruptedException e) {
            return;
          }
        }
      }
    }

    public static void Chiudi(String Nome){
      synchronized(mappaEventi){
        mappaEventi.remove(Nome);
        mappaEventi.notifyAll();
      }
    }

    public static String ListaEventi(){
      String risposta = "LISTA EVENTI:\n";
      risposta += mappaEventi.entrySet().stream()
        .map(x -> "Nome: " + x.getKey() + " Posti disponibili: " + x.getValue().getValue0()+"\n")
        .collect(Collectors.joining(""));
      return risposta;
    }
}