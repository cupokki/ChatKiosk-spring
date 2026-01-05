package dev.cupokki.ChatKiosk.controller;

import dev.cupokki.ChatKiosk.common.agent.ProductAssistant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

    private final ProductAssistant productAssistant;

    @GetMapping("/chat")
    public String getProductInfo(@RequestParam String query) {

        if (query == null || query.trim().isEmpty()) {
            return "질문을 입력해주세요.";
        }

        // 1. 사용자 질문을 Agent에 전달
        String ragResponse = productAssistant.chat(query);

        // 2. Agent가 Tool 호출과 LLM 생성을 거쳐 최종 응답을 반환
        return ragResponse;
    }

    @GetMapping("")
    public String test() {
        System.out.println("test");
        return "test";
    }
}