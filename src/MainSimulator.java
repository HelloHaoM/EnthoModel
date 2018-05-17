/* This program simulates a NetLogo model -- Ethnocentrism.
 * Created by Hao Mai(895890) and Yudong Gao(886576)
 * In this program, we use 'Region' to represent 'Color',
 * and use 'Strategy' to represent the combination of the second
 * and the third 'Trait'
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simulator to simulate the model's behavior
 * CSV file will be outputted
 * 
 * @author haomai
 * @author Yudong
 * 
 */
public class MainSimulator {
	
	public static void main(String[] args){
		World world = new World();
		Controller controller = new Controller(world);
		int tick = 0;
		try{
			FileWriter csv = new FileWriter(System.getProperty("user.dir") + "/result.csv");
                for (int a = 0; a < 5; a++) {
                	System.out.println(a+1 + " test start");
                    // setting parametres of each round 
                    Params.setParams(a);
                    // add parameters to the csv file
                    addParams2csv(csv);
                    // add title to the csv file
                    addTitle2csv(csv);

                    // begin the simulation, 2000 ticks in total
                    // after every 100 tick, we write the amount of (CC, CD, DC, DD) into a file
                    for (int i = 0; i < 20; i++) {
                        for(int j = i*100; j < (i+1)*100; j++){
                            controller.tick();
                            tick = j+1;
                        }
                        // write the output into a csv file
                        output2Csv(controller, csv, tick);
                    }
                }
                System.out.println("All test finished");
			csv.flush();
			csv.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addParams2csv(FileWriter csv) throws Exception{
		// Parameters of the simulation
        csv.append("immigrants-per-day");
        csv.append(",");
        csv.append("initial-PTR");
        csv.append(",");
        csv.append("cost-of-giving");
        csv.append(",");
        csv.append("gain-of-receiving");
        csv.append(",");
        csv.append("mutation-rate");
        csv.append(",");
        csv.append("death-rate");
        csv.append(",");
        csv.append("immigrants-chance-cooperate-with-same");
        csv.append(",");
        csv.append("immigrants-chance-cooperate-with-different");
        csv.append(",");
        csv.append("\n");
		csv.append(Double.toString(Params.IMMIGRANTSPERDAY));
        csv.append(",");
        csv.append(Double.toString(Params.INITIALPTR));
        csv.append(",");
        csv.append(Double.toString(Params.COSTOFGIVING));
        csv.append(",");
        csv.append(Double.toString(Params.GAINOFRECEIVING));
        csv.append(",");
        csv.append(Double.toString(Params.MUTATIONRATE));
        csv.append(",");
        csv.append(Double.toString(Params.DEATHRATE));
        csv.append(",");
        csv.append(Double.toString(Params.IMMIGRANTCHANGECOOPERATEWITHSAME));
        csv.append(",");
        csv.append(Double.toString(Params.IMMIGRANTCHANGECOOPERATEWITHDIFFERENT));
        csv.append("\n");
                
	}

	public static void addTitle2csv(FileWriter csv) throws Exception{
        csv.append("\n");
		// The title of csv
		csv.append("TICK");
		csv.append(",");
		csv.append("CC");
		csv.append(",");
		csv.append("CD");
		csv.append(",");
		csv.append("DC");
		csv.append(",");
		csv.append("DD");
		csv.append("\n");
	}
        
	public static void output2Csv(Controller controller, FileWriter csv, int tick) throws Exception{
		// Output result
		csv.append(Integer.toString(tick));
		csv.append(",");
		csv.append(Integer.toString(controller.getNumOfCC()));
		csv.append(",");
		csv.append(Integer.toString(controller.getNumOfCD()));
		csv.append(",");
		csv.append(Integer.toString(controller.getNumOfDC()));
		csv.append(",");
		csv.append(Integer.toString(controller.getNumOfDD()));
		csv.append("\n");
	}
}
