package Kusu.Blockchain.netty.server;

import Kusu.Blockchain.netty.path_handler.MainPathHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    Logger log = Logger.getLogger("ServerHandler");

    private final MainPathHandler mainPathHandler = new MainPathHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest request) {
            ByteBuf httpContent = ((HttpContent) msg).content();

            log.log(Level.INFO, "Received request: " + request.method() + " " + request.uri());

            FullHttpResponse response = mainPathHandler.handle(request, httpContent);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

            ctx.writeAndFlush(response);
        }

        if (msg instanceof LastHttpContent) {
            // Последняя часть HTTP-сообщения
            System.out.println("End of request");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
