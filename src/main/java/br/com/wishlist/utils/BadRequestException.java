package br.com.wishlist.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.BAD_REQUEST )
public class BadRequestException extends Exception {
    private String messageKey;

    public BadRequestException( String message, String messageKey ) {
        super( message );
        this.messageKey = messageKey;
    }
}
