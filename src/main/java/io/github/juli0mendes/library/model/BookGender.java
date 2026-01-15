package io.github.juli0mendes.library.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookGender {
    FICCION("FICCAO"),
    FANTASY("FANTASIA"),
    MISTERY("MISTÉRIO"),
    ROMANCE("ROMANCE"),
    BIOGRAPHY("BIOGRAFIA"),
    SCIENCE("CIÊNCIA");

    private final String descriptionPt;
}
