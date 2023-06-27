package batteriesPractice.controller;

import batteriesPractice.model.Batteries;
import batteriesPractice.model.BatteriesSet;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/")
public class BatteiesController{
    ArrayList<Batteries> arrayList;
    BatteriesSet batteriesList = new BatteriesSet();

    @GetMapping("/getall")
    public ArrayList<Batteries> allBatteries() {
        batteriesList.readJson();
        return batteriesList.getAllBattery();
    }

    @GetMapping("/getone/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Batteries getOneBattery(@PathVariable String id) {
        batteriesList.readJson();
        return batteriesList.getOneBattery(id);

    }


    @PostMapping("/addto")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Batteries add(@RequestBody Map<String, String> model) throws IOException {
        batteriesList.readJson();
        Batteries battery = new Batteries(model.get("identifier"), model.get("brand"), model.get("anode_material"),
                model.get("cathode_material"), model.get("electrolyte"), Double.parseDouble(model.get("voltage")),
                Double.parseDouble(model.get("capacity")));
        batteriesList.addBattery(battery);
        batteriesList.convertToJSON();
        return battery;
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Batteries update(@PathVariable String id, @RequestBody Map<String, String> model) throws IOException {
        batteriesList.readJson();
        Batteries coolBatareyka = batteriesList.getOneBattery(id);
        for (String key : model.keySet()) {
            switch (key) {
                case "identifier" -> coolBatareyka.setIdentifier(model.get(key));
                case "brand" -> coolBatareyka.setBrand(model.get(key));
                case "cathode_material" -> coolBatareyka.setCathode_material(model.get(key));
                case "anode_material" -> coolBatareyka.setAnode_material(model.get(key));
                case "electrolyte" -> coolBatareyka.setElectrolyte(model.get(key));
                case "voltage" -> coolBatareyka.setVoltage(Double.parseDouble(model.get(key)));
                case "capacity" -> coolBatareyka.setCapacity(Double.parseDouble(model.get(key)));

            }
        }
        batteriesList.convertToJSON();
        return coolBatareyka;

}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) throws IOException {
        batteriesList.readJson();
        batteriesList.removeBattery(id);
        batteriesList.convertToJSON();
    }

    @GetMapping("/math")
    public String math() throws IOException {
        batteriesList.readJson();
        return (batteriesList.math());
    }

    @GetMapping("/info")
    public String info(@RequestHeader("My-Info")String myInfo) throws IOException {
        return BatteriesSet.info();
    }
    @GetMapping("/helloWorld")
    @ResponseBody
    public String helloWorld() {
        return "Hello, World!";
    }
}









