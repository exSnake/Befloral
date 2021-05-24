package resources;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;

public class Helpers {

	public static void main(String[] args) throws IOException {
		System.out.println(printMigrations());
	}
	
	public static String printMigrations() throws IOException {;
		LinkedList<String> filesContent = new LinkedList<String>();
		Files.walk(Paths.get("./SQL")).filter(Files::isRegularFile)
				.forEach(ThrowingConsumer.throwingConsumerWrapper(p  -> {
					Files.readAllLines(p).forEach( s -> filesContent.add(s) );
				}));
		return String.join("\n", filesContent);
	}
}


