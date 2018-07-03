package model;
import java.util.List;

public class Jogador {

	private List<JogadorAttr> players;
	
	
	public List<JogadorAttr> getPlayers() {
		return players;
	}


	public void setPlayers(List<JogadorAttr> players) {
		this.players = players;
	}


	public class JogadorAttr{
		private String in_game_name;
		private String real_life_name;
		private String birth_place;
		private String game;
		private int id;
		
		
		public String getIn_game_name() {
			return in_game_name;
		}
		public void setIn_game_name(String in_game_name) {
			this.in_game_name = in_game_name;
		}
		public String getReal_life_name() {
			return real_life_name;
		}
		public void setReal_life_name(String real_life_name) {
			this.real_life_name = real_life_name;
		}
		public String getBirth_place() {
			return birth_place;
		}
		public void setBirth_place(String birth_place) {
			this.birth_place = birth_place;
		}
		public String getGame() {
			return game;
		}
		public void setGame(String game) {
			this.game = game;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
	}

}
