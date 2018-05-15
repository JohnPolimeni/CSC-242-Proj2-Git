import java.util.ArrayList;

public interface Sentence {
	
	/**
	 * Return true if this Sentence is satisfied by the given Model.
	 */
	public boolean isSatisfiedBy(Model model);

	public ArrayList<Symbol> getSymbols();

}
