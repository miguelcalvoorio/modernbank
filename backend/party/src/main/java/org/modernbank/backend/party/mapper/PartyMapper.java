package org.modernbank.backend.party.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.modernbank.backend.party.client.PartyAddressRest;
import org.modernbank.backend.party.client.PartyRest;
import org.modernbank.backend.party.model.Party;
import org.modernbank.backend.party.model.PartyAddress;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PartyMapper {
    @Mapping(target = "id", ignore = true)
    Party toEntity(PartyRest partyDetailedRest);

    @Mapping(target = "identifications", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    PartyRest toRestData(Party party);

    PartyRest toDetailedRestData(Party party);

    // Mapping nested objects
    PartyAddressRest toAddressRestData(PartyAddress address);

    // Mapping utilities
    default String toString(ObjectId objectId) {
        return objectId.toString();
    }

    default LocalDate emptyToNull(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
    }

    default LocalDateTime emptyLocalDateTimeToNull(String s) {
        if (s == null || s.isEmpty()) return null;
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
    }

    default String testLocalDate(LocalDate localDate) {
        if (localDate == null) return null;
        return localDate.format(DateTimeFormatter.ISO_DATE);
    }

    default String testLocalDateTime(LocalDate localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
