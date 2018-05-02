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
	private Region region;
	private Block block;
	private Controller controller;
	private double ptr = Params.INITIALPTR;
	
	public Agent(Strategy strategy, Region region, Block block, Controller controller){
		this.strategy = strategy;
		this.region = region;
		this.block = block;
		this.controller = controller;
	}
	
	public Region getRegion(){
		return this.region;
	}
	
	public Strategy getStrategy(){
		return this.strategy;
	}
	
	public Block getBlock(){
		return this.block;
	}
	
	// agent gain benefit with interaction
	private void gainPtr(){
		ptr += Params.GAINOFRECEIVING;
	}
	
	// agent cost benefit with interaction
	private void costPtr(){
		ptr -= Params.COSTOFGIVING;
	}
	
	/**
	 * Agent will cooperate with neighbors
	 * @param occupiedNeighborBlock
	 * @return cooperateTime: times of cooperation behaviors
	 */
	public int cooperate(Block occupiedNeighborBlock){
		int cooperateTime = 0;
		switch(strategy){
		// cooperate with everyone
		case CC: 
			if(occupiedNeighborBlock != null){
				this.costPtr();
				occupiedNeighborBlock.getOwner().gainPtr();
				cooperateTime += 1;
			}
			break;
		// cooperate with same type only
		case CD:
			if(occupiedNeighborBlock != null 
					 && occupiedNeighborBlock.getOwner().getRegion() == this.region){
				this.costPtr();
				occupiedNeighborBlock.getOwner().gainPtr();
				cooperateTime += 1;
			}
			break;
		// cooperate with different type only
		case DC:
			if(occupiedNeighborBlock != null 
					&& occupiedNeighborBlock.getOwner().getRegion() != this.region){
				this.costPtr();
				occupiedNeighborBlock.getOwner().gainPtr();
				cooperateTime += 1;
			}
			break;
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
