package com.learning.portal.api.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthenticationResponse<E> {

    private String jwt;

    private String message;

    private E data;

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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
