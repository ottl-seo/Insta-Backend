package efub.insta.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.insta.service.PostService;
import efub.insta.web.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("api/posts")
    public String createPost(@RequestParam(value = "image") MultipartFile image,
                             @RequestParam(value = "requestDto") String requestDtoString) throws Exception{
        PostRequestDto requestDto = new ObjectMapper().readValue(requestDtoString, PostRequestDto.class);
        String postNo = postService.createPost(requestDto, image);
        return postNo;
    }

}
