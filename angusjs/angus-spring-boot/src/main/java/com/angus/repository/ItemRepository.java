package com.angus.repository;

import com.angus.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

  @Query("SELECT i FROM Item i WHERE i.checked=true")
  List<Item> findChecked();
}
