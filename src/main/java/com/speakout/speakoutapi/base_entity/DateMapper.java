package com.speakout.speakoutapi.base_entity;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


//TODO set default for all other mappers
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public class DateMapper {
    OffsetDateTime timestampToOffset(Timestamp value){
        if (value != null){
            return OffsetDateTime.of(value.toLocalDateTime().getYear(), value.toLocalDateTime().getMonthValue(),
                    value.toLocalDateTime().getDayOfMonth(), value.toLocalDateTime().getHour(), value.toLocalDateTime().getMinute(),
                    value.toLocalDateTime().getSecond(), value.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }

    }

    Timestamp offsetToTimestamp(OffsetDateTime value){
        if(value != null) {
            return Timestamp.valueOf(value.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
