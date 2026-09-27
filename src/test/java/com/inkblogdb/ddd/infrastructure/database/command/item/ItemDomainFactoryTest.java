package com.inkblogdb.ddd.infrastructure.database.command.item;

import com.inkblogdb.ddd.domain.model.item.ItemDescription;
import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.item.ItemName;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemDomainFactoryTest {

  @InjectMocks
  private ItemDomainFactory itemDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      ItemId itemId = new ItemId(UUID.randomUUID().toString());
      ItemName itemName = new ItemName("テスト名");
      ItemDescription itemDescription = new ItemDescription("テスト説明");
      ItemRecordEntity entity = new ItemRecordEntity(
          itemId.value(),
          itemName.value(),
          itemDescription.value()
      );

      // when
      var result = itemDomainFactory.createFrom(entity);

      // then
      assertNotNull(result);
      assertEquals(itemId.value(), result.itemId().value());
      assertEquals(itemName.value(), result.itemName().value());
      assertEquals(itemDescription.value(), result.itemDescription().value());
    }

    @Test
    void nullの場合エラーになること() {
      // when then
      assertThrows(InfrastructureException.class, () -> itemDomainFactory.createFrom(null));
    }
  }

}
