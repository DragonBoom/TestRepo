package indi.oracle.java.util.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.jupiter.api.Test;

/**
 * 使用ForkJoinPool能够使用数量有限的线程来完成非常多的具有父子关系的任务，比如使用4个线程来完成超过200万个任务。<br>
 * 但是，使用ThreadPoolExecutor时，是不可能完成的，因为ThreadPoolExecutor中的Thread无法选择优先执行子任务，<br>
 * 需要完成200万个具有父子关系的任务时，也需要200万个线程，显然这是不可行的。<br>
 * ForkJoinPool是使用ForkJoin的分治法解决有父子关系的多线程任务的线程池？<br>
 * 之前我的爬虫项目，也碰到了父子关系的多线程任务，当时我把按优先级执行任务的逻辑“托管”给PriorityBlockingQueue<br>
 * 或许ForkJoinPool有着更为高效的解决方案 TODO
 * PriorityBlockingQueue的排序逻辑也需要了解TODO
 */
class ForkJoinPoolTest {

    @Test
    void go() {

        ForkJoinPool pool;
        ForkJoinTask task;
    }
}
