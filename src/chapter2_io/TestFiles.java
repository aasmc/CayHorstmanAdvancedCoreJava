package chapter2_io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class TestFiles {

    public static void testMove() throws IOException {
        Path from = Paths.get("TestDirectory/from/toMove.txt");
        Path fileName = from.getFileName();
        Path to = Paths.get("TestDirectory/to/", fileName.toString());
        Files.move(from, to);
    }

    public static void testCopy() throws IOException {
        Path dirPath = Paths.get("TestDirectory/from/");
        Path filePath = dirPath.resolve("toCopy.txt");

        Path to = Paths.get("TestDirectory/to", "toCopy.txt");

        Files.copy(filePath, to, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void testWalk() throws IOException {
        Path pathToRoot = Paths.get("TestDirectory/manyFiles");
        try (Stream<Path> entries = Files.walk(pathToRoot)) {
            entries.forEach((p) -> {
                System.out.println("Absolute path = " + p.toAbsolutePath());
            });
        }
    }

    public static void testDirectoryStream() throws IOException {
        Path pathToRoot = Paths.get("TestDirectory/manyFiles");
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(pathToRoot)) {
            for (Path entry :
                    entries) {
                System.out.println("entry file name = " + entry.getFileName());
            }
        }
    }

    public static void testWalkFileTree() throws IOException {
        Files.walkFileTree(Paths.get("/"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("dir = " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });
    }

    public static void testZipFileSystem() throws IOException {
        Path pathToZipFile = Paths.get("TestDirectory/testZip.zip");
        FileSystem fs = FileSystems.newFileSystem(pathToZipFile, (ClassLoader) null);
        Files.copy(
                fs.getPath("/testZip/dir1/install.sh"),
                Paths.get("TestDirectory/to/zip/install.sh"),
                StandardCopyOption.REPLACE_EXISTING
        );

        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });

    }


    public static void main(String[] args) throws IOException {
//        testMove();
//        testCopy();
//        System.out.println("Testing Walk");
//        testWalk();
//        System.out.println("Testing Directory stream");
//        testDirectoryStream();
//        testWalkFileTree();

        testZipFileSystem();
    }
}




















