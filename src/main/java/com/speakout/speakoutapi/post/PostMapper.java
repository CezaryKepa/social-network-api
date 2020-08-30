package com.speakout.speakoutapi.post;

import com.speakout.speakoutapi.comment.CommentMapper;
import com.speakout.speakoutapi.customer.CustomerMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@MapperConfig(uses = CommentMapper.class)
public interface PostMapper {

    @Mapping(source = "author.id", target = "authorId")
    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto postDto);

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
