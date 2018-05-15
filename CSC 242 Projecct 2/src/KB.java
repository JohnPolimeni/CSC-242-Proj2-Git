/*
 * Creator: George Ferguson
 * Edited By: John Polimeni, Isaac Wong, Luke Gerstner
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



/**
 * A KB is a set (actually a List) of Sentences and a SymbolTable
 * holding the PropositionalSymbols used in those sentences.
 */
public class KB {
	static int count = 0;

	protected List<Sentence>sentences;
	protected SymbolTable symtab;

	public KB(List<Sentence> sentences, SymbolTable symtab) {
		this.sentences = sentences;
		this.symtab = symtab;
	}

	public KB() {
		this(new LinkedList<Sentence>(), new SymbolTable());
	}

	/**
	 * Return the Symbols interned in this KB's SymbolTable
	 * as a Collection.
	 */
	public Collection<Symbol> symbols() {
		return symtab.symbols();
	}

	/**
	 * Return this KB's Sentences as a Collection. 
	 */
	public Collection<Sentence> sentences() {
		return sentences;
	}

	/**
	 * Intern the given name in this KB's SymbolTable and return
	 * the corresponding Symbol.
	 */
	public Symbol intern(String name) {
		return symtab.intern(name);
	}

	/**
	 * Add the given Sentence to this KB.
	 */
	public void add(Sentence s) {
		sentences.add(s);
	}

	/**
	 * Print the contents of this KB to System.out.
	 */
	public void dump() {
		for (Sentence s : sentences()) {
			System.out.println(s);
		}
	}
	
	/*
	 * TTEntails algorithm comes from AIMA Figure 7.10 (pg. 248)
	 */

	public boolean TTEntails(KB kb,Sentence alpha){
		System.out.println("<Solving by Model Checking>");
			
		// Create an ArrayList of the symbols from the knowledge base
		ArrayList<Symbol> kbList = new ArrayList<>(kb.symbols());
		
		ArrayList<Symbol> alphaList = alpha.getSymbols();

	
		for(Symbol sym : alphaList){
			// check if s is in the knowledge base list already, if it's not then add it to the list
			System.out.println("Name "+ sym.name);
			if(!(kbList.contains(sym))){
				kbList.add(sym);
			}
		}
		MyModel model = new MyModel();

		
		return TTCheckAll(kb,alpha,kbList,model);
	}
	

	//recusively iterates through all possible boolean values of all possible symbol values until it either finds a model that works or goes through every iteration
	public boolean TTCheckAll(KB kb, Sentence alpha, List<Symbol> symbols, MyModel model){
		//if there are no symbols left to assign boolean values to
		if(symbols.isEmpty()){
			
			//counts how many models were checked
			count++;
			
			boolean temp = true;

			//if the sentence is not satisfied by the model, remember that at least one sentence didnt work
			for(Sentence s: kb.sentences){
				if(!s.isSatisfiedBy(model)){
					temp = false;
				}
			}

			
			//if all sentences were satisfied by the model, check to see if alpha is also satisfied by the model
			if(temp){
				return alpha.isSatisfiedBy(model);
			} 
			else{
				return true;
			}

		}
		else{
			// Make a new Sentence that is the first element from the list
			Sentence P = symbols.get(0); 
			// Make a new list that does not include the first element from the original list, copy and not a pointer
			ArrayList<Symbol> rest = new ArrayList<Symbol>(symbols.subList(1, symbols.size()));
			MyModel m1 = model.clone();
			m1.set(P, true);
			MyModel m2 = model.clone();
			m2.set(P, false);
			
			//makes the recursive call that checks all possible models
			
			//*********** comment the below line out to see how many models were checked ******************//
			//System.out.println("models checked: " + count);
			return(TTCheckAll(kb,alpha,rest,m1) && TTCheckAll(kb,alpha,rest,m2));

		}

	}
	
	/*
	 * PLResolution algorithm comes from AIMA Figure 7.12 (pg. 255)
	 */
	
	public boolean PLResolution(KB kb, Sentence alpha){
		System.out.println("<Solving by Resolution>");
		//Empty set to be used later
		ArraySet<Clause> newClauseSet = new ArraySet<Clause>();
		// Convert the knowledge base to clauses
		Set<Clause> clauses =  CNFConverter.convert(kb);
		// Negate the sentence alpha and then convert it into a clause
		Set<Clause> alphaSet = CNFConverter.convert(new Negation(alpha));
		
		// Add all clauses from the sentence's clause(s) to the knowledge base clauses
		clauses.addAll(alphaSet);
		
		//PL resolution is sound and complete so it will always find either an empty set of resolved clauses or return false; so it's not an infinite while loop
		//because the algorithm has to iterate through every symbol, it takes exponential time
		while(true){
			
			//using an iterator instead of the .get(i) method of an arraylist because arraylists are confusing and hard to implement
			Iterator<Clause> iterator = clauses.iterator();
			//Continue while there is still another clause to go to
			while(iterator.hasNext()){
				Iterator<Clause> iterator2 = clauses.iterator();
				Clause tempClause = iterator.next();
				
				//double nested while loops used to compare each pair of clauses in the sets of clauses
				while(iterator2.hasNext()){
					//while there are still pairs to compare to
					
					Clause tempClause2 = iterator2.next();
					// Check to see if the two clauses don't equal each other
					if(!(tempClause.equals(tempClause2))){
						
						//this set equals the result of resolving the two clauses
						ArraySet<Clause> resolvents = PLResolve(tempClause,tempClause2);
						// Check if the new set of clauses is empty
							for(Clause c : resolvents){
							if(c.isEmpty()){
								
								//*********** comment the below line out to see how many resolutions were made ******************//
								System.out.println("resolutions resolved: " + count);
							
								
								//an empty clause will have zero elements,
								//means that the algorithm found a contradiction
								return true;
							}
					}
							//add the new resolvent set of clauses to the original 'new' set from before the while loops
							newClauseSet.addAll(resolvents);
 				}
			}
		}
			//if the resolve method didn't resolve any new clauses, then the algorithm determines no solution possible
			if(clauses.containsAll(newClauseSet)){
				
				//*********** comment the below line out to see how many resolutions were made ******************//
				System.out.println("resolutions resolved: " + count);
			
				
				return false;
			}
			
			//add the found clauses and start again
			clauses.addAll(newClauseSet);
		}
	}
	
	// Helper method to be used in PLResolution 
	//returns the set of all possible clauses obtained by resolving the two inputs
	public ArraySet<Clause> PLResolve(Clause c1, Clause c2){
		
		//first create a copy of the input clauses to avoid problems with pointers
		//Clause.copy() was a mehtod we wrote to copy the values of the objects of a clause
		Clause copyC1 = c1.copy();
		Clause copyC2 = c2.copy();
		
		
		//because clauses are sets of objects, we need a way to iterate through all the objects
		//using an arraylist so that we could access individual elements with .get(i) wasn't efficient and gave us problems with casting sets to arraylists
		//instead we used an iterator
		Iterator<Literal> copyC1Literal = copyC1.iterator();
		Iterator<Literal> copyC2Literal = copyC2.iterator();
		
		//a set of resolvents
		ArraySet<Clause> resolvents = new ArraySet<>();
		
		//iterate through the elements of the first clause
		while(copyC1Literal.hasNext()){
			//store the next current literal of the clause 
			Literal tempLiteral1 = copyC1Literal.next();
	
			//iterate through the literals of the second clause
			//double nested while loops to compare each possible pair of elements of each clause
			while(copyC2Literal.hasNext()){
				
				//hold the current literal from clause 2
				Literal tempLiteral2 = copyC2Literal.next();
				
				//compare the literal symbols of the two literals we're currently looking at
				boolean tempContentComparison = tempLiteral1.getContent().equals(tempLiteral2.getContent());
				//copmare the literal polarities of the two literals we're currently looking at
				boolean tempPolarityComparison = tempLiteral1.getPolarity().equals(tempLiteral2.getPolarity());
				//for example, if the current literal of clause1 is 'P' and the current literal of clause2 is '!P', tempCotentComparison is TRUE and tempPolarityComparison is FALSE
				
				
				//if the two literals are the same but with different polarities,
				//if the if statement evaluates to true, then we resolve the two literals
				if(tempContentComparison && !tempPolarityComparison){
					
					//counter to determine how many resolutions were done
					count++;
					
					//resolving first means removing the current literals from both clauses that its from
					copyC1.remove(tempLiteral1);
					copyC2.remove(tempLiteral2);
					
					//then add the second literal/clause to the first to finish the resolution step
					copyC1.addAll(copyC2);
					
					//add the newly resolved clause to the list of resolved clauses
					resolvents.add(copyC1);
					
					//return the updated list of resolved clauses
					return resolvents;
				}
			}
		}
		
		//update the list of reolved clauses, need to include both clauses
		resolvents.add(copyC1);
		resolvents.add(copyC2);
		
		//return list of resolved clauses
		return resolvents;
		
	}
	
	
}