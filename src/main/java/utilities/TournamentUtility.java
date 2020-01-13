package utilities;

import java.util.ArrayList;
import java.util.Collections;

import objects.SingleElimBracketEntry;
import objects.Player;

public class TournamentUtility {
	public static void generateSeedings(ArrayList<Player> entrants){
		int size_factor = 0;
		for(size_factor = 0; Math.pow(2.0, size_factor) < entrants.size(); size_factor++) {
			
		}
		size_factor--;
		ArrayList<Player> byes = new ArrayList<Player>();
		/*
		for(int i = (int) Math.pow(2.0, size_factor); i < entrants.size(); ) {
			byes.add(entrants.remove((int)(Math.random()*entrants.size())));
		}*/
		
		for(int i = entrants.size() - 1; i >= Math.pow(2.0, size_factor); i--) {
			entrants.remove(i);
		}
		
		for(int i = 0, e = entrants.size()/2 - 1; i < entrants.size()/4; i++, e--) {
			Collections.swap(entrants, 2*i + 1, 2*e + 1);
		}
		
		int block_size = 2;
		
		for(int i3 = 0; i3 < size_factor-1; i3++) {
			for(int i2 = 0; i2 < entrants.size()/(2*block_size) - 1; i2++) {
				int add_index = (2*i2+1) * block_size;
				int rem_index = entrants.size()/2 + 1 + i2 * block_size;
				if(add_index + block_size + 1 != rem_index) {
					for(int i = 0; i < block_size; i++) {
						entrants.add(add_index, entrants.remove(rem_index));
					}
				}else {

					for(int i = 0; i < block_size; i++) {
						Collections.swap(entrants, add_index + i, rem_index + i - 1);
					}
				}
			}
			block_size *= 2;
		}
		
		populateBracket(entrants, byes);
	}

	public static void populateBracket(ArrayList<Player> contenders, ArrayList<Player> byes){
		//contenders should be a factor of 2;
		int size_factor = 0;
		for(size_factor = 0; Math.pow(2.0, size_factor) < contenders.size(); size_factor++) {

		}

		ArrayList<SingleElimBracketEntry> entries = new ArrayList<SingleElimBracketEntry>();

		//testing purposes
		for(Player p: byes) {
			byes.remove(p);
		}

		if(byes.size() == 0) {
			for(int i = 0; i < contenders.size(); i++) {
				entries.add(new SingleElimBracketEntry(8, contenders.get(i).getId(), 0, 0,  0, 0, 0, i));
			}
		}

		String json = JSONUtility.ToJSONArray(entries);	
		json = json.replace("},", "}\n");
		System.out.println(json);
	}
}
