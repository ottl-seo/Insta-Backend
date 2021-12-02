package efub.insta.domain;

import efub.insta.dto.ChatMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "chat_message_new")
public class ChatMessage {

    @Id
    @Column(name = "message_no")
    private int messageNo;

    @ManyToOne
    @JoinColumn(name = "room_no")
    private ChatRoom chatRoom; //방번호

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @Column(name = "flag")
    private Boolean flag; // 1이면 sender , 0이면 receiver

    @Column(name = "message")
    private String message; //메시지 내용

    @Builder
    public ChatMessage(int messageNo, ChatRoom chatRoom, String nickname, LocalDateTime sendTime, Boolean flag, String message){//
        this.messageNo = messageNo;
        this.chatRoom = chatRoom;
        this.nickname = nickname;
        this.sendTime = sendTime;
        this.flag = flag;
        this.message = message;
    }
}
