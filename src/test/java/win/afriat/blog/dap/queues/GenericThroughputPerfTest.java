package win.afriat.blog.dap.queues;


import win.afriat.blog.queues.view.ConsumerView;
import win.afriat.blog.queues.view.ProducerView;
import win.afriat.blog.queues.view.QueueViews;
import win.afriat.blog.queues.view.SPSCQueueView;

public class GenericThroughputPerfTest {
    // 15 == 32 * 1024
    public static final int REPETITIONS = Integer.getInteger("reps", 50) * 1000 * 1000;
    public static final Integer TEST_VALUE = 11;
    public static final int QUEUE_CAPACITY = 1 << Integer.getInteger("pow2.capacity", 17);

    
    public static void main(final String[] args) throws Exception {
        System.out.println("capacity:" + QUEUE_CAPACITY + " reps:" + REPETITIONS);
        final SPSCQueueView<Integer> queue = QueueViews.createQueueViewArrayBlockingQueue(QUEUE_CAPACITY);
        for (int i = 0; i < 100000; i++) {
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
        long[] sum = new long[3];
        for (int i = testCount-lastCount; i < testCount; i++) {
            sum[0] += results[i][0];
            sum[1] += results[i][1];
            sum[2] += results[i][2];
        }
        System.out.format("summary,QueuePerfTest,%s, ops=%,d, latency=%,d, failed.poll=%,d\n", queue.queueName, sum[0]/lastCount, sum[1]/lastCount, sum[2]/lastCount);
    }

    private static long[] performanceRun(int runNumber, String queueName, ProducerView<Integer> producerView, ConsumerView<Integer> consumerView) throws Exception {
        Producer p = new Producer(producerView);
        Thread thread = new Thread(p);
        thread.start();// producer will timestamp start

        Integer result;
        int queueEmpty = 0;
        int removed = 0;
        boolean countingEmpty = false;
        do {
        	result = consumerView.consume();
            if (null == result) {
            	if (countingEmpty) {
            		queueEmpty++;
            	}
//              System.out.println("Queue Empty:" + queueEmpty);
                Thread.yield();
            }
            else {
            	countingEmpty = true;
            	removed++;
//            	System.out.println("Consumed:" + removed);
            }
        } while (removed != REPETITIONS);
        long end = System.nanoTime();
        
        thread.join();
        long duration = end - p.start;
        long ops = (REPETITIONS * 1000L * 1000L * 1000L) / duration;
        long latency = duration / REPETITIONS;
        perRunPrint(runNumber, queueName, p, result, removed, queueEmpty, ops, latency);
        return new long[] {ops, latency, queueEmpty};
    }

    private static void perRunPrint(int runNumber, String queueName, Producer p, Integer result, int removed,
            int queueEmpty, long ops, long latency) {
        String qName = queueName;
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
