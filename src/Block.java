import java.util.ArrayList;

/**
 * A class that represents a single block unit in the world
 * 
 * @author haomai
 *
 */
public class Block {
	
	private ArrayList<Block> neighbors = new ArrayList<Block>();
	private Agent owner;
	private World world;
	
	public Block(World world){
		this.world = world;
	}

	public Agent getOwner() {
		return owner;
	}

	public void setOwner(Agent owner) {
		this.owner = owner;
	}
	
	public void setNeighbors(ArrayList<Block> negihbors){
		this.neighbors.addAll(negihbors);
	}
	
	public boolean isEmpty(){
		if(owner == null)
			return true;
		return false;
	}
	
	public void setEmpty(){
		owner = null;
		world.setEmpty(this);	
	}
	
	public void setAgent(Agent agent){
		owner = agent;
		world.setOccupied(this);
	}
	
	/**
	 * Get all agents near a agent
	 * @return occupiedNeighbors: a list of agents that near a agent
	 */
	public ArrayList<Agent> getOccupiedNeighbors(){
		ArrayList<Agent> occupiedNeighbors = new ArrayList<Agent>();
		for(Block neighbor : neighbors){
			if(!neighbor.isEmpty())
				occupiedNeighbors.add(neighbor.owner);
		}
		return occupiedNeighbors;
	}
	
	/**
	 * Get all empty block near a agent
	 * @return emptyNeighbors: a list of empty block that near a agent
	 */
	public ArrayList<Block> getEmptyNeighbors(){
		ArrayList<Block> emptyNeighbors = new ArrayList<Block>();
		for(Block neighbor : neighbors){
			if(neighbor.isEmpty())
				emptyNeighbors.add(neighbor);
		}
		return emptyNeighbors;
	}
	


}
