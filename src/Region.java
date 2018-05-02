import java.util.Random;

/**
 * A enum that represent different agent's region
 * 
 * @author haomai
 *
 */
public enum Region {
	RED, YELLOW, BLUE, GREEN;
	
	public static Region randomSelectRegion() throws Exception{
		Random random = new Random();
		switch(random.nextInt(Params.NUMOFREGION)){
		case 0:
			return RED;
		case 1:
			return YELLOW;
		case 2:
			return BLUE;
		case 3:
			return GREEN;
		default:
			throw new Exception("Error Region");	
		}
	}

}
