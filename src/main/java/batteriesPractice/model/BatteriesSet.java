package batteriesPractice.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.awt.Point;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BatteriesSet extends ArrayList<Batteries> {
    private

    ArrayList<batteriesPractice.model.Batteries> arrayList;

    ArrayList<batteriesPractice.model.Batteries> listik;
    // ArrayList<batteriesPractice.model.Batteries>batteriesList;
    ObjectMapper mapper;


    public BatteriesSet() {
        this.listik = new ArrayList<>();
        this.arrayList = new ArrayList<>();
        mapper = new ObjectMapper();

    }


    public void addBattery(batteriesPractice.model.Batteries parbattery) {
        arrayList.add(parbattery);
    }

    public void removeBattery(batteriesPractice.model.Batteries fullbattery) {
        arrayList.remove(arrayList.indexOf(fullbattery));
    }

    public void removeBattery(String id) {
        arrayList.remove(arrayList.stream().filter(Batteries->Batteries.getId().equals(id)).findFirst().orElseThrow());}


    public Batteries getOneBattery(String id) { return arrayList.stream().filter(Batteries->Batteries.getId().equals(id)).findFirst().orElseThrow();}

    public ArrayList<batteriesPractice.model.Batteries> getAllBattery() { //чтобы можно получить все элементы
        return arrayList;
    }

    public void convertToJSON() throws IOException { // чтобы список можно было при помощи json'а конвертировать в файл
        File file = new File("src/main/resources/batteries.json");
        mapper.writeValue(file, arrayList);


    }

    public void convertToPOJO() throws IOException { // чтобы файл можно было при помощи json'а конвертировать в список
        File file = new File("src/main/resources/batteries.json");
        List<batteriesPractice.model.Batteries> spisok = mapper.readValue(file, new TypeReference<List<batteriesPractice.model.Batteries>>() {
        });

        for (batteriesPractice.model.Batteries battery : spisok) {
            System.out.println("Identifier: " + battery.getIdentifier());
            System.out.println("Brand: " + battery.getBrand());
            System.out.println("Anode Material: " + battery.getAnode_material());
            System.out.println("Cathode Material: " + battery.getCathode_material());
            System.out.println("Electrolyte: " + battery.getElectrolyte());
            System.out.println("Voltage: " + battery.getVoltage());
            System.out.println("Capacity: " + battery.getCapacity());
            System.out.println("________________________");

        }
        System.out.println("Amount of batteries: " + spisok.size());
        System.out.println(spisok);

    }

    public String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public ArrayList<Batteries> readJson() {
        String path = "C:\\Users\\sheeeeesh\\IdeaProjects\\Practice\\src\\main\\resources\\batteries.json";
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String json = readFileAsString(path);
            ArrayList<Batteries> jsonlist = mapper.readValue(json, new TypeReference<ArrayList<Batteries>>() {
            });
            this.arrayList = jsonlist;
            return jsonlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static String info(){
        String information = "Developer: Олег Брусенский \n Language:java \n Data: 29.05.2023";
        return information;
    }

    private static double calculateDistributionValue(double x, double mean, double standardDeviation) {

        double exponent = -((x - mean) * (x - mean)) / (2 * standardDeviation * standardDeviation);
        double coefficient = 1.0 / (Math.sqrt(2 * Math.PI) * standardDeviation);
        return coefficient * Math.exp(exponent);
    }


    public String math() {
        double sum = 0.0;
        int amount = arrayList.size();
        for (Batteries battery : arrayList) {
            double forcount = battery.getVoltage();
            sum += forcount;
        }
        double medium_znach = sum / amount;

        double sumOfQuadroDif = 0.0;
        for (Batteries battery : arrayList) {
            double difference = battery.getVoltage() - medium_znach;
            sumOfQuadroDif += difference * difference;
        }

        double otklon = Math.sqrt(sumOfQuadroDif / amount);

        Point[] points = new Point[amount];

        double interval = 1.0;
        double startX = 1.0;

        for (int i = 0; i < amount; i++) {
            double x = startX + (i * interval);
            double y = calculateDistributionValue(x, medium_znach, otklon)*1000;
            points[i] = new Point((int) x, (int) y);
        }
        return  Arrays.toString(points);
    }

}


