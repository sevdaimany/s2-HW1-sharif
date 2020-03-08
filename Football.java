import java.io.*;
import java.util.*;

class Player {
    String name;
    String lastName;

    public Player(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}

class TeamPlayers {
    ArrayList<Player> team = new ArrayList<Player>();
    String name;

    public TeamPlayers(String name) {
        this.name = name;
    }

    void addPlayer(Player player) {
        team.add(player);
    }
}

class Teams {
    ArrayList<TeamPlayers> teams = new ArrayList<TeamPlayers>();

    void addTeams(TeamPlayers team) {
        teams.add(team);
    }
}

public class Football {

    int check(TeamPlayers singleTeam, ArrayList<Player> allPlayers) {
        Iterator<Player> it = singleTeam.team.iterator();
        Iterator<Player> it2 = allPlayers.iterator();
        int num = 0;
        while (it.hasNext()) {

            Player c = it.next();
            while (it2.hasNext()) {
                if (c.equals(it2.next())) {
                    num = 1;
                }
            }
            if (num == 0) {
                return -1;
            }

        }
        return 0;

    }
     
   
    public static void main(String[] args) {
        int numTeam;
        int numPlayers;
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        Teams allTeams = new Teams();
        try {
            File x = new File("input.txt");
            Scanner sc = new Scanner(x);
            // read first line
            numPlayers = sc.nextInt();
            numTeam = sc.nextInt();

            // read and add all players
            for (int i = 0; i < numPlayers; i++) {
                String name = sc.next();
                String lastName = sc.next();
                Player singlePlayer = new Player(name, lastName);
                allPlayers.add(singlePlayer);
            }

            // read and add players in their team
            for (int i = 0; i < numTeam; i++) {
                String teamName = sc.nextLine();
                TeamPlayers team = new TeamPlayers(teamName);
                allTeams.addTeams(team);
                int numTeamPlayers = sc.nextInt();
                for (int j = 0; j < numTeamPlayers; j++) {
                    String name = sc.next();
                    String lastName = sc.next();
                    Player singlePlayer = new Player(name, lastName);
                    team.addPlayer(singlePlayer);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("cannot open the File!");
        }
    }

}