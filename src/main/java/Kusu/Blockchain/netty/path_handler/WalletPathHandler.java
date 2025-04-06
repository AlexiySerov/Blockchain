package Kusu.Blockchain.netty.path_handler;

import Kusu.Blockchain.exceptions.BadRequestException;
import Kusu.Blockchain.utils.GsonSerialization;
import Kusu.Blockchain.models.WalletHandler;
import Kusu.Blockchain.utils.SHA512;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.security.NoSuchAlgorithmException;


public class WalletPathHandler implements PathHandlerInterface{

    private final WalletHandler walletHandler = new WalletHandler();
    private final Gson gson = GsonSerialization.getInstance();

    @Override
    public FullHttpResponse handle(HttpRequest request, ByteBuf body) throws BadRequestException, NoSuchAlgorithmException {

        switch (request.method().name()){
            case "GET" -> {
                return new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(
                                gson.toJson(
                                        walletHandler.getWallet(
                                                SHA512.byteArrayToHexString(readBody(body))
                                        )
                                ),
                                CharsetUtil.UTF_8)
                );
            }

            case "POST" -> {
                return new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        HttpResponseStatus.OK,
                        Unpooled.copiedBuffer(
                                gson.toJson(walletHandler.createWallet())
                                , CharsetUtil.UTF_8)
                );
            }

            default -> throw new BadRequestException("Unexpected value: " + request.method());
        }


    }
}
