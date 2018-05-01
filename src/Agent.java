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
	
	public Strategy getStrategy(){
		return this.strategy;
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
				
				cooperateTime += 1;
			}
			break;
		// cooperate with same type only
		case CD:
			for(Agent neighbor : neighbors){
				if(neighbor.getRegion() == this.region){
					this.costPtr();
					neighbor.gainPtr();
					cooperateTime += 1;
				}
			}
			break;
		// cooperate with different type only
		case DC:
			for(Agent neighbor : neighbors){
				if(neighbor.getRegion() != this.region){
					this.costPtr();
					neighbor.gainPtr();
					cooperateTime += 1;
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
	 * @throws Exception 
	 */
	public int reproduce() throws Exception{
		int reproduceTime = 0;
		Random random = new Random();
		if(random.nextDouble() < ptr){
			
			
			// select a empty block randomly
			ArrayList<Block> emptyNeighbors = block.getEmptyNeighbors();
			if(emptyNeighbors.size() > 0){
				Block emptyBlock = emptyNeighbors.get(
						random.nextInt(emptyNeighbors.size()));
				
				if(emptyBlock != null){
					//TODO controller should make a child
					makeChild(emptyBlock, this);
					reproduceTime += 1;
				}
			}
			
		}
		return reproduceTime;
	}
	
	/**
	 * agent is dead and its block will be empty
	 */
	public void die(){
		this.block.setEmpty();
	}
	
	public void makeChild(Block block, Agent agent) throws Exception{
		Random random = new Random();
		Strategy childStrategy;
		int childRegion;
		
		// select a strategy and a region based on mutation rate
		if(random.nextDouble() < Params.MUTATIONRATE){
			childStrategy = Strategy.randomSelectStrategy();
			childRegion = random.nextInt(Params.NUMOFREGION);
		}else{
			childStrategy = agent.getStrategy();
			childRegion = agent.getRegion();
		}
		
		// generate a child agent
		Agent childAgent = new Agent(childStrategy, childRegion, block, controller);
		block.setAgent(childAgent);
		controller.addWorldAgents(childAgent);
	}
	
	

}
