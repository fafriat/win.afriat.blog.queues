package win.afriat.blog.queues.view;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

import win.afriat.blog.queues.SpecialLinkedListSupplier;
import win.afriat.blog.queues.SpecialLinkedListSupplierSynchronized;
import win.afriat.blog.queues.SpecialLinkedListSupplierSynchronizedBasic;
import win.afriat.blog.queues.nodes.LinkedNode;
import win.afriat.blog.queues.nodes.SimpleLinkedListSupplierSynchronized;
import win.afriat.blog.queues.nodes.SingleLinkedNode;
import win.afriat.blog.queues.nodes.SingleLinkedNodeAtomic;
import win.afriat.blog.queues.nodes.SingleLinkedNodeAtomicLazy;
import win.afriat.blog.queues.nodes.SingleLinkedNodeSynchronized;
import win.afriat.blog.queues.nodes.SingleLinkedNodeSynchronizedWrite;
import win.afriat.blog.queues.nodes.SingleLinkedNodeVolatile;

public class QueueViews {

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplier(int capacity) {
    	Supplier supplier = SingleLinkedNode<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNode";
    	return queueView;
	}
	
	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierVolatile(int capacity) {
    	Supplier supplier = SingleLinkedNodeVolatile<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNodeVolatile";
    	return queueView;
	}

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierAtomic(int capacity) {
    	Supplier supplier = SingleLinkedNodeAtomic<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNodeAtomic";
    	return queueView;
	}

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierAtomicLazy(int capacity) {
    	Supplier supplier = SingleLinkedNodeAtomicLazy<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNodeAtomicLazy";
    	return queueView;
	}

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierNodeSynchronized(int capacity) {
    	Supplier supplier = SingleLinkedNodeSynchronized<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNodeSynchronized";
    	return queueView;
	}
	//SimpleLinkedListSupplierSynchronized
	
	public static SPSCQueueView<Integer> createQueueViewSimpleLinkedListSupplierSynchronized(int capacity) {
    	Supplier supplier = SingleLinkedNode<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SimpleLinkedListSupplierSynchronized<Integer> queue = new SimpleLinkedListSupplierSynchronized<Integer>(nodeSupplier);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SimpleLinkedListSupplierSynchronized:SingleLinkedNode";
    	return queueView;
	}
	
	
	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierNodeSynchronizedWrite(int capacity) {
    	Supplier supplier = SingleLinkedNodeSynchronizedWrite<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplier<Integer> queue = new SpecialLinkedListSupplier<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplier:SingleLinkedNodeSynchronizedWrite";
    	return queueView;
	}

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierSynchronized(int capacity) {
    	Supplier supplier = SingleLinkedNode<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplierSynchronized<Integer> queue = new SpecialLinkedListSupplierSynchronized<Integer>(nodeSupplier);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplierSynchronized:SingleLinkedNode";
    	return queueView;
	}

	public static SPSCQueueView<Integer> createQueueViewSpecialLinkedListSupplierSynchronizedBasic(int capacity) {
    	Supplier supplier = SingleLinkedNode<Integer>::new;
    	Supplier<LinkedNode<Integer>> nodeSupplier = supplier;
    	SpecialLinkedListSupplierSynchronizedBasic<Integer> queue = new SpecialLinkedListSupplierSynchronizedBasic<Integer>(nodeSupplier, true);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::addToTail;
    	queueView.consumerView = queue::removeFromHead;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "SpecialLinkedListSupplierSynchronizedBasic:SingleLinkedNode";
    	return queueView;
	}
	
	public static SPSCQueueView<Integer> createQueueViewConcurrentLinkedQueue(int capacity) {
    	ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::offer;
    	queueView.consumerView = queue::poll;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "ConcurrentLinkedQueue";
    	return queueView;
	}
	
	public static SPSCQueueView<Integer> createQueueViewArrayBlockingQueue(int capacity) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);
    	SPSCQueueView<Integer> queueView = new SPSCQueueView<>();
    	queueView.producerView = queue::offer;
    	queueView.consumerView = queue::poll;
    	queueView.queueClass = queue.getClass();
    	queueView.queueName = "ArrayBlockingQueue";
    	return queueView;
	}
	
	
}
