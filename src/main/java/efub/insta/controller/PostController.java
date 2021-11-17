package efub.insta.controller;

import efub.insta.dto.PostDto;
import efub.insta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/list")
    public List<PostDto> getPostList(){
        return postService.getPostList();
    }
}
