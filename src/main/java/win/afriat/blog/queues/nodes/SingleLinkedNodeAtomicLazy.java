/*
   Copyright 2012 Frank AFRIAT

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.  
 */
package win.afriat.blog.queues.nodes;

import java.util.concurrent.atomic.AtomicReference;

public class SingleLinkedNodeAtomicLazy<T> implements LinkedNode<T> {

	protected T value;
	protected final AtomicReference<LinkedNode<T>> next;
	
	public SingleLinkedNodeAtomicLazy() {
		this.next = new AtomicReference<>();
	}
	
	public SingleLinkedNodeAtomicLazy(T value) {
		next = new AtomicReference<>();
	}

	public LinkedNode<T> getNext() {
		return this.next.get();
	}
	
	public T getValue() {
		return value;
	}
	
	public void setNext(LinkedNode<T> next) {
		this.next.lazySet(next);
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[" + String.valueOf(value) + "]";
	}
	
}
