package com.test.ofbusiness.controller;

import com.test.ofbusiness.api.body.ChatLogReq;
import com.test.ofbusiness.model.ChatLogEntry;
import com.test.ofbusiness.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chatlogs")
public class ChatLogController {

    @Autowired
    private ChatLogService chatLogService;

    @PostMapping("/{user}")
    public String createChatLogEntry(@PathVariable(value = "user") String userId,
                                           @RequestBody ChatLogReq chatLogReq) {
        return chatLogService
                .createChatlogEntry(chatLogReq.message, userId, chatLogReq.timestamp)
                .getMsgId();
    }

    @GetMapping("/{user}")
    public List<ChatLogEntry> getChatLogEntries(@PathVariable(value = "user") String userId,
                                                @RequestParam Optional<Integer> limit,
                                                @RequestParam Optional<String> startMsgId) {
        return chatLogService
                .getChatlogEntries(userId, limit, startMsgId);
    }

    @DeleteMapping("/{user}")
    public void deleteChatLogEntries(@PathVariable(value = "user") String userId) {
        chatLogService.deleteChatlogEntries(userId);
    }

    @DeleteMapping("/{user}/{msgId}")
    public void deleteChatLogEntry(@PathVariable(value = "user") String userId,
                                   @PathVariable(value = "msgId") String msgId) {
        chatLogService.deleteChatlogEntry(userId, msgId);
    }


}
