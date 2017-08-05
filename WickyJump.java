public class WickyJump{
	
	public static void main(String[] args){
		int hops=10;
		int[] distances={100,600,10};
		int[] secs={1,2,5};
		
		int totalDistance = 0;
		int totalSecs =0;
		
		int round = 0;
		for(int hopRound=1;hopRound<=hops;hopRound++){
			round++;
			totalDistance+=distances[round-1];
			System.out.println("Hop "+hopRound+"\t"+"Distance "+totalDistance+"\t"+"Seconds "+totalSecs);
			if(hopRound==hops){break;}	// If last hop time should not caluculate
			totalSecs+=secs[round-1];
			if(round==distances.length) {round = 0;}
		}		
		System.out.println("\nResult:\nTotal hops "+hops+"\n"+"Total distance "+totalDistance+"\n"+"Total seconds "+totalSecs);
		
	}

}