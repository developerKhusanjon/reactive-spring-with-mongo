package org.khusanjon.springreactivewithmongo.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.khusanjon.springreactivewithmongo.model.ItemStatus;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class ItemPatchResource {
    Optional<@NotBlank String> description;
    Optional<@NotNull ItemStatus> itemStatus;
}
