package indi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import indi.util.CollectionUtils;
import indi.util.ReflectUtils;
import indi.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 可以把该类当作各类的目录使用
 */
@Slf4j
public final class Blank {
    private static final String ONE_DRIVE_ENV_NAME = "OneDrive";
    private static final String ONE_DRIVE_PATH_STR = System.getenv("OneDrive");
    private static final Path ONE_DRIVE_PATH = Paths.get(ONE_DRIVE_PATH_STR, new String[0]);

    @Test
    void v() throws IOException {
        String rootLocation = ReflectUtils.getRootLocation();

        if (!Files.isDirectory(ONE_DRIVE_PATH, new java.nio.file.LinkOption[0])) {
            throw new IllegalArgumentException(
                    String.format("One Drive ���� %s ����", new Object[] { ONE_DRIVE_PATH_STR }));
        }

        Path path = ONE_DRIVE_PATH.resolve("En.md");

        if (!Files.exists(path, new java.nio.file.LinkOption[0])) {
            throw new IllegalArgumentException(
                    String.format("En.md ���� %s ����", new Object[] { ONE_DRIVE_PATH_STR }));
        }

        int backupTimes = 0;

        Path backupPath = null;
        while (true) {
            backupPath = Paths.get(rootLocation, new String[] { "En.md" + backupTimes });
            if (!Files.exists(backupPath, new java.nio.file.LinkOption[0])) {
                break;
            }
            backupTimes++;
        }

        List<String> allLines = Files.readAllLines(path, Charset.forName("utf-8"));

        ListMultimap<Character, String> multimap = MultimapBuilder.hashKeys().linkedListValues().build();
        int beginLine = 0;

        for (String str : allLines) {
            char firstChar = ' ';
            if (str.length() > 0) {
                firstChar = str.charAt(0);
            }
            if (!StringUtils.isEnglish(Character.valueOf(firstChar))) {
                beginLine++;

                continue;
            }
            break;
        }
        for (int i = beginLine; i <= allLines.size() - 1; i++) {
            String line = (String) allLines.get(i);
            char c = line.charAt(0);

            if (!StringUtils.isEnglish(Character.valueOf(c))) {
                throw new IllegalArgumentException(String.format("�� %d ����������������������������%s",
                        new Object[] { Integer.valueOf(i), line }));
            }
            if (StringUtils.isChaos(line)) {
                throw new IllegalArgumentException(
                        String.format("�� %d ����������������������%s", new Object[] { Integer.valueOf(i), line }));
            }

            if (c < 'a') {
                c = (char) (c + ' ');
            }
            multimap.put(Character.valueOf(c), line);
        }

        List<String> newLines = CollectionUtils.cloneSubList(allLines, 0, beginLine);
        log.debug("headers: {}", newLines);
        Set<Character> keySet = multimap.keySet();
        keySet.forEach(key -> {
            List<String> list = multimap.get(key);

            newLines.addAll(list);
        });
        String newText = StringUtils.join("\n", newLines);
        log.info(newText);
        if (!newLines.equals(allLines)) {

            Files.copy(path, backupPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
            log.info("备份成功" + backupPath);
            Files.write(path, newText.getBytes("utf-8"), new OpenOption[] { StandardOpenOption.TRUNCATE_EXISTING });
            log.info("更新成功");
        } else {
            log.info("无需更新");
        }
    }
}
