package org.khusanjon.springreactivewithmongo.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.khusanjon.springreactivewithmongo.model.ItemStatus;

@Data
@Accessors(chain = true)
public class ItemUpdateResource {
    @NotBlank
    private String description;
    @NotNull
    private ItemStatus itemStatus;
}
