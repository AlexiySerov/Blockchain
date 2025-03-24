package Kusu.Blockchain.netty.path_handler;

import Kusu.Blockchain.exceptions.BadRequestException;
import Kusu.Blockchain.utils.GsonSerialization;
import Kusu.Blockchain.utils.WalletHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.security.NoSuchAlgorithmException;


public class WalletPathHandler implements PathHandlerInterface{

    private final WalletHandler walletHandler = new WalletHandler();

    @Override
    public FullHttpResponse handle(HttpRequest request, ByteBuf body) throws BadRequestException, NoSuchAlgorithmException {

        switch (request.method().name()){
            case "GET" -> {
                return new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(
                                GsonSerialization.getInstance().toJson(
                                        walletHandler.getWallet(body.toString())
                                ),
                                CharsetUtil.UTF_8)
                );
            }
            case "POST" -> {
                return new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(
                                walletHandler.createWallet()
                                , CharsetUtil.UTF_8)
                );
            }
            default -> throw new BadRequestException("Unexpected value: " + request.method());
        }


    }
}
