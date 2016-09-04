package win.afriat.blog.queues.view;

@FunctionalInterface
public interface ProducerView<T> {

	public boolean produce(T t);
	
}
