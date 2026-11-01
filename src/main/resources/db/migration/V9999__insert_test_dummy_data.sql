-- ユーザー
INSERT INTO t_users(id, player_id) VALUES (X'e7e01f93cd3541adaaa660db9c3ad7b1', 'zzzzzzzz');
-- プレイヤー
INSERT INTO t_players(id, name) VALUES ('zzzzzzzz', 'ダミープレイヤー');

-- 楽曲マスタ
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('xxxxxxxx', 'ダミー楽曲1_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('yyyyyyyy', 'ダミー楽曲2_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type) VALUES ('zzzzzzzz', 'ダミー楽曲3_初期から解放', 1, 2, 3, 4, 0);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_target_id, unlock_quantity) VALUES ('tttttttt', 'ダミー楽曲_チケットで解放', 1, 2, 3, 4, 1, X'ace443fa11b5467e884ef9493f408795', 1);
INSERT INTO m_songs(id, name, easy_level, normal_level, hard_level, expert_level, unlock_type, unlock_target_id) VALUES ('ssssssss', 'ダミー楽曲_ストーリーで解放', 1, 2, 3, 4, 1, X'28260e158063448e97323609a805e742');

-- アイテムマスタ
INSERT INTO m_items(id, name, description) VALUES (X'ace443fa11b5467e884ef9493f408795', '楽曲チケット', '楽曲の交換に使用するアイテム。');
-- アイテム
INSERT INTO t_player_items(player_id, item_id, quantity) VALUES ('zzzzzzzz', X'ace443fa11b5467e884ef9493f408795', 100);

-- ストーリーマスタ
INSERT INTO m_main_stories(id, name, chapter, episode) VALUES (X'28260e158063448e97323609a805e742', 'はじまり', 1, 1);
INSERT INTO m_main_stories(id, name, chapter, episode) VALUES (X'48d6fdf322044002818b36bfb9c8ec45', 'ライブにあこがれて', 1, 2);
INSERT INTO m_main_stories(id, name, chapter, episode) VALUES (X'8875106dd97c450ab2306101df8e7fd5', 'ライブハウスに潜入', 1, 3);
-- ストーリー
INSERT INTO t_player_main_stories(player_id, story_id, read_at) VALUES ('zzzzzzzz', X'28260e158063448e97323609a805e742', '2026-10-10 12:34:56');
INSERT INTO t_player_main_stories(player_id, story_id) VALUES ('zzzzzzzz', X'48d6fdf322044002818b36bfb9c8ec45');
