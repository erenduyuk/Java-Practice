import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

public class FixtureMaker {
	
	private ArrayList<String> teams = new ArrayList<>();
	
	public void makeEven() {
		if(teams.size() % 2 != 0) {
			teams.add("Empty");
		}
	}

	public ArrayList<String> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<String> teams) {
		this.teams = teams;
	}
	
	public void rounds() {
		makeEven();
		ArrayList<String> tempList = new ArrayList<>();
		tempList = matchup();
		int k = 0;
		for(int i = 0; i < (teams.size() * 2 - 2) ; i++) {
			System.out.println("Round " + (i+1));
			for(int j = 0; j < (teams.size() / 2); j++) {
				System.out.println(tempList.get(k));
				k++;
			}
			System.out.println();
		}
	}
	
	public ArrayList<String> matchup() {
		int totalMatchNumber = (teams.size() * 2 - 2) * (teams.size() / 2);
		ArrayList<String> tempList = new ArrayList<>();
		LinkedHashMap<Integer,String> set = new LinkedHashMap<>();
		Random random = new Random();
		ArrayList<String> tempTeamList = new ArrayList<>();
		int a = 0, b = 0, linkedListNumber = 0;
		
		while(set.size() != totalMatchNumber) {
			while(tempTeamList.size() != teams.size()) {
				a = random.nextInt(teams.size());
				b = random.nextInt(teams.size());
				if(a != b && !(tempTeamList.contains(teams.get(a)) || tempTeamList.contains(teams.get(b)))) {
					set.put(linkedListNumber, teams.get(a) + " vs " + teams.get(b));
					linkedListNumber++;
					tempTeamList.add(teams.get(a));
					tempTeamList.add(teams.get(b));
				}
			}
			tempTeamList.clear();
		}
		
		for(String element: set.values()) {
			tempList.add(element);
		}
		
		return tempList;
	}
}
