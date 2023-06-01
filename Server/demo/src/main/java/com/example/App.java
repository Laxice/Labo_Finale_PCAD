package com.example;
import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Eventi.Crea("Pippo", 10);
        Eventi.Crea("C4", 86);

        int maxThreads = 16;
        threadPool(maxThreads);
        get("/eventi", (req,res) -> Eventi.ListaEventi());

        post("/eventi", (req,res) -> {
            String name =  req.queryParams("nome");
            Integer posti = Integer.valueOf(req.queryParams("posti"));
            Eventi.Crea(name, posti);
            return "ok";
        });

        put("/eventi", (req,res) -> {
            String name =  req.queryParams("nome");
            Integer posti = Integer.valueOf(req.queryParams("posti"));
            Eventi.Aggiungi(name, posti);
            return "ok";
        });

        delete("/eventi", (req,res) -> {
            String name =  req.queryParams("nome");
            Eventi.Chiudi(name);
            return "ok";
        });

        put("/prenota", (req,res) ->{
            String name =  req.queryParams("nome");
            Integer posti = Integer.valueOf(req.queryParams("posti"));
            Eventi.Prenota(name, posti);
            return "ok";
        });
    }
}
