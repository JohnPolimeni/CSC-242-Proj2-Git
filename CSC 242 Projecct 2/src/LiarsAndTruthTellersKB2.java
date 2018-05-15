/*
 * Extra Credit, part of B the Liars and Truth-Tellers
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 */
public class LiarsAndTruthTellersKB2 extends KB {

		public LiarsAndTruthTellersKB2(){
			super();
			Symbol amy = intern("A");
			Symbol bob = intern("B");
			Symbol cal = intern("C");
			
			add(new Biconditional(amy,new Negation(cal)));
			add(new Biconditional(bob,new Conjunction(amy,cal)));
			add(new Biconditional(cal,bob));
			
		}
		public static void main(String[] argv) {
			LiarsAndTruthTellersKB2 liars2 = new LiarsAndTruthTellersKB2();
			liars2.dump();
			
			System.out.println("\tAmy is truthful: " + liars2.TTEntails(liars2,new Symbol("A")));
			System.out.println("\tBob is truthful: " + liars2.TTEntails(liars2, new Symbol("B")));
			System.out.println("\tCal is truthful: " + liars2.TTEntails(liars2, new Symbol("C")));
			System.out.println();
			System.out.println("\tAmy is truthful: " + liars2.PLResolution(liars2,new Symbol("A")));
			System.out.println("\tBob is truthful: " + liars2.PLResolution(liars2, new Symbol("B")));
			System.out.println("\tCal is truthful: " + liars2.PLResolution(liars2, new Symbol("C")));
			
			
		}
	}