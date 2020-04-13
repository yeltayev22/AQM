package kz.yeltayev.aqm.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("yeltayev22 !!!!!! Error" + " Status code "
                + response.getStatusCode() + " Status text " + response.getStatusText());
    }
}