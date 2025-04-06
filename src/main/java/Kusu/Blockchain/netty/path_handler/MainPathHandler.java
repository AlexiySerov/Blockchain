package Kusu.Blockchain.netty.path_handler;

import Kusu.Blockchain.exceptions.HttpStatusException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainPathHandler implements PathHandlerInterface {

    private final WalletPathHandler walletPathHandler = new WalletPathHandler();
    private final TransactionPathHandler transactionPathHandler = new TransactionPathHandler();
    private final ObjectPathHandler objectPathHandler = new ObjectPathHandler();
    private final GiftPathHandler giftPathHandler = new GiftPathHandler();


    @Override
    public FullHttpResponse handle(HttpRequest request, ByteBuf body) {
        Logger log = Logger.getLogger("ServerHandler");

        try {
            switch (request.uri()){
                case "/wallet" -> { return walletPathHandler.handle(request, body); }
                case "/transaction" -> { return transactionPathHandler.handle(request, body); }
                case "/object" -> { return objectPathHandler.handle(request, body); }
                case "/gift" -> { return giftPathHandler.handle(request, body); }
                default -> {
                    return new DefaultFullHttpResponse(
                            HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK,
                            Unpooled.copiedBuffer("I don`t know this url", CharsetUtil.UTF_8)
                    );
                }
            }
        } catch (HttpStatusException e){
            return new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST,
                    Unpooled.copiedBuffer(String.format("Error: %s/n%s", e.getClass(), e.getMessage()), CharsetUtil.UTF_8)
            );
        }catch (Exception e){
            return new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.INTERNAL_SERVER_ERROR,
                    Unpooled.copiedBuffer(String.format("Error: %s/n%s", e.getClass(), e.getMessage()), CharsetUtil.UTF_8)
            );
        }

    }
}
