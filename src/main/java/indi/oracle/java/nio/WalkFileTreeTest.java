package indi.oracle.java.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreeTest {

	public static void main(String[] args) {
		String location = "D:\\download\\nginx-1.12.2\\pic\\uid\\1\\upload\\13214.png";
		String destination = "D:\\download\\nginx-1.12.2\\pic\\uid\\1\\fff.png";
		Path p1 = Paths.get(location);
		Path p2 = Paths.get(destination);
		int baseCount = 5;
		System.out.println(p1.getFileName());

		FileVisitor<Path> fv = new FileVisitor<Path>() {
			Path p3 = null;

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
					throws IOException {
				int nameCount = dir.getNameCount();
				// System.out.println(dir.subpath(baseCount, nameCount));
				if (nameCount > baseCount + 1) {
					Path part = dir.subpath(baseCount + 1, nameCount);
					p3 = p2.resolve(part);
					if (!Files.isDirectory(p3)) {
						Files.createDirectory(p3);
					}
				} else {
					p3 = p2;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException {
				if (p3 != null) {
					Path p4 = p3.resolve(file.getFileName());
					System.out.println(file  + "	" + p4);
					Files.move(file, p4);
				}
					
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

				return null;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

		};
		
		try {
			Files.walkFileTree(p1, fv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
