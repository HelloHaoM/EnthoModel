import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class that is used to simulate the model
 * @author haomai
 * @author Yudong
 *
 */
public class Controller {
	
	//private ArrayList<Agent> worldAgents = new ArrayList<Agent>();
	private World world;
	
	// construction function, get connected with the world
	public Controller(World world){
		this.world = world;
	}
	
	// get the amount of CC agents
	public int getNumOfCC(){
		int numOfCC = 0;
		for(Agent agent : world.getWorldAgents()){
			if(agent.getStrategy().equals(Strategy.CC))
				numOfCC++;
		}
		return numOfCC;
	}

	// get the amount of CD agents
	public int getNumOfCD(){
		int numOfCD = 0;
		for(Agent agent : world.getWorldAgents()){
			if(agent.getStrategy().equals(Strategy.CD))
				numOfCD++;
		}
		return numOfCD;
	}
	
	// get the amount of DC agents
	public int getNumOfDC(){
		int numOfDC = 0;
		for(Agent agent : world.getWorldAgents()){
			if(agent.getStrategy().equals(Strategy.DC))
				numOfDC++;
		}
		return numOfDC;
	}
	
	//// get the amount of DD agents
	public int getNumOfDD(){
		int numOfDD = 0;
		for(Agent agent : world.getWorldAgents()){
			if(agent.getStrategy().equals(Strategy.DD))
				numOfDD++;
		}
		return numOfDD;
	}
	
	/**
	 * A main function for simulation
	 * Each tick represents a unit time
	 */
	public void tick() throws Exception{
		immigrate();
		cooperate();
		reproduce();
		die();
	}
	
	/**
	 * Immigration: generate a new agent
	 * @throws Exception
	 */
	private void immigrate() throws Exception{
		Random random = new Random();
		for(int i = 0; i < Params.IMMIGRANTSPERDAY; i++){
			// Find a empty block randomly
			Block emptyBlock = world.getEmptyBlock();
			
			// select region and strategy randomly
			Region region = Region.randomSelectRegion();
			Strategy strategy = Strategy.CC;

			// if random number is less than the rate of 
			// cooperating with the same color
			// then its second trait should be 'C'
			if(random.nextDouble() < Params.IMMIGRANTCHANGECOOPERATEWITHSAME){
				strategy = Strategy.randomSelectCoSame();
			}else{
				// else its second trait should be 'D'
				strategy = Strategy.randomSelectCoDifferent();
			}
			
			// generate a new agent
			Agent immigrant = new Agent(strategy, region, emptyBlock, this);
			emptyBlock.setAgent(immigrant);
			world.addAgent(immigrant);
		}
	}
	
	/**
	 * all agents in the world will have chance to cooperate
	 */
	private void cooperate(){
		// create a list that agents ordered randomly
		ArrayList<Agent> shuffleAgentList = new ArrayList<Agent>();
		shuffleAgentList.addAll(world.getWorldAgents());
		Collections.shuffle(shuffleAgentList);
		
		// find the neighbours of agents
		ArrayList<Block> occupiedNeighborBlocks = null;
		for(Agent agent : shuffleAgentList){
			occupiedNeighborBlocks = agent.getBlock().getOccupiedNeighborBlocks();

			// cooperate with its neighbours
			agent.cooperate(occupiedNeighborBlocks);
		}
	}
	
	/**
	 * all agents in the world will have chance to reproduce
	 * @throws Exception
	 */
	private void reproduce() throws Exception{
		// create a list that agents ordered randomly
		ArrayList<Agent> shuffleAgentList = new ArrayList<Agent>();
		shuffleAgentList.addAll(world.getWorldAgents());
		Collections.shuffle(shuffleAgentList);
		
		// find the adjacent empty block
		Block emptyNeighborsBlock = null;
		for(Agent agent : shuffleAgentList){
			emptyNeighborsBlock = agent.getBlock().getEmptyNeighborBlock();

			// reproduce a child in its adjacent empty block
			agent.reproduce(emptyNeighborsBlock);
		}
	}
	
	/**
	 * Generate a child based on parameter and parent
	 * @param block
	 * @param agent
	 * @throws Exception
	 */
	protected void reproduceChild(Block block, Agent agent) throws Exception{
		Random random = new Random();
		Strategy childStrategy;
		Region childRegion;

		// Initialise the Region and Strategy for child agent
		childStrategy = agent.getStrategy();
		childRegion = agent.getRegion();
		
		if (random.nextDouble() < Params.MUTATIONRATE) {
			// mutate child's region
			childRegion = Region.randomSelectRegion();
		}

		if (random.nextDouble() < Params.MUTATIONRATE) {
			// decide the second trait of the child agent
			childStrategy = Strategy.mutateSecondTrait(childStrategy);
		} else if (random.nextDouble() < Params.MUTATIONRATE) {
			childStrategy = Strategy.mutateThirdTrait(childStrategy);
		}

		// generate a child agent
		Agent childAgent = new Agent(childStrategy, childRegion, block, this);
		block.setAgent(childAgent);
		world.addAgent(childAgent);
	}

	/**
	 * some agents(depend on death rate) will dead and be removed in the world
	 */
	private void die(){
		Random random = new Random();
		
		ArrayList<Agent> shuffleAgentList = new ArrayList<Agent>();
		shuffleAgentList.addAll(world.getWorldAgents());
		Collections.shuffle(shuffleAgentList);
		
		for(Agent agent : shuffleAgentList){
			if(random.nextDouble() < Params.DEATHRATE){
				// if this agent dies, remove the agent from the block,
				// and then remove it from the world
				agent.die();
				world.removeAgent(agent);	
			}
		}
		
	}
	
}
