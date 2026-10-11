CREATE TABLE IF NOT EXISTS t_player_items (
    player_id CHAR(8) NOT NULL COMMENT 'プレイヤーID',
    item_id BINARY(16) NOT NULL COMMENT 'アイテムID',
    quantity INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '所持数',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (player_id, item_id),
    CONSTRAINT fk_t_player_items_t_players_id FOREIGN KEY (player_id) REFERENCES t_players (id),
    CONSTRAINT fk_t_player_items_m_items_id FOREIGN KEY (item_id) REFERENCES m_items (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='プレイヤーアイテム';
