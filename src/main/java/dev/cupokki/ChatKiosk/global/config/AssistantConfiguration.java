package dev.cupokki.ChatKiosk.global.config;

import dev.cupokki.ChatKiosk.common.agent.ProductAssistant;
import dev.cupokki.ChatKiosk.common.tool.ProductQueryTool;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AssistantConfiguration {

    // Ollama URL과 모델 이름은 application.yml 등에서 설정
    @Value("${ollama.base-url:http://localhost:11434}")
    private String ollamaBaseUrl;

    @Value("${ollama.model-name:mistral}")
    private String ollamaModelName;

    private final ProductQueryTool productQueryTool;

    @Bean
    public ProductAssistant productAssistant() {

        // 1. Ollama Chat 모델 설정
        OllamaChatModel chatModel = OllamaChatModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(ollamaModelName)
                .temperature(0.1) // 툴 호출의 안정성을 위해 낮게 설정
//                .responseFormat(ResponseFormat.JSON)
                .logRequests(true)
                .logResponses(true)
                .build();

        // 2. AiServices를 통해 Agent(Assistant) 생성 및 Tool 연결
        return AiServices.builder(ProductAssistant.class)
                .chatModel(chatModel)
                .tools(productQueryTool) // DB 조회 툴 연결
                .build();
    }
}
