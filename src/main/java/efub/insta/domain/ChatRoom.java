package efub.insta.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "chat_room_new")
public class ChatRoom {
    // chatMsg랑 chatRoom JOIN
    // chatMsg랑 ChatUser JOIN
    @Id
    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "room_name")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @Builder
    public ChatRoom(String roomNo, String roomName, User sender, User receiver){
        this.roomNo = roomNo;
        this.roomName = roomName;
        this.sender = sender;
        this.receiver = receiver;
    }


}
