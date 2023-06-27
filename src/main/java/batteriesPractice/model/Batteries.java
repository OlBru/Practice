package batteriesPractice.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public class Batteries {
    private String identifier, brand, anode_material, cathode_material, electrolyte,id;
    private double voltage, capacity;

    public Batteries(String identifier, String brand, String anode_material, String cathode_material, String electrolyte, double voltage, double capacity) {
        this.id = UUID.randomUUID().toString();
        this.identifier = identifier;
        this.brand = brand;
        this.anode_material = anode_material;
        this.cathode_material = cathode_material;
        this.electrolyte = electrolyte;
        this.voltage = voltage;
        this.capacity = capacity;
    }
    public Batteries() {
        this.id = UUID.randomUUID().toString();
        this.identifier = "";
        this.brand = "";
        this.anode_material = "";
        this.cathode_material = "";
        this.electrolyte = "";
        this.voltage = 0;
        this.capacity = 0;
    }
    public Batteries(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Batteries battery = objectMapper.readValue(json, Batteries.class);
            this.id = UUID.randomUUID().toString();
            this.identifier = battery.identifier;
            this.brand = battery.brand;
            this.anode_material = battery.anode_material;
            this.cathode_material = battery.cathode_material;
            this.electrolyte = battery.electrolyte;
            this.voltage = battery.voltage;
            this.capacity = battery.capacity;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAnode_material() {
        return anode_material;
    }

    public void setAnode_material(String anode_material) {
        this.anode_material = anode_material;
    }

    public String getCathode_material() {
        return cathode_material;
    }

    public void setCathode_material(String cathode_material) {
        this.cathode_material = cathode_material;
    }

    public String getElectrolyte() {
        return electrolyte;
    }

    public void setElectrolyte(String electrolyte) {
        this.electrolyte = electrolyte;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

}



