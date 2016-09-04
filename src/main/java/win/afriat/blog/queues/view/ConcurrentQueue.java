package win.afriat.blog.queues.view;

public interface ConcurrentQueue<T> {

	public ConsumerView<T> getConsumerView();

	public ProducerView<T> getProducerView();

}
