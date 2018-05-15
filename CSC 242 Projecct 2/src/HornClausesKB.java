/*
 * Class for the Horn Clause problem. The information in the problem is 
 * used in the constructor, so when an instance of HornClausesKB is created
 * it has the correct knowledge base
 */
public class HornClausesKB extends KB {

	public HornClausesKB() {
		super();
		Symbol mythical = intern("M");
		Symbol mortal = intern("MO");
		Symbol mammal = intern("MAM");
		Symbol horned = intern("H");
		Symbol magical = intern("MA");
		
		add(new Implication(mythical,new Negation(mortal)));
		add(new Implication(new Negation(mythical),new Conjunction(mortal,mammal)));
		add(new Implication(new Disjunction(new Negation(mortal),mammal),horned));
		add(new Implication(horned,magical));
	}

	// Main method to test the three Horn Cluases questions
	public static void main(String[] argv) {
		HornClausesKB hc = new HornClausesKB();
		hc.dump();
		
		System.out.println("\tWe can prove that the unicorn is mythical: " + hc.TTEntails(hc,new Symbol("M")));
		System.out.println("\tWe can prove that the unicorn is magical: " + hc.TTEntails(hc, new Symbol("MA")));
		System.out.println("\tWe can prove that the unicorn is horned: " + hc.TTEntails(hc, new Symbol("H")));
		System.out.println();
		System.out.println("\tWe can prove that the unicorn is mythical: " + hc.PLResolution(hc,new Symbol("M")));
		System.out.println("\tWe can prove that the unicorn is magical: " + hc.PLResolution(hc, new Symbol("MA")));
		System.out.println("\tWe can prove that the unicorn is horned: " + hc.PLResolution(hc, new Symbol("H")));
	
	}
}