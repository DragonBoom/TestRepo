package indi.oracle.java.net;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioFileTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void deleteEmptyDirectory() {
		String location = "d:/skyrim"
				+ "";
		Path start = Paths.get(location);
		MyFileVisitor visitor = new MyFileVisitor();
		try {
			Files.walkFileTree(start, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Delete complete, go through directory count: {}", visitor.count);
	}

	class MyFileVisitor extends SimpleFileVisitor<Path> {
		private HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		private int count = 0;

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
			hashMap.put(dir.toString(), false);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			String parentString = file.getParent().toString();
			if (!hashMap.get(parentString)) {
				hashMap.put(parentString, true);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Boolean b = hashMap.get(dir.toString());
			if (b == null) {
				try {
					throw new Exception("no include this dir!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (b.booleanValue() == false) {				
				logger.info("delete empty directory {} ", dir.toString());
				Files.delete(dir);
			}
			Path parent = null;
			if ((parent = dir.getParent()) != null) {
				hashMap.put(parent.toString(), true);
			}
			count++;
			return FileVisitResult.CONTINUE;
		}

	}
}
