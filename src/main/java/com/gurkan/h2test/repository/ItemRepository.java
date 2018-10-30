package com.gurkan.h2test.repository;

import com.gurkan.h2test.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}