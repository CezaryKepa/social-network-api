package com.speakout.speakoutapi.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment save(CommentDto commentDto) {
        return mapAndSaveComment(commentDto);
    }

    private Comment mapAndSaveComment(CommentDto comment) {
        Comment commentEntity = commentMapper.commentDtoToComment(comment);
        return  commentRepository.save(commentEntity);
    }
}
