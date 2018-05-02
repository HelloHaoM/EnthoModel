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
	
	public Block getBlock(){
		return this.block;
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
	 * @param occupiedNeighbors
	 * @return cooperateTime: times of cooperation behaviors
	 */
	public int cooperate(ArrayList<Agent> occupiedNeighbors){
		int cooperateTime = 0;
		switch(strategy){
		// cooperate with everyone
		case CC: 
			for(Agent neighbor : occupiedNeighbors){
				// agent cost ptr if they cooperate with others
				this.costPtr();
				
				// agent who is cooperated by others gains ptr
				neighbor.gainPtr();
				
				cooperateTime += 1;
			}
			break;
		// cooperate with same type only
		case CD:
			for(Agent neighbor : occupiedNeighbors){
				if(neighbor.getRegion() == this.region){
					this.costPtr();
					neighbor.gainPtr();
					cooperateTime += 1;
				}
			}
			break;
		// cooperate with different type only
		case DC:
			for(Agent neighbor : occupiedNeighbors){
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
	 * @param emptyNeighborsBlock
	 * @return reproduceTime: the times of reproduction behavior
	 * @throws Exception 
	 */
	public int reproduce(Block emptyNeighborsBlock) throws Exception{
		int reproduceTime = 0;
		Random random = new Random();
		if(random.nextDouble() < ptr){
			if(emptyNeighborsBlock != null){
				controller.reproduceChild(emptyNeighborsBlock, this);
				reproduceTime += 1;
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

}
