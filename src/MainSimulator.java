import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simulator to simulate the model's behavior
 * CSV file will be outputted
 * 
 * @author haomai
 *
 */
public class MainSimulator {
	
	public static void main(String[] args){
		World world = new World();
		Controller controller = new Controller(world);
		try{
			FileWriter csv = new FileWriter(System.getProperty("user.dir") + "/result.csv");
			
			// Run the simulation
			for(int i =0; i < Params.TOTALTICK; i++){
				controller.tick();
			}
			
			output2Csv(controller, csv);
			csv.flush();
			csv.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void output2Csv(Controller controller, FileWriter csv) throws Exception{
		// The title of csv
		csv.append("CC");
		csv.append(",");
		csv.append("CD");
		csv.append(",");
		csv.append("DC");
		csv.append(",");
		csv.append("DD");
		csv.append("\n");
		
		// Output result
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
