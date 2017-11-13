package circularList;

import node.Node;

public class CircularList<T> {
	private Node<T> sentinel	= null;
	private Node<T> actual	= null;
	
	public CircularList() {
		sentinel	 = new Node<T>();
		actual	 = new Node<T>();
		sentinel.setIndex(-1);
		actual.setIndex(-1);
	}
	public CircularList(T value) {
		this();
		sentinel.setNext(new Node<T>(value));
		actual = sentinel.getNext();
		sentinel.getNext().setNext(actual);
	}
	public Node<T> Search(T value){
		return(!isEmpty())?Search(value, sentinel.getNext()):null;
		// else 
		//	return null;
		//return Search(value, sentinel.getNext());
	}
	private Node<T> Search(T value, Node<T> list){	
		if (list.getNext().getValue().equals(value)) {
			return list.getNext();
		}
		if (list.getNext().equals(sentinel.getNext())) {
			return null;
		}
		
		return Search(value, list.getNext());
	}
	public Node<T> indexOf(long index){
		return (!isEmpty())?indexOf(index, sentinel.getNext()):null;
	}
	private Node<T> indexOf(long index,Node<T> list){
		if (list.getNext().getIndex()==index) {
			return list.getNext();
		}
		if (list.getNext().equals(sentinel.getNext())) {
			return null;
		}
		return indexOf(index, list.getNext());
	}
	public boolean isEmpty() {
		return (sentinel.getNext()==null)?true:false;
	}
	
	public Node<T> getLast(){
		Node<T> tmp = sentinel.getNext();
		if(!isEmpty()) {
			while(!sentinel.getNext().equals(tmp.getNext())) 
				tmp = tmp.getNext();
				return tmp;
		}// end if
		return null;
	}
	private Node<T> getFist(){
		return (!isEmpty())?sentinel.getNext():null;
	}
	//Version de Alets
	public void AddFirst(T value) {
		Node<T> nuevo = new Node<T>(value), last = getLast();
		if(last == null) {
			sentinel.setNext(nuevo);
			nuevo.setNext(sentinel.getNext());
		}else {
			nuevo.setNext(sentinel.getNext());
			sentinel.setNext(nuevo);
			last.setNext(nuevo);
		}
	}
	//Version en clase
	public void addFirst(T value) {
		Node<T> nuevo = new Node<T>(value);
		Node<T> last = getLast();
		if(isEmpty()) {
			sentinel.setNext(nuevo);
			nuevo.setNext(nuevo);
		}else {
			nuevo.setNext(sentinel.getNext());
			sentinel.setNext(nuevo);
			last.setNext(nuevo);
		}
		reIndex();
	}
	public void addBefore(T value, T newValue) {
		Node<T> foundBefore = searchBefore(value, sentinel.getNext());
		if (foundBefore != null) {
			if (foundBefore.getNext().equals(getFist())) {
				addFirst(newValue);
			}else {
				Node<T> _new = new Node<T>(newValue);
				_new.setNext(foundBefore.getNext());
				foundBefore.setNext(_new);
			}
			reIndex();
		}
	}
	public void addEnd(T value) {
		Node<T> _new = new Node<T>(value);
		Node<T> last = getLast();
		if (!isEmpty()) {
			_new.setNext(last.getNext());
			last.setNext(_new);
		}else {
			_new.setNext(sentinel.getNext());
			sentinel.setNext(_new);
			last.setNext(_new);
		}
		reIndex();
	}
	public void addAfter(T value, T newValue) {
		//Node<T> _new = new Node<T>(newValue);
		Node<T> found = Search(value);
		if (found != null) {
			if (found.equals(getLast())) {
				addEnd(newValue);
			}else {
				Node<T> _new = new Node<T>(newValue);
				_new.setNext(found.getNext());
				found.setNext(_new);
			}
			reIndex();
		}
	}
	
	public void print() {
		Node<T> tmp = sentinel.getNext();
		/*if(tmp!=null) 
			System.out.println(tmp.getValue());
			while(tmp.getNext()!=sentinel.getNext()) {
				System.out.println(tmp.getNext().getValue());
				tmp=tmp.getNext();
		}*/
		if(!isEmpty()) {
			while(!tmp.getNext().equals(sentinel.getNext())) {
				System.out.println(tmp.getIndex()+" "+tmp.getValue());
				tmp=tmp.getNext();
			}
			System.out.println(tmp.getIndex()+" "+tmp.getValue());
		}
	}
	private void reIndex() {
		int index=0;
		Node<T> tmp=sentinel.getNext();
		while(!tmp.getNext().equals(sentinel.getNext())) {
			tmp.setIndex(index);
			index++;
			tmp=tmp.getNext();
		}
		tmp.setIndex(index++);
	}
	private Node<T> searchBefore(T value, Node<T> list){	
		if (list.getNext().getValue().equals(value)) {
			return list;
		}
		if (list.getNext().equals(sentinel.getNext())) {
			return null;
		}
		
		return searchBefore(value, list.getNext());
	}
	
	public boolean remove(T value) {
		if(!isEmpty()) {
			Node<T> found = Search(value);
			if(found!= null) {
				Node<T> tmp =searchBefore(value,sentinel.getNext());
				if(tmp.equals(tmp.getNext())) 
					sentinel.setNext(null);
				else if(sentinel.getNext().equals(found)) {
					sentinel.setNext(found.getNext());
					tmp.setNext(found.getNext());
				}else {
					tmp.setNext(found.getNext());
				}
			}
				/*
				if(tmp.equals(tmp.getNext())){
					sentinel.setNext(null);
						
					}else{
						if (found.equals(sentinel.getNext())){
							sentinel.setNext(found.getNext());
						}
					tmp.setNext(found.getNext());
					}
			*/
		}
		return false;
			
	}
	public void remplase(T value,T newValue) {
		Node<T> found=Search(value);
		if (found != null) {
			found.setValue(newValue);
		}
	}
	public String get(T value) {
		Node<T> found = Search(value);
		if (found != null)
			return found.toString();
		return null;
	}
	
	public int size() {
		int cont=0;
		Node<T> tmp=sentinel.getNext();
		while (!tmp.getNext().equals(sentinel.getNext())) {
			cont++;
			tmp=tmp.getNext();
		}
		return 1 + cont;
	}
	public void removeFirst() {
		Node<T> foundFisrt= getFist();
		Node<T> foundLast= getLast();
		if (foundFisrt != null && foundLast != null) {
			sentinel.setNext(foundFisrt.getNext());
			foundLast.setNext(sentinel.getNext());
			System.gc();
			reIndex();
		}
	}
	public void removeLast() {
		Node<T> foundBefore = searchBefore(getLast().getValue(), sentinel.getNext());
		if (foundBefore != null) {
			foundBefore.setNext(sentinel.getNext());
			System.gc();
			reIndex();
		}
	}
	public void removeAfter(T value) {
		Node<T> found = Search(value);
		if (found != null) {
			Node<T> tmp = found.getNext().getNext();
			if (found.getNext().equals(sentinel.getNext())) {
				sentinel.setNext(tmp);
				found.getNext().setNext(null);
				found.setNext(tmp);
			}else {
				found.getNext().setNext(null);
				found.setNext(tmp);
			}
			System.gc();
			reIndex();
		}
	}
	public void removeBefore(T value) {
		Node<T> found = Search(value);
		Node<T> foundBefore = searchBefore(searchBefore(value, sentinel.getNext()).getValue(), sentinel.getNext());
		if (found != null && foundBefore != null) {
			if (foundBefore.getNext().equals(sentinel.getNext())) {
				foundBefore.getNext().setNext(null);
				sentinel.setNext(found);
				foundBefore.setNext(sentinel.getNext());
			}else {
				foundBefore.getNext().setNext(null);
				foundBefore.setNext(found);
			}
			reIndex();
		}
	}
	public void clean() {
		if (!isEmpty()) {
			Node<T> tmp=sentinel.getNext();
			Node<T> temp=tmp.getNext();
			while (!tmp.getNext().equals(sentinel.getNext())) {
				tmp.setNext(null);
				tmp=temp;
				temp=temp.getNext();
			}
			sentinel.setNext(null);
			System.gc();
		}
	}
}