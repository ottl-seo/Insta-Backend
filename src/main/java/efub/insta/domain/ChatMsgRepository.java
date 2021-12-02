package efub.insta.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMsgRepository extends JpaRepository<ChatMsg, String> {
}
