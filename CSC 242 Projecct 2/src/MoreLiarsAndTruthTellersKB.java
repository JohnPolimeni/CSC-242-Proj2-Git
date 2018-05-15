/*
 * Extra Credit, More Liars and Truth Tellers Problem 
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 */
public class MoreLiarsAndTruthTellersKB extends KB{
	
	public MoreLiarsAndTruthTellersKB(){
		super();
		Symbol amy = intern("A");
		Symbol bob = intern("B");
		Symbol cal = intern("C");
		Symbol dee = intern("D");
		Symbol eli = intern("E");
		Symbol fay = intern("F");
		Symbol gil = intern("G");
		Symbol hal = intern("H");
		Symbol ida = intern("I");
		Symbol jay = intern("J");
		Symbol kay = intern("K");
		Symbol lee = intern("L");
		
		add(new Biconditional(amy,new Conjunction(hal,ida)));
		add(new Biconditional(bob,new Conjunction(amy,lee)));
		add(new Biconditional(cal,new Conjunction(bob,gil)));
		add(new Biconditional(dee,new Conjunction(eli,lee)));
		add(new Biconditional(eli,new Conjunction(cal,hal)));
		add(new Biconditional(fay,new Conjunction(dee,ida)));
		add(new Biconditional(gil,new Conjunction(new Negation(eli),new Negation(jay))));
		add(new Biconditional(hal,new Conjunction(new Negation(fay),new Negation(kay))));
		add(new Biconditional(ida,new Conjunction(new Negation(gil),new Negation(kay))));
		add(new Biconditional(jay,new Conjunction(new Negation(amy),new Negation(cal))));
		add(new Biconditional(kay,new Conjunction(new Negation(dee),new Negation(fay))));
		add(new Biconditional(lee,new Conjunction(new Negation(bob),new Negation(jay))));
	}
	public static void main(String[] argv) {
		MoreLiarsAndTruthTellersKB liars = new MoreLiarsAndTruthTellersKB();
		liars.dump();
		
		System.out.println("\tAmy is truthful: " + liars.TTEntails(liars,new Symbol("A")));
		System.out.println("\tBob is truthful: " + liars.TTEntails(liars, new Symbol("B")));
		System.out.println("\tCal is truthful: " + liars.TTEntails(liars, new Symbol("C")));
		System.out.println("\tDee is truthful: " + liars.TTEntails(liars, new Symbol("D")));
		System.out.println("\tEli is truthful: " + liars.TTEntails(liars, new Symbol("E")));
		System.out.println("\tFay is truthful: " + liars.TTEntails(liars, new Symbol("F")));
		System.out.println("\tGil is truthful: " + liars.TTEntails(liars, new Symbol("G")));
		System.out.println("\tHal is truthful: " + liars.TTEntails(liars, new Symbol("H")));
		System.out.println("\tIda is truthful: " + liars.TTEntails(liars, new Symbol("I")));
		System.out.println("\tJay is truthful: " + liars.TTEntails(liars, new Symbol("J")));
		System.out.println("\tKay is truthful: " + liars.TTEntails(liars, new Symbol("K")));
		System.out.println("\tLee is truthful: " + liars.TTEntails(liars, new Symbol("L")));
		System.out.println();
		System.out.println("\tAmy is truthful: " + liars.PLResolution(liars,new Symbol("A")));
		System.out.println("\tBob is truthful: " + liars.PLResolution(liars, new Symbol("B")));
		System.out.println("\tCal is truthful: " + liars.PLResolution(liars, new Symbol("C")));
		System.out.println("\tDee is truthful: " + liars.PLResolution(liars, new Symbol("D")));
		System.out.println("\tEli is truthful: " + liars.PLResolution(liars, new Symbol("E")));
		System.out.println("\tFay is truthful: " + liars.PLResolution(liars, new Symbol("F")));
		System.out.println("\tGil is truthful: " + liars.PLResolution(liars, new Symbol("G")));
		System.out.println("\tHal is truthful: " + liars.PLResolution(liars, new Symbol("H")));
		System.out.println("\tIda is truthful: " + liars.PLResolution(liars, new Symbol("I")));
		System.out.println("\tJay is truthful: " + liars.PLResolution(liars, new Symbol("J")));
		System.out.println("\tKay is truthful: " + liars.PLResolution(liars, new Symbol("K")));
		System.out.println("\tLee is truthful: " + liars.PLResolution(liars, new Symbol("L")));
		
		
	}

}