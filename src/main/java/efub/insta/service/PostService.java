package efub.insta.service;

import efub.insta.domain.Post;
import efub.insta.domain.PostRepository;
import efub.insta.domain.UserRepository;
import efub.insta.web.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public String createPost(PostRequestDto requestDto, MultipartFile uploadFile) throws Exception{
        Post post = new Post(
                requestDto.getUser(),
                requestDto.getContent()
        );

        s3Uploader.upload(uploadFile, post);

        postRepository.save(post);
        return post.getPostNo().toString();
    }
}
