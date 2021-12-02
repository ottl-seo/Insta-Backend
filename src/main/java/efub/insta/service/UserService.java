package efub.insta.service;

import efub.insta.domain.User;
import efub.insta.domain.UserRepository;
import efub.insta.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
//
//    @Transactional
//    public UserDto findUserByUserId(String userId){
//        User entity = userRepository.findUserByUserId(userId);
//        UserDto userDto = new UserDto(entity);
//        return userDto;
//    }

}
