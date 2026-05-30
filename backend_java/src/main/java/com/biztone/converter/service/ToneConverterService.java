package com.biztone.converter.service;

import com.biztone.converter.config.PromptTemplates;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToneConverterService {

    private final ChatModel chatModel;

    public ToneConverterService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String convert(String text, String targetAudience) {
        String systemPrompt = PromptTemplates.PROMPTS.get(targetAudience);
        if (systemPrompt == null) {
            throw new IllegalArgumentException("지원하지 않는 수신 대상입니다: " + targetAudience);
        }

        // 로깅: 요청 내역
        System.out.println("\n[Spring AI Request - Target: " + targetAudience + "]");
        System.out.println("- System Prompt: " + systemPrompt);
        System.out.println("- User Text: " + text);

        // Spring AI 메시지 구성
        var systemMessage = new SystemMessage(systemPrompt);
        var userMessage = new UserMessage(text);
        var prompt = new Prompt(List.of(systemMessage, userMessage));

        // AI 호출
        var response = chatModel.call(prompt);
        String convertedText = response.getResult().getOutput().getContent();

        // 로깅: 응답 내역
        System.out.println("\n[Spring AI Response]");
        System.out.println("- Converted: " + convertedText + "\n");

        return convertedText;
    }
}
