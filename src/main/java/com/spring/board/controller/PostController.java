package com.spring.board.controller;

import com.spring.board.domain.Post;
import com.spring.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/new")
    public String form(Model model) {
        model.addAttribute("post", new PostCreateForm());
        return "postCreateForm";
    }

    @PostMapping("/posts/new")
    public String create(PostCreateForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postService.findOne(id);
        PostUpdateForm postUpdateForm = new PostUpdateForm();
        postUpdateForm.setId(post.getId());
        postUpdateForm.setTitle(post.getTitle());
        postUpdateForm.setContent(post.getContent());
        model.addAttribute("post", postUpdateForm);
        return "post";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Post post = postService.findOne(id);
        PostUpdateForm postUpdateForm = new PostUpdateForm();
        postUpdateForm.setId(post.getId());
        postUpdateForm.setTitle(post.getTitle());
        postUpdateForm.setContent(post.getContent());
        model.addAttribute("post", postUpdateForm);
        return "postUpdateForm";
    }

    @PostMapping("/posts/update/{id}")
    public String edit(PostUpdateForm postUpdateForm) {
        Post post = new Post();
        post.setId(postUpdateForm.getId());
        post.setTitle(postUpdateForm.getTitle());
        post.setContent(postUpdateForm.getContent());
        postService.update(post);
        return "redirect:/";
    }

    @GetMapping("/posts/delete/{id}")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "postList";
    }
}
