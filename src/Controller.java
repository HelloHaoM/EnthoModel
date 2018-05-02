import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class that is used to simulate the model
 * @author haomai
 *
 */
public class Controller {
	/**
	 * 按我的理解来说，controller应该要有一个tick函数，函数里面有4个流程：
	 * 1. immigrate (产生一个agent并占据一个空block,strategy, color之类的要随机确定)
	 * 2. interact	(调用agent间interact)
	 * 3. reproduce (随机调用agent的reproduce)
	 * 4. die (随机让某个agents死掉)
	 */
	
	/**
	 * 维护一个agent list 代表现在的agent
	 * 因为要输出最终结果，可能还要维护一下4种agent的数目
	 */
	
	/**
	 * 在MainSimulator函数里，循环调用tick函数多次，然后输出csv即可
	 */
	
	private ArrayList<Agent> worldAgents = new ArrayList<Agent>();
	private World world;
	
	public Controller(World world){
		this.world = world;
	}
	
	public void addWorldAgents(Agent agent){
		worldAgents.add(agent);
	}
	
	public int getNumOfCC(){
		int numOfCC = 0;
		for(Agent agent : worldAgents){
			if(agent.getStrategy().equals(Strategy.CC))
				numOfCC++;
		}
		return numOfCC;
	}
	
	public int getNumOfCD(){
		int numOfCD = 0;
		for(Agent agent : worldAgents){
			if(agent.getStrategy().equals(Strategy.CD))
				numOfCD++;
		}
		return numOfCD;
	}
	
	public int getNumOfDC(){
		int numOfDC = 0;
		for(Agent agent : worldAgents){
			if(agent.getStrategy().equals(Strategy.DC))
				numOfDC++;
		}
		return numOfDC;
	}
	
	public int getNumOfDD(){
		int numOfDD = 0;
		for(Agent agent : worldAgents){
			if(agent.getStrategy().equals(Strategy.DD))
				numOfDD++;
		}
		return numOfDD;
	}
	
	/**
	 * A main function for simulation
	 * Each tick represents each time
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
	public void immigrate() throws Exception{
		Random random = new Random();
		for(int i = 0; i < Params.IMMIGRANTSPERDAY; i++){
			// Find a empty block randomly
			Block emptyBlock = world.getEmptyBlock();
			
			// select region and strategy randomly
			int region = random.nextInt(Params.NUMOFREGION);
			Strategy strategy = Strategy.CC;
			if(random.nextDouble() > Params.IMMIGRANTCHANGECOOPERATEWITHSAME){
				strategy = Strategy.randomSelectCoSame();
			}else if(random.nextDouble() > Params.IMMIGRANTCHANGECOOPERATEWITHDIFFERENT){
				strategy = Strategy.randomSelectCoSame();
			}
			
			// generate a new agent
			Agent immgrant = new Agent(strategy, region, emptyBlock, this);
			emptyBlock.setAgent(immgrant);
			worldAgents.add(immgrant);
		}
	}
	
	/**
	 * all agents in the world will have chance to cooperate
	 */
	public void cooperate(){
		// create a list that agents ordered randomly
		ArrayList<Agent> shuffleAgentList = new ArrayList<Agent>();
		shuffleAgentList.addAll(worldAgents);
		Collections.shuffle(shuffleAgentList);
		
		ArrayList<Agent> occupiedNeighbors = new ArrayList<Agent>();
		for(Agent agent : shuffleAgentList){
			occupiedNeighbors = agent.getBlock().getOccupiedNeighbors();
			agent.cooperate(occupiedNeighbors);
		}
	}
	
	/**
	 * all agents in the world will have chance to reproduce
	 * @throws Exception
	 */
	public void reproduce() throws Exception{
		// create a list that agents ordered randomly
		ArrayList<Agent> shuffleAgentList = new ArrayList<Agent>();
		shuffleAgentList.addAll(worldAgents);
		Collections.shuffle(shuffleAgentList);
		
		Block emptyNeighborsBlock = null;
		for(Agent agent : shuffleAgentList){
			emptyNeighborsBlock = agent.getBlock().getEmptyNeighborBlock();
			agent.reproduce(emptyNeighborsBlock);
		}
	}
	
	/**
	 * Generate a child based on parameter and parent
	 * @param block
	 * @param agent
	 * @throws Exception
	 */
	public void reproduceChild(Block block, Agent agent) throws Exception{
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
		Agent childAgent = new Agent(childStrategy, childRegion, block, this);
		block.setAgent(childAgent);
		worldAgents.add(childAgent);
	}

	/**
	 * some agents(depend on death rate) will dead and be removed in the world
	 */
	public void die(){
		Random random = new Random();
		ArrayList<Agent> deadAgents = new ArrayList<Agent>();
		for(Agent agent : worldAgents){
			if(random.nextDouble() < Params.DEATHRATE){
				agent.die();
				deadAgents.add(agent);
			}
		}
		
		worldAgents.removeAll(deadAgents);
	}
	
	

}
