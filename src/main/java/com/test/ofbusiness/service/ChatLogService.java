package com.test.ofbusiness.service;

import com.test.ofbusiness.exceptions.ChatLogEntryNotFoundException;
import com.test.ofbusiness.model.ChatLogEntry;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatLogService {

    private final Map<String, Map<Long, ChatLogEntry>> chatData = new HashMap<>();

    public ChatLogEntry createChatlogEntry(String message,
                                           String userId,
                                           Long timestamp) {
        ChatLogEntry chatLogEntry = new ChatLogEntry(UUID.randomUUID().toString(),
                userId,
                message,
                timestamp);

        Map<Long, ChatLogEntry> userChatData = chatData.get(userId);
        if(userChatData == null) {
            userChatData = new TreeMap<>(Comparator.reverseOrder());
        }

        userChatData.put(chatLogEntry.getTimestamp(), chatLogEntry);
        return chatLogEntry;
    }

    public List<ChatLogEntry> getChatlogEntries(String userId,
                                                Optional<Integer> limit,
                                                Optional<String> startMsgID) {

        Map<Long, ChatLogEntry> userChatData = chatData.get(userId);
        int limitVal = limit.orElse(10);

        List<ChatLogEntry> resChatLogsEntries = new ArrayList<>(limitVal);
        List<ChatLogEntry> chatLogEntries = (List)userChatData.values();

        String startMsgIdVal = startMsgID.orElse(chatLogEntries.get(0).getMsgId());
        boolean addToRes = false;

        for(ChatLogEntry chatLogEntry : chatLogEntries) {
            if(chatLogEntry.getMsgId().equals(startMsgIdVal)) {
                addToRes = true;
            }

            if(addToRes && resChatLogsEntries.size() < limitVal) {
                resChatLogsEntries.add(chatLogEntry);
            }
        }

        return resChatLogsEntries;
    }

    public void deleteChatlogEntries(String userId) {
        chatData.remove(userId);
    }


    public void deleteChatlogEntry(String userId, String msgId) {
        Map<Long, ChatLogEntry> userChatData = chatData.get(userId);
        Long entryToDelete = null;

        for(Map.Entry<Long, ChatLogEntry> chatLogMapEntry : userChatData.entrySet()) {
            ChatLogEntry chatLogEntry = chatLogMapEntry.getValue();
            if(chatLogEntry.getMsgId().equals(msgId)) {
                entryToDelete = chatLogMapEntry.getKey();
                break;
            }
        }

        if(entryToDelete == null) {
            throw new ChatLogEntryNotFoundException();
        } else {
            userChatData.remove(entryToDelete);
        }
    }




}
