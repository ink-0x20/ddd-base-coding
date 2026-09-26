package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.type.Text;

public class ItemDescription extends Text {

  public ItemDescription(String value) {
    super(value);
  }

  @Override
  protected void validate(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("アイテム説明が空");
    }
    if (150 < value.length()) {
      throw new DomainException("アイテム説明が150桁を超えている " + value);
    }
  }

}
