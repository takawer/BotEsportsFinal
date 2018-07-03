package model;
import java.util.List;

public class Team {
	
	private String full_name;
	private String short_name;
	private String country;
	private List<Team> teams;
	
	
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String Country) {
		this.country = Country;
	}
	
}
