package objects;

import java.util.Calendar;

import org.json.JSONObject;

import utilities.JSONInterface;

public class SingleElimBracketEntry implements JSONInterface{
	int entry_id;
	int tournament_id;
	int competitor_id;
	int score;
	int parent_id;
	int child_id1;
	int child_id2;
	int table_x;
	int table_y;
	

	
	public SingleElimBracketEntry(int entry_id, int tournament_id,int competitor_id, int score,  int parent_id,
			int child_id1, int child_id2, int table_x, int table_y) {
		super();
		this.entry_id = entry_id;
		this.tournament_id = tournament_id;
		this.competitor_id = competitor_id;
		this.score = score;
		this.parent_id = parent_id;
		this.child_id1 = child_id1;
		this.child_id2 = child_id2;
		this.table_x = table_x;
		this.table_y = table_y;
	}


	public SingleElimBracketEntry(int tournament_id, int competitor_id, int score, int parent_id, int child_id1,
			int child_id2, int table_x, int table_y) {
		super();
		this.tournament_id = tournament_id;
		this.competitor_id = competitor_id;
		this.score = score;
		this.parent_id = parent_id;
		this.child_id1 = child_id1;
		this.child_id2 = child_id2;
		this.table_x = table_x;
		this.table_y = table_y;
	}
	

	public int getEntry_id() {
		return entry_id;
	}


	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}


	public int getTournament_id() {
		return tournament_id;
	}


	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public int getCompetitor_id() {
		return competitor_id;
	}


	public void setCompetitor_id(int competitor_id) {
		this.competitor_id = competitor_id;
	}

	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getParent_id() {
		return parent_id;
	}


	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}


	public int getChild_id1() {
		return child_id1;
	}


	public void setChild_id1(int child_id1) {
		this.child_id1 = child_id1;
	}


	public int getChild_id2() {
		return child_id2;
	}


	public void setChild_id2(int child_id2) {
		this.child_id2 = child_id2;
	}


	public int getTable_x() {
		return table_x;
	}


	public void setTable_x(int table_x) {
		this.table_x = table_x;
	}


	public int getTable_y() {
		return table_y;
	}


	public void setTable_y(int table_y) {
		this.table_y = table_y;
	}


	public String toJSON() {
		String json = "{";
		int items = 0;
		
		if(entry_id > -1) {
			json += "\"entry_id\":\"" + entry_id + "\"";
			items++;
		}
		if(tournament_id > -1) {
			if(items > 0) json += ",";
			json += "\"tournament_id\":\"" + tournament_id + "\"";
			items++;
		}
		if(competitor_id > 0) {
			if(items > 0) json += ",";
			json += "\"competitor_id\":\"" + competitor_id + "\"";
			items++;
		}
		if(score > -1) {
			if(items > 0) json += ",";
			json += "\"score\":\"" + score + "\"";
			items++;
		}
		if(parent_id > 0) {
			if(items > 0) json += ",";
			json += "\"parent_id\":\"" + parent_id + "\"";
			items++;
		}
		if(child_id1 > 0) {
			if(items > 0) json += ",";
			json += "\"child_id1\":\"" + child_id1 + "\"";
			items++;
		}
		if(child_id2 > 0) {
			if(items > 0) json += ",";
			json += "\"child_id2\":\"" + child_id2 + "\"";
			items++;
		}

		if(table_x > -1) {
			if(items > 0) json += ",";
			json += "\"table_x\":\"" + table_x + "\"";
			items++;
		}
		if(table_y > -1) {
			if(items > 0) json += ",";
			json += "\"table_y\":\"" + table_y + "\"";
			items++;
		}
		json += "}";
		return json;
	}
}
