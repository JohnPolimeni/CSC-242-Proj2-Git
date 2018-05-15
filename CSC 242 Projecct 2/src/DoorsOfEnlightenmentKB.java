/*
 *  Extra Credit, part of A of the Doors of Enlightenment problem
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 *
 */
public class DoorsOfEnlightenmentKB extends KB {
	
	public DoorsOfEnlightenmentKB(){
		Symbol a = intern("A");
		Symbol b = intern("B");
		Symbol c = intern("C");
		Symbol d = intern("D");
		Symbol e = intern("E");
		Symbol f = intern("F");
		Symbol g = intern("G");
		Symbol h = intern("H");
		Symbol w = intern("W");
		Symbol x = intern("X");
		Symbol y = intern("Y");
		Symbol z = intern("Z");
		
		add(new Biconditional(a,x));
		add(new Biconditional(b,new Disjunction(y,z)));
		add(new Biconditional(c,new Conjunction(a,b)));
		add(new Biconditional(d,new Conjunction(x,y)));
		add(new Biconditional(e,new Conjunction(x,z)));
		add(new Biconditional(f,new Disjunction(d,e)));
		add(new Biconditional(g,new Implication(c,f)));
		add(new Biconditional(h,new Implication(new Conjunction(g,h),a)));
		
	}
	
	public static void main(String[] argv) {
		DoorsOfEnlightenmentKB door = new DoorsOfEnlightenmentKB();
		System.out.println("The philosopher should choose door W: " + door.TTEntails(door, new Symbol("W")));
		System.out.println("The philosopher should choose door X: " + door.TTEntails(door, new Symbol("X")));
		System.out.println("The philosopher should choose door Y: " + door.TTEntails(door, new Symbol("Y")));
		System.out.println("The philosopher should choose door Z: " + door.TTEntails(door, new Symbol("Z")));
		
		System.out.println(door.PLResolution(door, new Symbol("W")));
		System.out.println(door.PLResolution(door, new Symbol("X")));
		System.out.println(door.PLResolution(door, new Symbol("Y")));
		System.out.println(door.PLResolution(door, new Symbol("Z")));
		
		
	}
	

}