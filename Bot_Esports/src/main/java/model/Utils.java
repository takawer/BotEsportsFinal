package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Utils {
	private static BufferedReader parse;

	public String GetResponse(URL url) {
		HttpURLConnection con;
		String jsonText = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			parse = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;

			StringBuffer response = new StringBuffer();

			while ((inputLine = parse.readLine()) != null) {
				response.append(inputLine);
			}
			try {
				jsonText = response.toString();
			} finally {
				if (parse == null) {

					try {
						parse.close();

					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return jsonText;

	}

	public Jogador deserializePlayers(String jsonText) {
		Gson gson = new Gson();
		Jogador player = gson.fromJson(jsonText, Jogador.class);

		return player;

	}
	
	public Team deserializeTeams(String jsonText) {
		Gson gson = new Gson();
		Team team= gson.fromJson(jsonText, Team.class);

		return team;

	}

}
