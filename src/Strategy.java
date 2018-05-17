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
	 * Select a strategy randomly that cooperate with same region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy randomSelectCoSame() throws Exception{
		Random random = new Random();
		// then here decide the third trait 
		if (random.nextDouble() < Params.IMMIGRANTCHANGECOOPERATEWITHDIFFERENT) {
			// if the random number is less than the rate of 
			// coopertating with agents of different colour
			// then it cooperates with them, third trait is 'C'
			return CC;
		}else{
			// else it defects them
			// the third trait is 'D'
			return CD;
		}
	}
	
	/**
	 * Select a strategy randomly that cooperate with different region
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy randomSelectCoDifferent() throws Exception{
		Random random = new Random();
		if (random.nextDouble() < Params.IMMIGRANTCHANGECOOPERATEWITHDIFFERENT) {
			// if the random number is less than the rate of 
			// coopertating with agents of different colour
			// then it cooperates with them, third trait is 'C'
			return DC;
		}else{
			// else it defects them
			// the third trait is 'D'
			return DD;
		}
	}

	/**
	 * Mutate a strategy, make it different from the previous one
	 * @return a specific strategy
	 * @throws Exception
	 */
	public static Strategy mutateSecondTrait(Strategy strategy) throws Exception{
		Random random = new Random();
		// if the program enters this method, 
		// it means that at least one trait will be mutated
		if (random.nextDouble() < Params.MUTATIONRATE) {
			// if it also needs to mutate the third trait
			switch(strategy){
				case CC:
					return DD;
				case DC:
					return CD;
				case CD:
					return DC;
				case DD:
					return CC;
				default:
					throw new Exception("Error strategy");
			}
		}else{
			// if it doesn't need to mutate the third trait
			switch(strategy){
				case CC:
					return DC;
				case DC:
					return CC;
				case CD:
					return DD;
				case DD:
					return CD;
				default:
					throw new Exception("Error strategy");
			}
		}
	}

	public static Strategy mutateThirdTrait(Strategy strategy) throws Exception{
		Random random = new Random();
		// if the program enters this method, 
		// it means that at least one trait will be mutated
		// if it also needs to mutate the third trait
		switch(strategy){
			case CC:
				return CD;
			case DC:
				return DD;
			case CD:
				return CC;
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
