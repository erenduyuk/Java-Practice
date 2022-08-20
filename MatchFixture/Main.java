import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FixtureMaker fixture = new FixtureMaker();
		
		fixture.getTeams().add("Sevilla");
		fixture.getTeams().add("Atletico Madrid");
		fixture.getTeams().add("Milano");
		fixture.getTeams().add("Liverpool");
		//fixture.getTeams().add("Juventus");
		//fixture.getTeams().add("Real Madrid");
		
		fixture.rounds();
	}

}
