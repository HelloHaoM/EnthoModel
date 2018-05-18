import java.util.Random;

/**
 * A enum that represent different agent's region
 * 
 * @author haomai
 * @author Yudong
 *
 */
public enum Region {
	RED, YELLOW, BLUE, GREEN, GRAY, ORANGE, BROWN, PURPLE;
	
	public static Region randomSelectRegion() throws Exception{
		Random random = new Random();
		switch(random.nextInt(Params.NUMOFREGION)){
			// randomly choose a color and then return it
			case 0:
				return RED;
			case 1:
				return YELLOW;
			case 2:
				return BLUE;
			case 3:
				return GREEN;
			case 4:
				return GRAY;
			case 5:
				return ORANGE;
			case 6:
				return BROWN;
			case 7:
				return PURPLE;
			default:
				throw new Exception("Error Region");	
		}
	}

}
