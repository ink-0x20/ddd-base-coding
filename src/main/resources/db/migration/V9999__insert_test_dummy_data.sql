-- ユーザー
INSERT INTO t_users(id, player_id) VALUES (X'e7e01f93cd3541adaaa660db9c3ad7b1', 'zzzzzzzz');
-- プレイヤー
INSERT INTO t_players(id, name) VALUES ('zzzzzzzz', 'ダミープレイヤー');

-- 楽曲マスタ
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx2', 'ダミー楽曲x2_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx3', 'ダミー楽曲x3_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx4', 'ダミー楽曲x4_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx5', 'ダミー楽曲x5_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx6', 'ダミー楽曲x6_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx7', 'ダミー楽曲x7_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx8', 'ダミー楽曲x8_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxx9', 'ダミー楽曲x9_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy2', 'ダミー楽曲y2_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy3', 'ダミー楽曲y3_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy4', 'ダミー楽曲y4_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy5', 'ダミー楽曲y5_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy6', 'ダミー楽曲y6_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy7', 'ダミー楽曲y7_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy8', 'ダミー楽曲y8_チケットで解放', 1, 2, 3, 4, 1, 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_quantity) VALUES ('yyyyyyy9', 'ダミー楽曲y9_チケットで解放', 1, 2, 3, 4, 1, 1);
-- プレイヤー楽曲
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx2', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx3', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx4', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx5', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx6', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx7', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx8', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'xxxxxxx9', 1);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy2', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy3', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy4', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy5', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy6', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy7', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy8', 0);
INSERT INTO t_player_songs(player_id, song_id, unlock_status) VALUES ('zzzzzzzz', 'yyyyyyy9', 0);
