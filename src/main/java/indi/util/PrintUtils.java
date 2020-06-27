package indi.util;

import java.util.function.Function;

public class PrintUtils {
    private Function<Object, Object> translator;

    private PrintUtils() {
    }

    public static final PrintUtils begin() {
        return new PrintUtils();
    }

    public static final PrintUtils with(Function<Object, Object> translator) {
        PrintUtils printUtils = new PrintUtils();
        printUtils.translator = translator;
        return printUtils;
    }

    /**
     * 向控制台打印一对映射关系，格式如下：
     * 
     * <pre>
     * [参数参数参数] -> 值值值值值值值值值值值值值
     * </pre>
     * 
     * @return PrintUtils 可继续从下一行开始打印
     */
    public PrintUtils print(Object p, Object v) {
        StringBuilder sb = new StringBuilder();
        String toPrint = sb.append("[").append(p).append("] -> ").append(v).toString();
        System.out.println(toPrint);
        return this;
    }

    /**
     * 见 {@link indi.util.PrintUtils#print(Object, Object)}
     */
    public PrintUtils print(Object o, Function<Object, Object> translator) {
        print(o, translator.apply(o));
        return this;
    }
    
    /**
     * 打印传入对象。若设置了翻译器，则会将对象以 {@link indi.util.PrintUtils#print(Object, Object)} 的方式翻译后打印出来
     * 
     * @param o
     */
    public PrintUtils print(Object o) {
        if (translator != null) {
            print(o, translator);
        } else {
            System.out.println(o);
        }
        return this;
    }
}
