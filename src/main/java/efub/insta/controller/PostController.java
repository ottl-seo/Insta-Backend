package efub.insta.controller;

import efub.insta.dto.LikeDto;
import efub.insta.dto.PostDto;
import efub.insta.dto.UserDto;
import efub.insta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PostController {

    @Autowired
    PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/postList")
    public List<PostDto> getPostList(){
        return postService.getPostList();
    }

    @GetMapping("{postNo}/likeList")
    public List<UserDto> getLikeUser(@PathVariable("postNo") Long postNo){
        return postService.getLikeUserList(postNo);
    }

    @GetMapping("{postNo}/likeListCount")
    public Long getLikeCount(@PathVariable("postNo") Long postNo){
        return postService.getLikeUserCount(postNo);
    }

    @PatchMapping("/{postNo}/{userNo}/like")
    public ResponseEntity<String> likePost(@PathVariable("postNo") Long postNo, @PathVariable("userNo") Long userNo){
        postService.updateLike(postNo, userNo);
        return ResponseEntity.ok("ok");
    }

    /*테스트용 좋아요 테이블 전체 출력
    @GetMapping("/likeList")
    public List<LikeDto> getLikeList(){
        return postService.getLikeUserAllList();
    }*/
}
