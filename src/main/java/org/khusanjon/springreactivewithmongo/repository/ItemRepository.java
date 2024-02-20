package org.khusanjon.springreactivewithmongo.repository;

import org.khusanjon.springreactivewithmongo.model.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemRepository extends ReactiveMongoRepository<Item, String> {
}
