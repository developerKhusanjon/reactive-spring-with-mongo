package org.khusanjon.springreactivewithmongo.api;

import lombok.Data;
import lombok.experimental.Accessors;
import org.khusanjon.springreactivewithmongo.model.ItemStatus;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ItemResource {

    private String id;
    private Long version;

    private String description;
    private ItemStatus status;

    private Instant createdDate;
    private Instant lastModifiedDate;

}