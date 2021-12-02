package efub.insta.domain;
import efub.insta.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>{
    ChatRoom findChatRoomByRoomName(String name);
    ChatRoom findChatRoomByRoomNo(String No);
}
