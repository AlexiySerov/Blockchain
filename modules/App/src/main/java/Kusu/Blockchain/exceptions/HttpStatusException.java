package Kusu.Blockchain.exceptions;

import io.netty.handler.codec.http.HttpResponseStatus;

public class HttpStatusException extends Exception{


    private final HttpResponseStatus status;

    public HttpStatusException(HttpResponseStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatusException(HttpResponseStatus status){
        super();
        this.status = status;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }
}
