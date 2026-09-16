-- ユーザー
INSERT INTO t_users(id, player_id) VALUES (X'e7e01f93cd3541adaaa660db9c3ad7b1', 'zzzzzzzz');
-- プレイヤー
INSERT INTO t_players(id, name) VALUES ('zzzzzzzz', 'ダミープレイヤー');

-- 楽曲マスタ
INSERT INTO m_songs(id, name, level) VALUES ('xxxxxxxx', 'ダミー楽曲1', 1);
INSERT INTO m_songs(id, name, level) VALUES ('yyyyyyyy', 'ダミー楽曲2', 2);
INSERT INTO m_songs(id, name, level) VALUES ('zzzzzzzz', 'ダミー楽曲3', 3);
-- プレイヤー楽曲
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxxx', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyyy', 2);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'zzzzzzzz', 0);
