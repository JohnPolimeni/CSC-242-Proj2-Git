/*
 * Extra Credit, part of A of the Liars and Truth-Tellers
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 */
public class LiarsAndTruthTellersKB extends KB {

	public LiarsAndTruthTellersKB(){
		super();
		Symbol amy = intern("A");
		Symbol bob = intern("B");
		Symbol cal = intern("C");
		
		add(new Biconditional(amy,new Conjunction(amy,cal)));
		add(new Biconditional(bob,new Negation(cal)));
		add(new Biconditional(cal,new Disjunction(bob,new Negation(amy))));
		
	}
	
	public static void main(String[] argv) {
	LiarsAndTruthTellersKB liars = new LiarsAndTruthTellersKB();
	liars.dump();
	
	System.out.println("\tAmy is truthful: " + liars.TTEntails(liars,new Symbol("A")));
	System.out.println("\tBob is truthful: " + liars.TTEntails(liars, new Symbol("B")));
	System.out.println("\tCal is truthful: " + liars.TTEntails(liars, new Symbol("C")));
	System.out.println();
	System.out.println("\tAmy is truthful: " + liars.PLResolution(liars,new Symbol("A")));
	System.out.println("\tBob is truthful: " + liars.PLResolution(liars, new Symbol("B")));
	System.out.println("\tCal is truthful: " + liars.PLResolution(liars, new Symbol("C")));
	
	
}
}
	

