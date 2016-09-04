package win.afriat.blog.queues.view;

public class SPSCQueueView<T> {

	public String queueName;
	public Class queueClass;
	public ProducerView<T> producerView;
	public ConsumerView<T> consumerView;
	
}
