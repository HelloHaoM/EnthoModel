import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represent an individual 
 * with specific region and cooperation strategy
 * 
 * @author haomai
 * @author Yudong
 *
 */
public class Agent {
	
	private Strategy strategy;
	private Region region;
	private Block block;
	private Controller controller;
	private double ptr;
	
	public Agent(Strategy strategy, Region region, Block block, Controller controller){
		this.strategy = strategy;
		this.region = region;
		this.block = block;
		this.controller = controller;
		this.ptr = Params.INITIALPTR + block.getAdvantage();
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
	 */
	public void cooperate(ArrayList<Block> occupiedNeighborBlocks){
		switch(strategy){
		// cooperate with everyone
		case CC: 
			for(Block occupiedNeighborBlock : occupiedNeighborBlocks){
				this.costPtr();
				occupiedNeighborBlock.getOwner().gainPtr();
			}
			break;
		// cooperate with same type only
		case CD:
			for(Block occupiedNeighborBlock : occupiedNeighborBlocks){
				if(occupiedNeighborBlock.getOwner().getRegion() == this.region){
					this.costPtr();
					occupiedNeighborBlock.getOwner().gainPtr();
				}
			}
			break;
		// cooperate with different type only
		case DC:
			for(Block occupiedNeighborBlock : occupiedNeighborBlocks){
				if(occupiedNeighborBlock.getOwner().getRegion() != this.region){
					this.costPtr();
					occupiedNeighborBlock.getOwner().gainPtr();
				}
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * Agent will reproduce a new agent
	 * if there is a space near by him
	 * @param emptyNeighborsBlock
	 * @throws Exception 
	 */
	public void reproduce(Block emptyNeighborsBlock) throws Exception{
		Random random = new Random();
		if(random.nextDouble() < ptr){
			if(emptyNeighborsBlock != null){
				controller.reproduceChild(emptyNeighborsBlock, this);
			}
		}
	}
	
	/**
	 * agent is dead and its block will be empty
	 */
	public void die(){
		this.block.setEmpty();
	}

}
