package com.speakout.speakoutapi.comment;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {
    @Mapping(source = "author.id", target = "authorId")
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);

    default OffsetDateTime toOffset(Timestamp value){
        if (value != null){
            return OffsetDateTime.of(value.toLocalDateTime().getYear(), value.toLocalDateTime().getMonthValue(),
                    value.toLocalDateTime().getDayOfMonth(), value.toLocalDateTime().getHour(), value.toLocalDateTime().getMinute(),
                    value.toLocalDateTime().getSecond(), value.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }

    }

    default Timestamp toTimestamp(OffsetDateTime value){
        if(value != null) {
            return Timestamp.valueOf(value.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
