import java.util.Random;

/**
 * A enum that represent cooperation strategy
 * 
 * CC: The agent who cooperates with everyone
 * CD: The agent who cooperates with only with people of the same type
 * DC: The agent who do not cooperate with anyone
 * DD: The agent who only cooperate with people of different types
 * 
 * @author haomai
 *
 */
public enum Strategy {
	CC, CD, DC, DD;
	
	/**
	 * Select a strategy randomly that cooperate with same region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy randomSelectCoSame() throws Exception{
		Random random = new Random();
		switch(random.nextInt(2)){
		case 0:
			return CC;
		case 1:
			return CD;
		default:
			throw new Exception("Error strategy");		
		}
	}
	
	/**
	 * Select a strategy randomly that cooperate with different region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy randomSelectCoDifferent() throws Exception{
		Random random = new Random();
		switch(random.nextInt(2)){
		case 0:
			return DC;
		case 1:
			return DD;
		default:
			throw new Exception("Error strategy");		
		}
	}
	
	/**
	 * Select a strategy randomly
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy randomSelectStrategy() throws Exception{
		Random random = new Random();
		switch(random.nextInt(Params.NUMOFREGION)){
		case 0:
			return CC;
		case 1:
			return CD;
		case 2:
			return DC;
		case 3:
			return DD;
		default:
			throw new Exception("Error strategy");
		}
	}

}
