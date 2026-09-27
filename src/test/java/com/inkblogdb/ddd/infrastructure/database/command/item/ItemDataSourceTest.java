package com.inkblogdb.ddd.infrastructure.database.command.item;

import com.inkblogdb.ddd.domain.model.item.Item;
import com.inkblogdb.ddd.domain.model.item.ItemDescription;
import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.item.ItemName;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemDataSourceTest {

  @InjectMocks
  private ItemDataSource itemDataSource;
  @Mock
  private ItemMapper itemMapper;
  @Mock
  private ItemDomainFactory itemDomainFactory;

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      ItemId itemId = new ItemId(UUID.randomUUID().toString());
      ItemName itemName = new ItemName("テスト名");
      ItemDescription itemDescription = new ItemDescription("テスト説明");
      Item item = new Item(
          itemId,
          itemName,
          itemDescription
      );
      when(itemDomainFactory.createFrom(any())).thenReturn(item);

      // when
      var result = itemDataSource.findById(itemId);

      // then
      assertEquals(itemId, result.itemId());
      assertEquals(itemName, result.itemName());
      assertEquals(itemDescription, result.itemDescription());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      ItemId itemId = new ItemId(UUID.randomUUID());
      when(itemDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> itemDataSource.findById(itemId));
    }
  }

}
