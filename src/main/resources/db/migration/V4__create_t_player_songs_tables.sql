CREATE TABLE IF NOT EXISTS t_player_songs (
    player_id CHAR(8) NOT NULL COMMENT 'プレイヤーID',
    song_id CHAR(8) NOT NULL COMMENT '楽曲ID',
    unlock_status TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '解放状態',
    unlocked_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '解放日時',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (player_id, song_id),
    CONSTRAINT fk_t_player_songs_t_players_player_id FOREIGN KEY (player_id) REFERENCES t_players (id),
    CONSTRAINT fk_t_player_songs_m_songs_song_id FOREIGN KEY (song_id) REFERENCES m_songs (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='プレイヤー楽曲';
