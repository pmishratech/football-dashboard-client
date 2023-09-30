package com.scoreboard;

import com.scoreboard.football.FootballMatch;
import com.scoreboard.football.modal.Player;
import com.scoreboard.football.modal.Team;
import com.scoreboard.football.service.MatchManager;

import java.util.List;
import java.util.Map;

public class FootballMatchClient {
    public static void main(String[] args) {
        // Create a new MatchManager instance
        MatchManager matchManager = new MatchManager();

        // Create teams and a football match
        Team teamA = createTeamWithPlayers("TeamA", "PlayerA", 11);
        Team teamB = createTeamWithPlayers("TeamB", "PlayerB", 11);
        Player referee1 = new Player("Robin Referee", 777);
        FootballMatch match1 = createFootballMatch(teamA, teamB, "Match Football1", referee1);

        // Create teams and another football match
        Team teamC = createTeamWithPlayers("TeamC", "PlayerC", 11);
        Team teamD = createTeamWithPlayers("TeamD", "PlayerD", 11);
        Player referee2 = new Player("New Referee", 777);
        FootballMatch match2 = createFootballMatch(teamC, teamD, "Match Football2", referee2);

        // Add the football matches to the MatchManager
        matchManager.addMatch("Match1", match1);
        matchManager.addMatch("Match2", match2);

        // Perform actions on the match, update scores, etc.
        matchManager.scoreGoal("Match1", "PlayerA1", teamA);
        matchManager.scoreGoal("Match1", "PlayerB1", teamB);
        matchManager.scoreGoal("Match1", "PlayerA1", teamA);
        matchManager.scoreGoal("Match1", "PlayerB2", teamB);

        // Get match details
        FootballMatch matchDetails = matchManager.getMatchDetails("Match1");
        System.out.println("Match Title: " + matchDetails.getMatchTitle());
        System.out.println("Match Status: " + matchDetails.getMatchStatus());
        System.out.println("Match Start Time: " + matchManager.getMatchStartTime("Match1"));

        // Get player rankings for the match
        List<Player> playerRankings = matchManager.getPlayersRanking("Match1");
        System.out.println("Player Rankings:");
        for (Player player : playerRankings) {
            System.out.println(player.getName() + " - Score: " + player.getScore());
        }

        // Get details of a specific player
        Player playerDetails = matchManager.getPlayerDetails("PlayerA1");
        System.out.println("Player Details - Name: " + playerDetails.getName() + ", Score: " + playerDetails.getScore());

        // Get a list of all players in all matches
        List<String> allPlayers = matchManager.getAllPlayers();
        System.out.println("All Players:");
        for (String playerName : allPlayers) {
            System.out.println(playerName);
        }

        // Remove a match
        matchManager.removeMatch("Match2");

        // Get all matches
        Map<String, FootballMatch> allMatches = matchManager.getAllMatches();
        System.out.println("All Matches:");
        for (Map.Entry<String, FootballMatch> entry : allMatches.entrySet()) {
            System.out.println("Match ID: " + entry.getKey());
            System.out.println("Match Title: " + entry.getValue().getMatchTitle());
        }
    }

    private static Team createTeamWithPlayers(String teamName, String playerNamePrefix, int numberOfPlayers) {
        Team team = new Team(teamName);
        for (int i = 1; i <= numberOfPlayers; i++) {
            Player player = new Player(playerNamePrefix + i, i);
            team.addPlayer(player);
        }
        return team;
    }

    private static FootballMatch createFootballMatch(Team teamA, Team teamB, String matchTitle, Player referee) {
        return new FootballMatch(teamA, teamB, matchTitle, referee);
    }
}
