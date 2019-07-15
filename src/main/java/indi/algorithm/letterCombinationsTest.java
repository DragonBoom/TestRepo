package indi.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class letterCombinationsTest {

    @ParameterizedTest
    @ValueSource(strings = {"7"})
    public void letterCombinations(String digits) {
        if (digits.length() == 0) {
            return;
        }
        List<StringBuilder> result = new LinkedList<>();
        char[] chars = digits.toCharArray();
        // init
        char c0 = (char) (3 * (chars[0] - '2') + 'a');
        result.add(new StringBuilder().append(c0));
        result.add(new StringBuilder().append((char) (c0 + 1)));
        result.add(new StringBuilder().append((char) (c0 + 2)));

        // double loop
        for (int n = 1; n < chars.length; n++) {
            char c = chars[n];
            if (c == '1') {
                continue;
            }
            int len = result.size();// avoid infinite loop
            for (int i = 0; i < len; i++) {
                StringBuilder sb = result.get(i);

                // 获取对应字母数组
                char c1 = (char) (3 * (c - '2') + 'a');

                // 修改一条数据
                // 添加另外两条数据
                result.add(new StringBuilder().append(sb).append(c1));
                result.add(new StringBuilder().append(sb).append((char) (c1 + 1)));
                sb.append((char) (c1 + 2));
            }
        }
        List<String> collect = result.stream().map(StringBuilder::toString).collect(Collectors.toList());
        System.out.println(collect);
    }
}
