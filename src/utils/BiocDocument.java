package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;

public class BiocDocument {
	public String id;
	/*
	 * Title has an offset of zero, while the abstract is assumed to begin after the title and one space. 
	 */
	public String title;
	public String abstractt;
	
	/*public HashMap<String,Entity> entities;
	public HashMap<String,Relation> relations;*/
	public ArrayList<Entity> entities; // gold
	public HashSet<Relation> relations; // gold
	public ArrayList<Entity> preEntities; // predicted entities
	
	
	public BiocDocument(String id, String title, String abstractt) {
		super();
		this.id = id;
		this.title = title;
		this.abstractt = abstractt;
		
		entities = new ArrayList<Entity>(); // new HashMap<String,Entity>();
		relations = new HashSet<Relation>(); // new HashMap<String,Relation>();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	
	
	// Judge whether a token(begin at "start" and end at "end") is inside a gold entity or not.
	public boolean isInsideAGoldEntity(int start, int end) {
		for(Entity temp:entities) {
			if(start >= temp.offset && end <= (temp.offset+temp.text.length()-1))
			{
				return true;
			}
		}
		return false;
	}
	
	public Entity isInsideAGoldEntityAndReturnIt(int start, int end) {
		for(Entity temp:entities) {
			if(start >= temp.offset && end <= (temp.offset+temp.text.length()-1))
			{
				return temp;
			}
		}
		return null;
	}
	
	// Judge whether a token and its previous token are inside the same gold entity
	public boolean isTokenAndPreviousInsideAGoldEntity(int startToken, int endToken, int startPre, int endPre) {
		for(Entity temp:entities) {
			if(	startToken >= temp.offset && startPre >= temp.offset &&
					endToken <= (temp.offset+temp.text.length()-1) && endPre <= (temp.offset+temp.text.length()-1))
			{
				return true;
			}
		}
		return false;
	}
	
}
