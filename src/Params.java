/**
 * A class that defining all parameters using in the model
 * @author haomai
 * @author Yudong
 *
 */
public class Params {
		// The rate of mutation for a reproducing child
		public static double MUTATIONRATE = 0.005;
		
		// the initial rate of reproducing
		public static double INITIALPTR = 0.12;
		
		// the rate of agent death
		public static double DEATHRATE = 0.10;
		
		// the number of agent giving to others
		public static double COSTOFGIVING = 0.01;
		
		// the number of agent receiving from others
		public static double GAINOFRECEIVING = 0.03;
		
		// the number of agent generating per day
		public static double IMMIGRANTSPERDAY = 1;
		
		// the rate of agent cooperating with same type
		public static double IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
		
		// the rate of agent cooperating with different type
		public static double IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
		
		// the number of Region
		public final static int NUMOFREGION = 8;
		
		// the total time tick
		public final static int TOTALTICK = 200;
		
		// the size of the world
		public final static int SIZE = 50;

		public static void setParams(int i){
			switch (i) {
				case 0:
					// round one, same value as original model
					MUTATIONRATE = 0.005;
					INITIALPTR = 0.12;
					DEATHRATE = 0.10;
					COSTOFGIVING = 0.01;
					GAINOFRECEIVING = 0.03;
					IMMIGRANTSPERDAY = 1;
					IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
					IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
					break;
				case 1:
					// round two, increase the initial-PTR
					// increase cost-of-giving
					// increase gain of receiving
					MUTATIONRATE = 0.005;
					INITIALPTR = 0.25;
					DEATHRATE = 0.10;
					COSTOFGIVING = 0.05;
					GAINOFRECEIVING = 0.10;
					IMMIGRANTSPERDAY = 1;
					IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
					IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
					break;
				case 2:
					// round three
					// significantly increase death-rate
					// increase immigrant-per-day
					MUTATIONRATE = 0.005;
					INITIALPTR = 0.12;
					DEATHRATE = 0.20;
					COSTOFGIVING = 0.01;
					GAINOFRECEIVING = 0.03;
					IMMIGRANTSPERDAY = 5;
					IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
					IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
					break;
				case 3:
					// round four
					// significantly increase mutation-rate
					// increase initial-PTR
					// increase immigrant-per-day
					MUTATIONRATE = 0.10;
					INITIALPTR = 0.20;
					DEATHRATE = 0.10;
					COSTOFGIVING = 0.01;
					GAINOFRECEIVING = 0.03;
					IMMIGRANTSPERDAY = 5;
					IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.50;
					IMMIGRANTCHANGECOOPERATEWITHSAME = 0.50;
					break;
				case 4:
					// round five
					// significantly increase cooperate-with-different
					// decrease cooperate-with-same
					// increase initial-PTR
					MUTATIONRATE = 0.005;
					INITIALPTR = 0.12;
					DEATHRATE = 0.10;
					COSTOFGIVING = 0.01;
					GAINOFRECEIVING = 0.03;
					IMMIGRANTSPERDAY = 5;
					IMMIGRANTCHANGECOOPERATEWITHDIFFERENT = 0.80;
					IMMIGRANTCHANGECOOPERATEWITHSAME = 0.40;
					break;
			}
		}

}
