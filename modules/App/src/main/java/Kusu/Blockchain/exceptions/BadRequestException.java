package Kusu.Blockchain.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

public class BadRequestException extends HttpStatusException{

    public BadRequestException(String message){
        super(HttpResponseStatus.BAD_REQUEST, message);
    }

    public BadRequestException(){
        super(HttpResponseStatus.BAD_REQUEST);
    }
}
