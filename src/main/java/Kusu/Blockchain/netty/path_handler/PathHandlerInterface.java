package Kusu.Blockchain.netty.path_handler;

import Kusu.Blockchain.exceptions.HttpStatusException;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

import java.security.NoSuchAlgorithmException;

public interface PathHandlerInterface {


    FullHttpResponse handle(HttpRequest request, ByteBuf body) throws HttpStatusException, NoSuchAlgorithmException;
}
