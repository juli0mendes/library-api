package io.github.juli0mendes.library.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookGenderConverter implements AttributeConverter<BookGender, String> {

    @Override
    public String convertToDatabaseColumn(BookGender bookGender) {
        return bookGender != null ? bookGender.getDescriptionPt() : null;
    }

    @Override
    public BookGender convertToEntityAttribute(String descriptionPt) {
        for (BookGender gender : BookGender.values()) {
            if (gender.getDescriptionPt().equals(descriptionPt)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + descriptionPt);
    }
}
