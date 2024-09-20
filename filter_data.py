import json
import copy

with open('./temp/teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())

with open('./temp/game_logs.json','r') as f:
    games_log = json.loads(f.read())

def extract_player_info(player, team_id):
    return {
        "name": player.get("PLAYER"),
        "nickname": player.get("NICKNAME"),
        "shirt_number": player.get("NUM"),
        "position": player.get("POSITION"),
        "height": player.get("HEIGHT"),
        "weight": player.get("WEIGHT"),
        "birth_date": player.get("BIRTH_DATE"),
        "age": player.get("AGE"),
        "id": player.get("PLAYER_ID"),
        "team_id": team_id
    }

def extract_player_statics_info(statics):
    return {
        "id": str(statics.get("Player_ID")) + statics.get("Game_ID"),
        "points": statics.get("PTS"),
        "rebounds": statics.get("REB"),
        "blocks": statics.get("BLK"),
        "assistances": statics.get("AST"),
        "steals": statics.get("STL"),
        "three_point_shots": statics.get("FG3M"),
        "perimeter_baskets": statics.get("FGA"),
        "free_throws": statics.get("FTA"),
        "player_id": statics.get("Player_ID"),
        "game_id": statics.get("Game_ID")
    }

def extract_team_statics_info(statics):
    return {
        "id": str(statics.get("TEAM_ID")) + statics.get("GAME_ID"),
        "points": statics.get("PTS"),
        "rebounds": statics.get("REB"),
        "blocks": statics.get("BLK"),
        "assistances": statics.get("AST"),
        "steals": statics.get("STL"),
        "three_point_shots": statics.get("FG3M"),
        "perimeter_baskets": statics.get("FGA"),
        "free_throws": statics.get("FTA"),
        "team_id": statics.get("TEAM_ID"),
        "game_id": statics.get("Game_ID"),
        "wl": statics.get("WL")
    }

def fitler_teams(data):
  teams = copy.deepcopy(data)

  for team in teams:
    team.pop("players")
    team.pop("season")

  return teams

def filter_players(data):
  players = copy.deepcopy(data)
  all_players = []

  for teams in players:
    for player in teams['players']:
      all_players.append(extract_player_info(player, teams['id']))
  
  return all_players

def filter_player_statics(data):
  players_statics = copy.deepcopy(data)
  all_statics = []
  for teams in players_statics:
    for player in teams['players']:
      for static in player['statics']:
        all_statics.append(extract_player_statics_info(static))
  
  return all_statics

def filter_game(games_log):
  games_logs = copy.deepcopy(games_log)
  games = []

  while len(games_logs) > 0:
    team_one = games_logs.pop(0)
    team_two = {}
    for game_two in games_logs:
      if game_two["GAME_ID"] == team_one["GAME_ID"]:
        team_two = game_two
        games_logs.remove(game_two)
        break
    position_one = 'home'
    position_two = 'away'
    vacabulary = team_one['MATCHUP']
    if '@' in vacabulary:
      position_one = 'away'
      position_two = 'home'
      vacabulary = team_two['MATCHUP']
    
    wl = team_two["TEAM_ID"]
    if 'W' in team_one:
      wl = team_one["TEAM_ID"]
      
    games.append({'id': team_one["GAME_ID"],
                  'matchup': vacabulary,
                  'season_id': team_one["SEASON_ID"],
                  position_one+'_team_id': team_one["TEAM_ID"],
                  position_two+'_team_id': team_two["TEAM_ID"],
                  'winner_team_id': wl
                  })
  
  return games

    

def filter_teams_statics(games_log):
  games_logs = copy.deepcopy(games_log)
  statics = []

  for games in games_logs:
    statics.append(extract_team_statics_info(games))
  
  return statics

def filter_player_season(data,season_id):
  players = copy.deepcopy(data)
  all_id = []

  for team in players:
    for player in team["players"]:
      id_player = player.get("PLAYER_ID")
      id_season = season_id

      all_id.append({'id': id_season + str(id_player),
                       'player_id': id_player,
                       'season_id': id_season})
  
  return all_id

def filter_team_season(data,season):
  teams = copy.deepcopy(data)
  all_id = []

  for team in teams:
    all_id.append({'id': season + str(team.get("id")),
                      'season_id': season,
                      'team_id': team.get("id")})
                       
  
  return all_id

dic_general = {"season": [{"id": games_log[0]["SEASON_ID"], "year": data[0]["season"]}],
               "team": fitler_teams(data),
               "player": filter_players(data),
               "game": filter_game(games_log),
               "players_statistics": filter_player_statics(data),
               "team_statistics": filter_teams_statics(games_log),
               "player_season": filter_player_season(data,games_log[0]["SEASON_ID"]),
               "season_team": filter_team_season(data,games_log[0]["SEASON_ID"])},

with open('./results/results'+data[0]["season"]+'.json', 'w') as f:
    json.dump(dic_general, f, indent=4)
