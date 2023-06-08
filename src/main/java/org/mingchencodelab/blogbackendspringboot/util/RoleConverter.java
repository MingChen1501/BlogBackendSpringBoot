package org.mingchencodelab.blogbackendspringboot.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.mingchencodelab.blogbackendspringboot.model.enumeration.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.valueOf(dbData.toUpperCase());
    }
}
