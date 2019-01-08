import javafx.util.Pair;

import java.io.*;
import java.security.KeyPair;
import java.util.*;

public class Main {

    private static class Cotxe{
        String mpg;
        String cylinders;
        String displacement;
        String horsepower;
        String weight;
        String acceleration;
        String model_year;
        String origin;
        String model;
        String marca;

        public Cotxe(String dades){
            String[] camps = dades.split(",");
            mpg = camps[0];
            cylinders = camps[1];
            displacement = camps[2];
            horsepower = camps[3];
            weight = camps[4];
            acceleration = camps[5];
            model_year = camps[6];
            origin = camps[7];
            if(camps[8].split( " ").length == 1){
                model = "NA";
            }else{
                model = camps[8].split(" ")[1].replace("\"", "");
            }
            marca = camps[8].split(" ")[0].replace("\"", "");
        }

        @Override public String toString(){
            return String.format("%s,%s,%s,%s,%s,%s,%s,\"%s\"", mpg,cylinders,displacement,horsepower,weight,acceleration,model_year,model);
        }
    }

    static final int MINIM = 2;


    public static void main(String ... args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader("dades.csv"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("res.csv"))){

            Map<String, List<Cotxe>> marques = new HashMap<>();
            String linia = null;
            while((linia = br.readLine()) != null){
                Cotxe c = new Cotxe(linia);
                if(!marques.containsKey(c.marca)){
                    marques.put(c.marca, new LinkedList<>());
                }
                marques.get(c.marca).add(c);
            }
            int n = 0;
            for(String marca: marques.keySet()){

                String m1 = "other";
                String m2 = "other";
                String m3 = "other";
                if(n < marques.keySet().size()/3){
                    m1 = marca;
                }else if(n < (2*marques.keySet().size()/3)){
                    m2 = marca;
                } else{
                    m3 = marca;
                }
                for(Cotxe c: marques.get(marca)){
                    bw.write(c+",\""+marca+"\",\""+m1+"\",\""+m2+"\",\""+m3+"\"\n");
                }
                n++;
            }
        }
    }
}
