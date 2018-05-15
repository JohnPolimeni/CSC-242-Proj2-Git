/*
 * Created By: John Polimeni, Isaac Wong, Luke Gerstner
 * Created On: October 15, 2017
 */
import java.util.HashMap;
import java.util.Map;

public class MyModel implements Model {
	
	public Map<Sentence,Boolean> symbolMap;
	
	// Constructor for MyModel, it has a new empty hashmap every time it is created.
	public MyModel(){
		symbolMap = new HashMap<>();
	}
	

	@Override
	public void set(Sentence sym, boolean value) {
		symbolMap.put(sym, value);
		
	}

	@Override
	public boolean get(Symbol sym) {
//		System.out.println(symbolMap);
//		System.out.println("this is sym: " + sym);
		return symbolMap.get(sym);
	}

	@Override
	//  Go through kb's table of symbols and check if the symbol map at s has a 
	//	false entry if so then the knowledge base does not satisfy the model
	public boolean satisfies(KB kb) {
		for(Symbol s: kb.symbols()){
			if(symbolMap.get(s).equals(Boolean.FALSE)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean satisfies(Sentence sentence) {
		return sentence.isSatisfiedBy(this); 
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub
		
	}
	
	// Make a clone of the original hashmap
	public MyModel clone(){
		MyModel cloneModel = new MyModel();
		 Map<Sentence,Boolean> newMap = new HashMap<>();
		 for(Sentence s : symbolMap.keySet()){
			 newMap.put(s,new Boolean(symbolMap.get(s)));
		 }
		 cloneModel.symbolMap = newMap;
		return cloneModel;
	}

	
}