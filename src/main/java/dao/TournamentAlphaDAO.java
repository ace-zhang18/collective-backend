package dao;

import java.util.ArrayList;
import java.util.List;

import objects.Player;
import objects.PlayerSearchQuery;
import objects.PlayerSearchResult;
import objects.TournamentAlpha;

public class TournamentAlphaDAO {
	public static void insert(TournamentAlpha tournament_alpha) throws Exception{
		SqlSessionContainer.getSession().insert("TournamentAlpha.insert", tournament_alpha);
		System.out.println("TournamentAlpha successfully inserted");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static List<TournamentAlpha> getAll() throws Exception{
		List<TournamentAlpha> tournament_alpha_list = new ArrayList<>();
		
		tournament_alpha_list = SqlSessionContainer.getSession().selectList("TournamentAlpha.getAll");
		
		return tournament_alpha_list;		
	}
	
	public static List<TournamentAlpha> search(String request){
		ArrayList<TournamentAlpha> tourney_list = null;
		try {
			tourney_list = (ArrayList<TournamentAlpha>) getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<TournamentAlpha> result_list = new ArrayList<TournamentAlpha>();
		
		for(TournamentAlpha tourney: tourney_list) {
			if(tourney.getName().contains(request)) {
				result_list.add(tourney);
			}
		}
		
		return result_list;
	}
	
	public static TournamentAlpha get(int tournament_id) throws Exception{
		TournamentAlpha tournament = (TournamentAlpha) SqlSessionContainer.getSession().selectOne("TournamentAlpha.get", tournament_id);
		
		return tournament;		
	}
}
