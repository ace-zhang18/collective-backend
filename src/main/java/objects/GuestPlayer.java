package objects;

public class GuestPlayer {
	int guest_id, player_id, tournament_id;

	public GuestPlayer(int guest_id, int player_id, int tournament_id) {
		super();
		this.guest_id = guest_id;
		this.player_id = player_id;
		this.tournament_id = tournament_id;
	}

	public int getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}
	
	
}
