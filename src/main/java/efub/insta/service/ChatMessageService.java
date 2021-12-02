package efub.insta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.insta.domain.ChatMessage;
import efub.insta.domain.ChatMessageRepository;
import efub.insta.domain.ChatRoom;
import efub.insta.domain.ChatRoomRepository;
import efub.insta.dto.ChatMessageDto;
import efub.insta.web.dto.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public List<ChatMessageDto> getMessageList() {
        return chatMessageRepository.findAll().stream().map(chatMessage -> new ChatMessageDto(chatMessage)).collect(Collectors.toList());
    }
    public ChatMessageDto findMessageById(int messageNo) {
        return (ChatMessageDto) chatMessageRepository.findById(messageNo).stream();
    }

    public int createMessage(ChatRequestDto requestDto) {
        // 시간과 방 정보 설정
        LocalDateTime time = LocalDateTime.now(); //현재 시간으로

        ChatMessage chatMessage = new ChatMessage(
                requestDto.getMessageNo(),
                requestDto.getChatRoom(),
                requestDto.getNickname(),
                time,
                requestDto.getFlag(),
                requestDto.getMessage()
        );
        chatMessageRepository.save(chatMessage);
        return chatMessage.getMessageNo();
    }
}