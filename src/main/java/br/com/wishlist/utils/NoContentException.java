package br.com.wishlist.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT )
public class NoContentException extends Exception {

    private String messageKey;

    public NoContentException( String message, String messageKey ) {
        super(message);
        this.messageKey = messageKey;
    }
}
