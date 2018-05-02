import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the real world
 * providing block unit to agents
 * @author haomai
 *
 */
public class World {
	
	private int size = Params.SIZE;
	private Block[][] blockWorld = new Block[size][size];
	private ArrayList<Block> emptyBlocks = new ArrayList<Block>();
	
	
	public World(){
		// Create a 2-dimension empty block world
		for(int i = 0; i < size; i++){
			Block[] rowBlock = new Block[size];
			blockWorld[i] = rowBlock;
			for(int j = 0; j < size; j++){
				rowBlock[j] = new Block(this);
				emptyBlocks.add(rowBlock[j]);
			}
		}
		worldInit();
	}
	
	public void setEmpty(Block block){
		emptyBlocks.add(block);
	}
	
	public void setOccupied(Block block){
		emptyBlocks.remove(block);
	}
	
	/**
	 * Initialize the world setting all block neighbors
	 */
	public void worldInit(){
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				// Four neighbors for a agent
				int up = i - 1;
				int down = i + 1;
				int left = j - 1;
				int right = j + 1;
				
				// Act as a circle
				if(i == 0)
					up = size - 1;
				if(i == size -1)
					down = 0;
				if(j == 0)
					left = size -1;
				if(j == size - 1)
					right = 0;
				
				blockInit(i,j,up,down,left,right);
			}
	}
	
	/**
	 * Add four neighbors to a specific block
	 * @param i
	 * @param j
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 */
	public void blockInit(int i, int j,
			int up, int down, int left, int right){
		Block block = blockWorld[i][j];
		
		block.addNeighborBlock(blockWorld[up][j]);
		block.addNeighborBlock(blockWorld[down][j]);
		block.addNeighborBlock(blockWorld[i][left]);
		block.addNeighborBlock(blockWorld[i][right]);
	}
	
	/**
	 * Select a empty block for immigration randomly
	 * @return emptyBlock: a empty block in the world
	 */
	public Block getEmptyBlock(){
		Random random = new Random();
		Block emptyBlock = emptyBlocks.get(
				random.nextInt(emptyBlocks.size()));
		return emptyBlock;
		
	}
}
