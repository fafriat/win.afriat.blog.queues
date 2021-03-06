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

public class SingleLinkedNodeSynchronized<T> implements LinkedNode<T> {

	protected T value;
	protected LinkedNode<T> next;
	
	public SingleLinkedNodeSynchronized() {
		this.value = null;
	}
	
	public SingleLinkedNodeSynchronized(T value) {
		this.value = value;
	}

	public synchronized LinkedNode<T> getNext() {
		return this.next;
	}
	
	public T getValue() {
		return value;
	}
	
	public synchronized void setNext(LinkedNode<T> next) {
		this.next = next;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
