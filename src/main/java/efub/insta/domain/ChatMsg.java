package efub.insta.domain;

import efub.insta.dto.ChatMsgDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "chat_message_new")
public class ChatMsg {

    private ChatMsgDto.MsgType type; //메시지 타입

    @Id
    @Column(name = "message_no")
    private Long messageNo;

    @ManyToOne
    @JoinColumn(name = "room_no")
    private ChatRoom chatRoom; //방번호

    @Column(name = "nickname")
    private String sender;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @Column(name = "flag")
    private Boolean flag;

    @Column(name = "message")
    private String content; //메시지 내용
}
