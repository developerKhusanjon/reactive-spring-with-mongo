package org.khusanjon.springreactivewithmongo.exception;

public class ItemNotFoundException extends NotFoundException {
    public ItemNotFoundException(String id) {
        super(String.format("Item [%s] is not found", id));
    }
}
