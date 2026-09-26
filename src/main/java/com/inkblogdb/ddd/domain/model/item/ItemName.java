package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.type.Text;

public class ItemName extends Text {

  public ItemName(String value) {
    super(value);
  }

  @Override
  protected void validate(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("アイテム名が空");
    }
    if (50 < value.length()) {
      throw new DomainException("アイテム名が50桁を超えている " + value);
    }
  }

}
