public class ModusPonensKB extends KB {
	
	public ModusPonensKB() {
		super();
		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
		
	}

	// Main method to test if Modus Ponens is true
	public static void main(String[] argv) {
		ModusPonensKB mp = new ModusPonensKB();
		mp.dump();
	
		System.out.println("\tDoes {P, P => Q} entail Q: " + mp.TTEntails(mp,new Symbol("Q")));
		System.out.println();
	
		System.out.println("\tDoes {P, P => Q} entail Q: " + mp.PLResolution(mp, new Symbol("Q")));
	

	}

}