package org.khusanjon.springreactivewithmongo.service;

import lombok.RequiredArgsConstructor;
import org.khusanjon.springreactivewithmongo.api.ItemPatchResource;
import org.khusanjon.springreactivewithmongo.api.ItemResource;
import org.khusanjon.springreactivewithmongo.api.ItemUpdateResource;
import org.khusanjon.springreactivewithmongo.api.NewItemResource;
import org.khusanjon.springreactivewithmongo.exception.ItemNotFoundException;
import org.khusanjon.springreactivewithmongo.mapper.ItemMapper;
import org.khusanjon.springreactivewithmongo.model.Item;
import org.khusanjon.springreactivewithmongo.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ItemService {

    private static final Sort DEFAULT_SORT = Sort.by(Sort.Order.by("lastModifiedDate"));

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Mono<ItemResource> create(final NewItemResource item) {
        return itemRepository.save(itemMapper.toModel(item))
                .map(itemMapper::toResource);
    }

    public Flux<ItemResource> findAll() {
        return itemRepository.findAll(DEFAULT_SORT)
                .map(itemMapper::toResource);
    }

    public Mono<ItemResource> findById(final String id) {
        return findItemById(id)
                .map(itemMapper::toResource);
    }

    public Mono<ItemResource> update(final String id, final ItemUpdateResource itemUpdateResource) {
        return findItemById(id)
                .flatMap(item -> {
                    itemMapper.update(itemUpdateResource, item);
                    return itemRepository.save(item);
                })
                .map(itemMapper::toResource);
    }

    @SuppressWarnings({"OptionalAssignedToNull", "OptionalGetWithoutIsPresent"})
    public Mono<ItemResource> patch(final String id, final ItemPatchResource itemPatchResource) {
        return findItemById(id)
                .flatMap(item -> {
                    if (itemPatchResource.getDescription() != null)
                        item.setDescription(itemPatchResource.getDescription().get());

                    if (itemPatchResource.getItemStatus() != null)
                        item.setItemStatus(itemPatchResource.getItemStatus().get());

                    return itemRepository.save(item);
                })
                .map(itemMapper::toResource);
    }

    public Mono<Void> deleteById(final String id) {
        return findItemById(id)
                .flatMap(itemRepository::delete);
    }

    /**
     * Find an item
     *
     * @param id Identifier of the item we are looking for
     * @return The item mono
     * @throws ItemNotFoundException if the item with the provided identifier does not exist
     */
    private Mono<Item> findItemById(final String id) {
        return itemRepository.findById(id)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(id)));
    }
}
