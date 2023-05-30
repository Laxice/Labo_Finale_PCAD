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
          if(value.getValue0()!= -1){value = value.setAt0(value.getValue0()+Posti);}return value;});
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
          //L'evento non esiste ancora, aspettiamo che lo creino
          try {
            mappaEventi.wait();
          } catch (InterruptedException e) {
            return;
          }
        }
        if(res.getValue0()<0){
          //L'evento è stato annullato ci arrendiamo
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
        mappaEventi.replace(Nome,Pair.with(-1, false));
        mappaEventi.notifyAll();
      }
    }

    public static String ListaEventi(){
      String risposta = "LISTA EVENTI:\n";
      risposta += mappaEventi.entrySet().stream()
        .map(x -> (x.getValue().getValue0() != -1)?"Nome: " + x.getKey() + " Posti disponibili: " + x.getValue().getValue0()+"\n":"")
        .collect(Collectors.joining(""));
      return risposta;
    }
}