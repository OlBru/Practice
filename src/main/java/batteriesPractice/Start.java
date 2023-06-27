package batteriesPractice;

import batteriesPractice.model.Batteries;
import batteriesPractice.model.BatteriesSet;
import org.springframework.boot.SpringApplication;
import  org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
    public static void main(String[] args) {
    /*    BatteriesSet battery_one = new BatteriesSet();

        battery_one.addBattery(new Batteries("krk","powercell","nikel","steel",
                                            "salt",8.75,150));

        battery_one.addBattery(new Batteries("svr","powermover","copper","steel",
                "acid",7.25,220));

        battery_one.addBattery(new Batteries("shq","pipa","graphite","steel",
                "crystal",5,110));*/

       /* try {
            System.out.println("__________1");
            battery_one.math();
            System.out.println(battery_one.math());
            System.out.println("__________2");
        }
        catch (Exception exp){
            System.out.println("__________3");
            System.out.println(exp);
            System.out.println("__________4");
        }*/
       SpringApplication.run(Start.class,args);



    }
}
