package dev.cupokki.ChatKiosk.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompletionDto {
    private final String text;
}
