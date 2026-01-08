package dev.cupokki.ChatKiosk.common.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ProductAssistant {

    @SystemMessage("반드시 한국어로 응답합니다.")
    String chat(String userMessage);
}
