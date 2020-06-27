/**
 * 
 */
package indi.oracle.java.jvm;

/**
 * @author wzh
 * @since 2020.06.18
 */
public class JVMTest {
    private static final int _1MB = 1024 * 1024;
    
    /**
     * 测试类，为了避免其他要素的影响，不使用JUnit
     * 
     * @author DragonBoom
     * @since 2020.06.18
     * @param args
     */
    public static void main(String[] args) {
        // 测试分配内存时的GC
        testAllocatioGC();
    }
    
    /**
     * JVM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 
     * 参数解释：打印GC信息，Java 堆最小20m、最大20m，新生代10m（因此老年代10m），打印GC详细信息，Eden:Survivor=8:1
     * 
[GC (Allocation Failure) [PSYoungGen: 7651K->503K(9728K)] 7651K->6983K(19968K), 0.0096446 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
[Full GC (Ergonomics) [PSYoungGen: 503K->0K(9728K)] [ParOldGen: 6480K->6951K(10240K)] 6983K->6951K(19968K), [Metaspace: 2792K->2792K(1056768K)], 0.0081851 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 9728K, used 4188K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 9216K, 45% used [0x00000000ff600000,0x00000000ffa17228,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
  to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
 ParOldGen       total 10240K, used 6951K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 67% used [0x00000000fec00000,0x00000000ff2c9e98,0x00000000ff600000)
 Metaspace       used 2798K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K
     * 
     * 可以看到新生代使用了4M，老年代使用了6M
     * 
     * @author DragonBoom
     * @since 2020.06.18
     */
    private static void testAllocatioGC() {
        byte[] bytes1, bytes2, bytes3, bytes4;
        bytes1 = new byte[ 2 * _1MB];
        bytes2 = new byte[ 2 * _1MB];
        bytes3 = new byte[ 2 * _1MB];
        bytes4 = new byte[ 4 * _1MB];
    }

}
