import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represent an individual 
 * with specific region and cooperation strategy
 * 
 * @author haomai
 *
 */
public class Agent {
	
	private Strategy strategy;
	private int region;
	private Block block;
	private Controller controller;
	private double ptr = Params.INITIALPTR;
	
	public Agent(Strategy strategy, int region, Block block, Controller controller){
		this.strategy = strategy;
		this.region = region;
		this.block = block;
		this.controller = controller;
	}
	
	public int getRegion(){
		return this.region;
	}
	
	// reset the ptr after reproducing
	public void resetPtr(){
		ptr = Params.INITIALPTR;
	}
	
	// agent gain benefit with interaction
	public void gainPtr(){
		ptr += Params.GAINOFRECEIVING;
	}
	
	// agent cost benefit with interaction
	public void costPtr(){
		ptr -= Params.COSTOFGIVING;
	}
	
	/**
	 * Agent will cooperate with neighbors
	 * @return cooperateTime: times of cooperation behaviors
	 */
	public int cooperate(){
		int cooperateTime = 0;
		// find out which blocks have agent
		ArrayList<Agent> neighbors = block.getOccupiedNeighbors();
		switch(strategy){
		// cooperate with everyone
		case CC: 
			for(Agent neighbor : neighbors){
				// agent cost ptr if they cooperate with others
				this.costPtr();
				
				// agent who is cooperated by others gains ptr
				neighbor.gainPtr();
			}
			break;
		// cooperate with same type only
		case CD:
			for(Agent neighbor : neighbors){
				cooperateTime = 1;
				if(neighbor.getRegion() == this.region){
					this.costPtr();
					neighbor.gainPtr();
				}
			}
			break;
		// cooperate with different type only
		case DC:
			for(Agent neighbor : neighbors){
				if(neighbor.getRegion() != this.region){
					this.costPtr();
					neighbor.gainPtr();
				}
			}
		default:
			break;
		}
		return cooperateTime;
	}
	
	/**
	 * Agent will reproduce a new agent
	 * if there is a space near by him
	 * @return reproduceTime: the times of reproduction behavior
	 */
	public int reproduce(){
		int reproduceTime = 0;
		Random random = new Random();
		if(random.nextDouble() < ptr){
			reproduceTime = 1;
			
			// select a empty block randomly
			ArrayList<Block> emptyNeighbors = block.getEmptyNeighbors();
			Block emptyBlock = emptyNeighbors.get(
					random.nextInt(emptyNeighbors.size()));
			
			//if(emptyBlock != null)
				//TODO controller should make a child
		}
		return reproduceTime;
	}
	
	/**
	 * agent is dead and its block will be empty
	 */
	public void die(){
		this.block.setEmpty();
	}
	
	

}
