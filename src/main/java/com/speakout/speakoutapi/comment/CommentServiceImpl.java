package com.speakout.speakoutapi.comment;


import com.speakout.speakoutapi.customer.Customer;
import com.speakout.speakoutapi.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CustomerService customerService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, CustomerService customerService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.customerService = customerService;
    }

    @Override
    public Comment save(CommentDto commentDto) {
        Comment commentEntity = commentMapper.commentDtoToComment(commentDto);
        Customer author = customerService.findCustomerByUsername(commentDto.getAuthorUsername());
        commentEntity.setAuthor(author);
        return  commentRepository.save(commentEntity);
    }

    @Override
    public CommentDto likeComment(Long commentId) {
        Optional<Comment> commentById = commentRepository.findById(commentId);
        Comment comment = commentById.orElseThrow(CommentNotFoundException::new);
        Customer authenticatedCustomer = customerService.getAuthenticatedCustomer();
        comment.getLikes().add(authenticatedCustomer);
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

}
