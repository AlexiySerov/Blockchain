package Kusu.Blockchain.netty.path_handler;

import Kusu.Blockchain.exceptions.HttpStatusException;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;

public interface PathHandlerInterface {


    FullHttpResponse handle(HttpRequest request, ByteBuf body) throws HttpStatusException, NoSuchAlgorithmException;

    default byte[] readBody(ByteBuf httpContent){
        byte[] bytes = new byte[httpContent.readableBytes()];
        ((HttpContent) httpContent).content().duplicate().readBytes(bytes);

        return bytes;
    }
}
