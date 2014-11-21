INSERT INTO tournament(id, name) VALUES(1, 'tournament_2014');

INSERT INTO player(id, name, nickname, race) VALUES(1, 'name_1', 'nick_1', 'Zerg');
INSERT INTO player(id, name, nickname, race) VALUES(2, 'name_2', 'nick_2', 'Protoss');
INSERT INTO player(id, name, nickname, race) VALUES(3, 'name_3', 'nick_3', 'Random');
INSERT INTO player(id, name, nickname, race) VALUES(4, 'name_4', 'nick_4', 'Terran');

INSERT INTO round (id, description, tournament_id) VALUES (1, 'Finals', 1);
INSERT INTO round (id, description, tournament_id) VALUES (2, 'Semifinals', 1);

INSERT INTO match(id, round_id, player_1_id, player_2_id) VALUES (1, 1, 1, 2);
INSERT INTO match(id, round_id, player_1_id, player_2_id) VALUES (2, 1, 3, 4);

INSERT INTO game(id, match_id, map, date, winner_id) VALUES(1, 1, 'OVERGROWTH', DATE '2014-01-01' ,1);
INSERT INTO game(id, match_id, map, date, winner_id) VALUES(2, 1, 'CATALLENA', DATE '2014-01-02' ,2);