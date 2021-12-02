package efub.insta.domain;

import efub.insta.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    ChatRoom findChatRoomByRoomName(String roomName);
    ChatRoom findChatRoomByRoomNo(String roomNo);
    List<ChatRoom> findByRoomNo(String no);

    @Query(value = "select c from ChatRoom c where c.sender.userId like :userId")
    List<ChatRoom> findBySenderUserId(@Param("userId") String userId);

    //List<ChatRoom> findByUserNo(Long userNo);
    //List<ChatRoom> findBySenderUserId(String sender);
}
