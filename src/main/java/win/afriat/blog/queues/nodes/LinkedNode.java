package win.afriat.blog.queues.nodes;

public interface LinkedNode<T> {

	LinkedNode<T> getNext();
	void setNext(LinkedNode<T> next);

	T getValue();
	void setValue(T value);

}
