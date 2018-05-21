import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents a single block unit in the world
 * 
 * @author haomai
 * @author Yudong
 *
 */
public class Block {
	
	private ArrayList<Block> neighbors = new ArrayList<Block>();
	private Agent owner;
	private World world;
	// for Extension 2, some of blocks may have advantages
	private double advantage = 0;
	
	public Block(World world){
		this.world = world;
	}

	private boolean isEmpty(){
		if(owner == null)
			return true;
		return false;
	}
	
	public Agent getOwner() {
		return owner;
	}
	
	public void addNeighborBlock(Block negihbor){
		this.neighbors.add(negihbor);
	}
	
	public void setEmpty(){
		owner = null;
		world.setEmpty(this);	
	}
	
	public void setAgent(Agent agent){
		owner = agent;
		world.setOccupied(this);
	}

	public double getAdvantage(){
		return advantage;
	}

	// the function to add advantage to a block
	public void addAdvantage(){
		Random random = new Random();
		// each block will have 20% to become more advantageous 
		if (random.nextDouble() < 0.2) {
			this.advantage += Params.ADVANTAGE;
		}
	}
	
	/**
	 * Get random occupied block near a agent
	 * @return occupiedNeighbors: a list of agents that near a agent
	 */
	public ArrayList<Block> getOccupiedNeighborBlocks(){
		ArrayList<Block> occupiedNeighborBlocks = new ArrayList<Block>();
		for(Block neighbor : neighbors){
			if(!neighbor.isEmpty())
				occupiedNeighborBlocks.add(neighbor);
		}
		
//		Random random = new Random();
//		Block occupiedNeighborBlock = null;
//		if(occupiedNeighborBlocks.size() > 0){
//			occupiedNeighborBlock = occupiedNeighborBlocks.get(
//					random.nextInt(occupiedNeighborBlocks.size()));
//		}
		
		return occupiedNeighborBlocks;
	}
	
	/**
	 * Get random empty block near a agent
	 * @return emptyNeighborBlock: a list of empty block that near a agent
	 */
	public Block getEmptyNeighborBlock(){
		ArrayList<Block> emptyNeighborBlocks = new ArrayList<Block>();
		for(Block neighborBlock : neighbors){
			if(neighborBlock.isEmpty())
				emptyNeighborBlocks.add(neighborBlock);
		}
		
		Random random = new Random();
		Block emptyNeighborBlock = null;
		if(emptyNeighborBlocks.size() > 0){
			emptyNeighborBlock = emptyNeighborBlocks.get(
					random.nextInt(emptyNeighborBlocks.size()));
		}
			
		return emptyNeighborBlock;
	}
	


}
