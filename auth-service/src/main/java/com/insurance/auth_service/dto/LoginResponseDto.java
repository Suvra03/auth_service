package com.insurance.auth_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private String token;
    private Long customerId;
    private String email;
}
