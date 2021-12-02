package efub.insta.domain;

import efub.insta.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    ChatRoom findChatRoomByRoomName(String roomName);
    ChatRoom findChatRoomByRoomNo(String roomNo);
    List<ChatRoom> findChatRoomsBySender(User user1);
}
