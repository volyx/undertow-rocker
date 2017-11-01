package test;

import com.fizzed.rocker.runtime.RockerRuntime;
import io.undertow.Undertow;
import io.undertow.util.Headers;

public class Main {

    public static void main(final String[] args) {

        RockerRuntime.getInstance().setReloading(true);
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                    String resp = views.index.template("World")
                            .render()
                            .toString();
                    exchange.getResponseSender().send(resp);

                }).build();
        server.start();
    }
}