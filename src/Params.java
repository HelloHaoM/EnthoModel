/**
 * A class that defining all parameters using in the model
 * @author haomai
 *
 */
public class Params {
		// The rate of mutation for a reproducing child
		public final static double MUTATIONRATE = 0.005;
		
		// the initial rate of reproducing
		public final static double INITIALPTR = 0.12;
		
		// the rate of agent death
		public final static double DEATHRATE = 0.10;
		
		// the number of agent giving to others
		public final static double COSTOFGIVING = 0.01;
		
		// the number of agent receiving from others
		public final static double GAINOFRECEIVING = 0.03;
		
		// the number of agent generating per day
		public final static double IMMIGRANTSPERDAY = 1;
		
		// the rate of agent cooperating with same type
		public final static double IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
		
		// the rate of agent cooperating with different type
		public final static double IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
		
		// the number of Region
		public final static int NUMOFREGION = 4;
		
		// the total time tick
		public final static int TOTALTICK = 2000;
		
		// the size of the world
		public final static int SIZE = 50;

}
