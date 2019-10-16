package concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

	public static void main(String[] args) {
		CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> "Hello")
				.thenApply(t -> t + " World")
				.thenApply(String::toUpperCase)
				.thenAccept(System.out::println);
		System.out.println(future.toString());
	}
}
