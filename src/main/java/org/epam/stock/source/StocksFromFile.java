package org.epam.stock.source;

import reactor.core.publisher.Flux;

import java.util.Scanner;

public class StocksFromFile implements StockSource<String, String> {

    @Override
    public Flux<String> getStocks(String fileName) {
        return Flux.<String, Scanner>generate(
                        () -> new Scanner(getClass().getClassLoader().getResourceAsStream(fileName)),
                        (scanner, sink) -> {
                            try {
                                if (scanner.hasNext()) {
                                    sink.next(scanner.nextLine());
                                } else {
                                    scanner.close();
                                    sink.complete();
                                }
                            } catch (Exception e) {
                                sink.error(e);
                                e.printStackTrace();
                            }
                            return scanner;
                        }
                )
                .skip(1);
    }
}
