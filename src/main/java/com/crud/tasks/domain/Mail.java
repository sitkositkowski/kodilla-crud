package com.crud.tasks.domain;

import lombok.*;

import java.util.Optional;

@Builder
@Getter
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCC;
}