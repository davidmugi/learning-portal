package com.learning.portal.api.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthenticationResponse {

    private String jwt;

    private String message;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
