package efub.insta.controller;

import efub.insta.domain.ChatRoom;
import efub.insta.domain.ChatRoomRepository;
import efub.insta.dto.ChatRoomDto;
import efub.insta.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){
        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        mv.addObject("list", chatRoomService.findAllRooms());
        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        log.info("# Create Chat Room , name : "+name);
        ChatRoomDto chatRoomDto = chatRoomService.findRoomByName(name);
        rttr.addFlashAttribute("roomName", chatRoomService.createChatRoom(chatRoomDto));

        return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomNo, Model model){
        log.info("# get Chat Room, roomNo : "+roomNo);
        model.addAttribute("room", chatRoomService.findRoomByNo(roomNo));
    }
}
