package com.example.pifinity.controller;

import com.example.pifinity.entity.Comment;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.serviceInterface.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @GetMapping()
    public List<Comment> findAllComments(){
        return commentService.findAllComments();
    }

    @GetMapping("/{commentid}")
    public Comment findByIdComment(@PathVariable("commentid") Long commentid){
        return commentService.findByIdComment(commentid);
    }

    @PostMapping()
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @PutMapping("/{commentid}")
    public Comment updateComment(@PathVariable Long commentid , @RequestBody Comment comment) {
        return commentService.updateComment(commentid , comment);
    }

    @DeleteMapping("/{commentid}")
    public void deleteComment(@PathVariable("commentid") Long commentid) {
        commentService.deleteComment(commentid);
    }

}
