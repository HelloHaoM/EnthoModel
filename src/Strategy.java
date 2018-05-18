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
 * @author Yudong
 *
 */
public enum Strategy {
	CC, CD, DC, DD;
	
	/**
	 * Select a strategy randomly if it doesn't cooperate with same region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy changeFirstLetter(Strategy strategy) throws Exception{
		switch(strategy) {
			case CC:
				return DC;
			case CD:
				return DD;
			case DC:
				return CC;
			case DD:
				return CD;
			default:
				throw new Exception("Error strategy");
		}
	}
	
	/**
	 * Select a strategy randomly if it doesn't cooperate with different region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy changeSecondLetter(Strategy strategy) throws Exception{
		switch(strategy) {
			case CC:
				return CD;
			case CD:
				return CC;
			case DC:
				return DD;
			case DD:
				return DC;
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
		switch(random.nextInt(Params.NUMOFSTRATEGY)){
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
