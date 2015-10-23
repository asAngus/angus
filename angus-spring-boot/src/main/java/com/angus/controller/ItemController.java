package com.angus.controller;

import com.angus.model.Item;
import com.angus.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
  @Autowired
  private ItemRepository repo;

  @RequestMapping(method = RequestMethod.GET)
  public List<Item> findItems() {
    return repo.findAll();
  }

  @RequestMapping(method = RequestMethod.POST)
  public Item addItem(/**@RequestBody Item item*/) {
    //item.setId(null);
    Item i = new Item();
    i.setChecked(true);
    i.setDescription("test");
    return repo.saveAndFlush(i);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Item updateItem(@RequestBody Item updatedItem, @PathVariable Integer id) {
    Item item = repo.getOne(id);
    item.setChecked(updatedItem.isChecked());
    item.setDescription(updatedItem.getDescription());
    return repo.saveAndFlush(item);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteItem(@PathVariable Integer id) {
    repo.delete(id);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = { EmptyResultDataAccessException.class, EntityNotFoundException.class })
  public void handleNotFound() { }
}
