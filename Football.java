import java.io.*;
import java.util.*;

class Player {
    private String fullName;

    public Player(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        return true;
    }

}

class TeamPlayers {
    ArrayList<Player> team = new ArrayList<Player>();
    private String name;
    

    public TeamPlayers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addPlayer(Player player) {
        team.add(player);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        TeamPlayers other = (TeamPlayers) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

   

}

class Teams {
    ArrayList<TeamPlayers> teams = new ArrayList<TeamPlayers>();

    void addTeams(TeamPlayers team) {
        teams.add(team);
    }
}

public class Football {

    // check is all the playrers in team are allowed to play or not
    int check(TeamPlayers singleTeam, ArrayList<Player> allPlayers) {
        Iterator<Player> it = singleTeam.team.iterator();

        int num = 0;
        while (it.hasNext()) {
            Iterator<Player> it2 = allPlayers.iterator();
            Player c = it.next();
            while (it2.hasNext()) {
                boolean a = c.equals(it2.next());
                if (a) {
                    num = 1;
                    break;
                }
            }
            if (num == 0) {
                return 1;
            }

        }
        return 0;

    }

    // check that each player just be in one team
    int check2(TeamPlayers singleTeam, Teams allTeams, ArrayList<Integer> eachTeamPlayerNum) {
        Iterator<TeamPlayers> it = allTeams.teams.iterator();
        Iterator<Integer> it2 = eachTeamPlayerNum.iterator();
        while (it.hasNext()) {

            int j = it2.next();
            TeamPlayers teamPlayers = it.next();
            if (singleTeam.equals(teamPlayers))
                continue;
            else {
                for (int i = 0; i < j; i++) {
                    Iterator<Player> itPlayer = singleTeam.team.iterator();
                    while (itPlayer.hasNext()) {
                        if (itPlayer.next().equals(teamPlayers.team.get(i))) {
                            return 1;
                        }
                    }
                }
            }
            j++;
        }
        return 0;
    }

    public static void main(String[] args) {
        int numTeam;
        int numPlayers;
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        // arraylist for keeping teams player num
        ArrayList<Integer> eachTeamPlayerNum = new ArrayList<Integer>();
        Teams allTeams = new Teams();
        try {
            File x = new File("input.txt");
            Scanner sc = new Scanner(x);
            // read first line
            numPlayers = sc.nextInt();
            numTeam = sc.nextInt();

            // read and add all players
            sc.nextLine();
            for (int i = 0; i < numPlayers; i++) {
                String name = sc.nextLine();
                Player singlePlayer = new Player(name);
                allPlayers.add(singlePlayer);

            }

            // read and add players in their team
            for (int i = 0; i < numTeam; i++) {
                // read team name
                String teamName = sc.nextLine();
                TeamPlayers team = new TeamPlayers(teamName);
                allTeams.addTeams(team);
                int numTeamPlayers = sc.nextInt();
                eachTeamPlayerNum.add(numTeamPlayers);
                sc.nextLine();
                // read team players name
                for (int j = 0; j < numTeamPlayers; j++) {
                    String name = sc.nextLine();
                    Player singlePlayer = new Player(name);
                    team.addPlayer(singlePlayer);
                }
            }

            sc.close();
            Iterator<TeamPlayers> itTeamPlayer = allTeams.teams.iterator();
            Football footballcheck = new Football();

            // new an arrayList for putting final teams name to print
            ArrayList<String> chosenTeams = new ArrayList<String>();

            while (itTeamPlayer.hasNext()) {
                TeamPlayers teamPlayerExample = itTeamPlayer.next();
                int a = footballcheck.check(teamPlayerExample, allPlayers);
                int b = footballcheck.check2(teamPlayerExample, allTeams, eachTeamPlayerNum);
                if ((a + b) != 0) {
                    chosenTeams.add(teamPlayerExample.getName());
                }

            }
            // sort the final teams list
            Collections.sort(chosenTeams);
            // print final teams name
            Iterator<String> it = chosenTeams.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }

        } catch (FileNotFoundException e) {
            System.out.println("cannot open the File!");
        }
    }

}