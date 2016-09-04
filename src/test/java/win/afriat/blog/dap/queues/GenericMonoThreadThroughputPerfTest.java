package win.afriat.blog.dap.queues;

import win.afriat.blog.queues.view.ConsumerView;
import win.afriat.blog.queues.view.ProducerView;
import win.afriat.blog.queues.view.QueueViews;
import win.afriat.blog.queues.view.SPSCQueueView;

public class GenericMonoThreadThroughputPerfTest {
    // 15 == 32 * 1024
    public static final Integer TEST_VALUE = 11;
    public static final int QUEUE_CAPACITY = 1 << Integer.getInteger("pow2.capacity", 22);
    public static final int REPETITIONS = QUEUE_CAPACITY;
        
    public static void main(final String[] args) throws Exception {
        System.out.println("capacity:" + QUEUE_CAPACITY + " reps:" + REPETITIONS);
        final SPSCQueueView<Integer> queue = QueueViews.createQueueViewArrayBlockingQueue(QUEUE_CAPACITY);
        for (int i = 0; i < QUEUE_CAPACITY; i++) {
            queue.producerView.produce(TEST_VALUE);
            queue.consumerView.consume();
        }
        final int testCount = 10;
        final long[][] results = new long[testCount][2];
        for (int i = 0; i < testCount; i++) {
            System.gc();
            results[i] = performanceRun(i, queue.queueName, queue.producerView, queue.consumerView);
        }
        // only average last 8 results for summary
        final int lastCount = 8;
        long[] sum = new long[2];
        for (int i = testCount-lastCount; i < testCount; i++) {
            sum[0] += results[i][0];
            sum[1] += results[i][1];
        }
        System.out.format("summary,QueuePerfTest,%s, ops=%,d, latency=%,d\n", queue.queueName, sum[0]/lastCount, sum[1]/lastCount);
    }

    private static long[] performanceRun(int runNumber, String queueClass, ProducerView<Integer> producerView, ConsumerView<Integer> consumerView) throws Exception {
        Producer p = new Producer(producerView);
        p.run();
        
        Integer result;
        int queueEmpty = 0;
        int removed = 0;
        do {
        	result = consumerView.consume();
            if (null == result) {
                queueEmpty++;
//                System.out.println("Queue Empty:" + queueEmpty);
                Thread.yield();
            }
            else {
            	removed++;
//            	System.out.println("Consumed:" + removed);
            }
        } while (removed != REPETITIONS);
        long end = System.nanoTime();
        
        long duration = end - p.start;
        long ops = (REPETITIONS * 1000L * 1000L * 1000L) / duration;
        long latency = duration / REPETITIONS;
        perRunPrint(runNumber, queueClass, p, result, removed, queueEmpty, ops, latency);
        return new long[] {ops, latency, queueEmpty};
    }

    private static void perRunPrint(int runNumber, String qName, Producer p, Integer result, int removed,
            int queueEmpty, long ops, long latency) {
        System.out.format("%d - ops/sec=%,d - latency=%d ns - %s result=%d success.poll=%d failed.poll=%d success.offer=%d failed.offer=%d\n", runNumber, ops, latency,
                qName, result, removed, queueEmpty, p.added, p.queueFull);
    }

    public static class Producer implements Runnable {
        private final ProducerView<Integer> queue;
        volatile int queueFull = 0;
        volatile int added = 0;
        volatile long start = 0;

        public Producer(ProducerView<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            int f = 0;
            int a = 0;
            ProducerView<Integer> q = queue;
            long s = System.nanoTime();
            do {
            	boolean produced = q.produce(TEST_VALUE); 
            	if (! produced) {
                    f++;
//                    System.out.println("Queue Full:" + f);
                    Thread.yield();
            	}
            	else {
            		a++;
//            		System.out.println("Produced:" + a);
            	}
            } while (a != REPETITIONS);
            
            queueFull = f;
            added = a;
            start = s;
        }
    }
}
