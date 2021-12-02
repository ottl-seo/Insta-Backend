package efub.insta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.insta.dto.ChatMessageDto;
import efub.insta.service.ChatMessageService;
import efub.insta.web.dto.ChatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageService chatMessageService;
/*
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (ChatMessageDto.MsgType.ENTER.equals(message.getType()))
            message.setContent(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
    }
 */

    @PostMapping("/send")  //int messageNo, String roomNo, String nickname, Boolean flag, String message
    public int postMessage(@RequestParam(value = "ChatMessageDto") String chatMessageDtoString) throws Exception {
        ChatRequestDto requestDto = new ObjectMapper().readValue(chatMessageDtoString, ChatRequestDto.class);
        return chatMessageService.createMessage(requestDto);
    }

    @GetMapping("/chat/messageList")
    public List<ChatMessageDto> findAllMessage(){
        return chatMessageService.getMessageList();
    }
    /*
      @PostMapping("api/posts")
    public String createPost(@RequestParam(value = "image") MultipartFile image,
                             @RequestParam(value = "requestDto") String requestDtoString) throws Exception{
        PostRequestDto requestDto = new ObjectMapper().readValue(requestDtoString, PostRequestDto.class);
        String postNo = postService.createPost(requestDto, image);
        return postNo;
    }
     */
}