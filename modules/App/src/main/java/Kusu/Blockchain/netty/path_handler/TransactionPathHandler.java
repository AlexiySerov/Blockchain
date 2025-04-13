package Kusu.Blockchain.netty.path_handler;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

public class TransactionPathHandler implements PathHandlerInterface{
    @Override
    public FullHttpResponse handle(HttpRequest request, ByteBuf body) {
        return null;
    }
}
