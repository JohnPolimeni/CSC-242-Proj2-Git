/*
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 * Second Problem we had to check to see if our algorithms worked properly
 */
public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");

		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);

	}

	// Main method to test if there is a pit in 1,2 in this Wumpus World
	public static void main(String[] argv) {
		WumpusWorldKB ww = new WumpusWorldKB();
		ww.dump();
		
		System.out.println("\tThere is a pit in [1,2]: " + ww.TTEntails(ww,new Symbol("P1,2")));
		System.out.println();
		
		System.out.println("\tThere is a pit in [1,2]: " + ww.PLResolution(ww,new Symbol("P1,2")));

	}

}