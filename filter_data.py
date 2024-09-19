import json

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

def fitler_teams(season_id):
  with open('teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())

  for team in data:
    # team.update({"season_id":season_id})
    team.pop("players")
    team.pop("season")

  return data

def filter_players():
  with open('teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())
  
  all_players = []

  for teams in data:
    for player in teams['players']:
      all_players.append(extract_player_info(player, teams['id']))
  
  return all_players

def filter_player_statics():
  all_statics = []
  with open('teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())
    for teams in data:
      for player in teams['players']:
        for static in player['statics']:
          all_statics.append(extract_player_statics_info(static))
  
  return all_statics

def filter_game():
  with open('game_logs.json','r') as f:
    data = json.loads(f.read())

  games = []

  while len(data) > 0:
    team_one = data.pop(0)
    team_two = {}
    for game_two in data:
      if game_two["GAME_ID"] == team_one["GAME_ID"]:
        team_two = game_two
        data.remove(game_two)
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

    

def filter_teams_statics():
  with open('game_logs.json','r') as f:
    data = json.loads(f.read())

  statics = []

  for games in data:
    statics.append(extract_team_statics_info(games))
  
  return statics

def filter_player_season(season_id):
  with open('teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())
  
  all_id = []

  for team in data:
    for player in team["players"]:
      id_player = player.get("PLAYER_ID")
      id_season = season_id

      all_id.append({'id': id_season + str(id_player),
                       'player_id': id_player,
                       'season_id': id_season})
  
  return all_id

def filter_team_season(season):
  with open('teams_for_players_for_statics.json','r') as f:
    data = json.loads(f.read())
  
  all_id = []

  for team in data:
    all_id.append({'id': season + str(team.get("id")),
                      'season_id': season,
                      'team_id': team.get("id")})
                       
  
  return all_id

dic_general = {"season": [{"id": "22022", "year": 2022}],
               "team": fitler_teams("22022"),
               "player": filter_players(),
               "game": filter_game(),
               "players_statistics": filter_player_statics(),
               "team_statistics": filter_teams_statics(),
               "player_season": filter_player_season("22022"),
               "season_team": filter_team_season("22022")},

with open('./results/results.json', 'w') as f:
    json.dump(dic_general, f, indent=4)
