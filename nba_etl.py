from nba_api.stats.static import teams
from nba_api.stats.endpoints import TeamYearByYearStats
from nba_api.stats.endpoints import commonteamroster
from nba_api.stats.endpoints import LeagueGameLog
from nba_api.stats.endpoints import PlayerGameLog
from nba_api.stats.endpoints import leaguegamefinder
import json
import os

# Configurar a temporada desejada
season = '2022-23'

def get_teams_by_season(season):
    all_teams_by_season = []
    nba_teams = teams.get_teams()
    for team in nba_teams:
        team_id = team['id']
        team_stats = TeamYearByYearStats(team_id=team_id)
        value = team_stats.get_data_frames()[0]
        season_stats = value[value['YEAR'] == season]
        team["season"] = season
        if not season_stats.empty:
            all_teams_by_season.append(team)
        os.system("sleep 1")
    
    return all_teams_by_season

def get_players_information_by_team_season(teams):
    for team in teams:
        players = commonteamroster.CommonTeamRoster(team_id=team['id'], season=team["season"])
        team.update({"players": players.get_data_frames()[0].to_dict(orient='records')})
        os.system("sleep 1")
    
    return teams

def get_game_log(season, team_or_player):
    games = LeagueGameLog(season=season, player_or_team_abbreviation=team_or_player)
    return games.get_data_frames()[0].to_dict(orient='records')

def get_players_statics_by_team_season(dict, season): 
    for team in dict:
        team_id = team["id"]
        for player in team["players"]:
            games_log = PlayerGameLog(player_id=player["PLAYER_ID"], season=season).get_data_frames()[0].to_dict(orient='records')
            os.system("sleep 1")
            for game in games_log:
                game['team_id'] = team_id
            player.update({"statics":games_log})

    return dict


    
teams = get_teams_by_season(season)
players = get_players_information_by_team_season(teams)
complete_data = get_players_statics_by_team_season(players,season)
games_statics = get_game_log(season, "T")

with open('./temp/game_logs.json', 'w') as f:
    json.dump(games_statics, f, indent=4)

with open('./temp/teams_for_players_for_statics.json', 'w') as f:
    json.dump(complete_data, f, indent=4)
print("Dados salvos no arquivo seasons.json.")