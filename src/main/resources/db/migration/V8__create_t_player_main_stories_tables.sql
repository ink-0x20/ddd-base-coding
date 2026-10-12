CREATE TABLE IF NOT EXISTS t_player_main_stories (
    player_id CHAR(8) NOT NULL COMMENT 'プレイヤーID',
    story_id BINARY(16) NOT NULL COMMENT 'ストーリーID',
    read_at DATETIME NULL DEFAULT NULL COMMENT '閲覧日時',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (player_id, story_id),
    CONSTRAINT fk_t_player_main_stories_t_players_id FOREIGN KEY (player_id) REFERENCES t_players (id),
    CONSTRAINT fk_t_player_main_stories_m_main_stories_id FOREIGN KEY (story_id) REFERENCES m_main_stories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='プレイヤーメインストーリー';
