package org.khusanjon.springreactivewithmongo.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.khusanjon.springreactivewithmongo.api.ItemPatchResource;
import org.khusanjon.springreactivewithmongo.api.ItemResource;
import org.khusanjon.springreactivewithmongo.api.ItemUpdateResource;
import org.khusanjon.springreactivewithmongo.api.NewItemResource;
import org.khusanjon.springreactivewithmongo.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @ApiOperation("Get the list of items")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ItemResource> getAllItems() {

        return itemService.findAll();
    }

    @ApiOperation("Create a new item")
    @PostMapping
    public Mono<ItemResource> create(@Valid @RequestBody final NewItemResource item) {

        return itemService.create(item);
    }

    @ApiOperation("Update an existing item")
    @PutMapping("/{id}")
    public Mono<ItemResource> update(@PathVariable @NotNull final String id,
                                     @Valid @RequestBody ItemUpdateResource itemUpdateResource) {

        return itemService.update(id, itemUpdateResource);
    }

    @ApiOperation("Patch an existing item following the patch merge RCF (https://tools.ietf.org/html/rfc7386)")
    @PatchMapping(value = "/{id}")
    public Mono<ItemResource> update(@PathVariable @NotNull final String id,
                                     @Valid @RequestBody ItemPatchResource itemPatchResource) {

        return itemService.patch(id,itemPatchResource);
    }

    @ApiOperation("Find an item by its id")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ItemResource> findById(@PathVariable String id) {

        return itemService.findById(id);
    }

    @ApiOperation("Delete an item")
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final String id) {

        return itemService.deleteById(id);
    }
}
