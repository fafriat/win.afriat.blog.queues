package win.afriat.blog.queues.view;

@FunctionalInterface
public interface ConsumerView<T> {

	public T consume();

}
