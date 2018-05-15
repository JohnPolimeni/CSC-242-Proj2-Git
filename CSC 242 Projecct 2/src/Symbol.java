import java.util.ArrayList;

public class Symbol implements Sentence {

	protected String name;
	
	public Symbol(String name){
		super();
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean isSatisfiedBy(Model model){
		return model.get(this);
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}
		else if(this == other){
			return true;
		}
		else if(!(other instanceof Symbol)){
			return false;
		}
		else{
			Symbol otherp = (Symbol) other;
			return name.equals(otherp.name);
		}
		
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public ArrayList<Symbol> getSymbols(){
		ArrayList<Symbol> symbols = new ArrayList<>();
		symbols.add(this);
		return symbols;
	}
}