package efub.insta.service;

import efub.insta.domain.ChatRoom;
import efub.insta.domain.ChatRoomRepository;
import efub.insta.domain.User;
import efub.insta.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public List<ChatRoomDto> findAllRoomsBySender(User sender){
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsBySender(sender);

        if(chatRooms==null){
            System.out.println("채팅방이 없습니다.");
        }

        List<ChatRoomDto> responseDtoList = new ArrayList<>();
        for(ChatRoom room:chatRooms){
            ChatRoomDto chatRoomDto = ChatRoomDto.create(room.getRoomName());
            chatRoomDto.setUserInfo(room);

            responseDtoList.add(chatRoomDto);
        }

        return responseDtoList;
    }

    public String createChatRoom(ChatRoomDto chatRoomDto){
        ChatRoom room = new ChatRoom(
                chatRoomDto.getRoomNo(),
                chatRoomDto.getRoomName(),
                chatRoomDto.getUser1(),
                chatRoomDto.getUser2()
        );

        chatRoomRepository.save(room);
        return room.getRoomNo();
    }

    public List<ChatRoomDto> findAllRooms(){
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for(ChatRoom room : chatRooms){
            ChatRoomDto chatRoomDto = ChatRoomDto.create(room.getRoomName());
            chatRoomDto.setUserInfo(room);

            chatRoomDtoList.add(chatRoomDto);
        }
        return chatRoomDtoList;
    }

}
