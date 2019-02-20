package indi.util;

import java.util.Random;

/**
 * 用于算法的工具类
 * 
 * @author Think
 *
 */
public class AlgorithmUtils {

    /**
     * 随机整数
     */
    public static final int randomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
    
    

}
