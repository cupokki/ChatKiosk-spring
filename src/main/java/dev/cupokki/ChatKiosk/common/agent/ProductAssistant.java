package dev.cupokki.ChatKiosk.common.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ProductAssistant {

    @SystemMessage("""
            당신은 상품 정보 전문가입니다. 사용자가 상품에 대해 질문하면,\s
            주어진 도구(Tool)를 사용해서 상품 정보를 조회해야 합니다.\s
            조회된 정보를 바탕으로 사용자에게 친절하고 정확하게 답변을 생성하세요.
            하나의 도구(Tool)을 반드시 사용하라
            """)
    @UserMessage("{userMessage}")
    String chat(String userMessage);
}
