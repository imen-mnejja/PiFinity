package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.Advertising;
import com.example.pifinity.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> findAllComments();
    Comment addComment(Comment comment);
    Comment updateComment (Long commentid ,Comment comment);
    Comment findByIdComment (Long commentid);
    void deleteComment(Long commentid);

}
