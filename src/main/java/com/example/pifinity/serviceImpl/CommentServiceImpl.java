package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Advertising;
import com.example.pifinity.entity.Comment;
import com.example.pifinity.repository.CommentRepo;
import com.example.pifinity.serviceInterface.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements ICommentService {
    CommentRepo commentRepo;

    @Override
    public List<Comment> findAllComments() {
        return commentRepo.findAll();
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setDate(LocalDate.now());
        return commentRepo.save(comment);

    }

    @Override
    public Comment updateComment(Long commentid, Comment updatedComment) {

        Comment existingComment = findByIdComment(commentid);
        existingComment.setContenu(updatedComment.getContenu());
        existingComment.setDate(LocalDate.now());
        return commentRepo.save(updatedComment);
    }
    @Override
    public Comment findByIdComment(Long commentid) {
        return commentRepo.findById(commentid)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentid));
    }

    @Override
    public void deleteComment(Long commentid) {
        commentRepo.deleteById(commentid);
    }
}
