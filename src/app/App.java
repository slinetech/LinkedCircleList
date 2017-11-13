package app;

import circularList.CircularList;
import node.Node;

public class App {

	public static void main(String[] args) {
		CircularList<String> listilla = new CircularList<String>();
		listilla.addFirst("globos");
		listilla.addFirst("sus");
		listilla.addFirst("y");
		listilla.addFirst("Pepe");
		listilla.addFirst("Don");
		listilla.remove("sus");
		listilla.addEnd("xavi");
		listilla.addAfter("xavi", "kike");
		listilla.addAfter("Don","oscar");
		listilla.addBefore("Don", "Milton");
		listilla.addBefore("Pepe", "Andrik");
		listilla.print();
		listilla.remplase("xavi", "regina");
		System.out.println("");
		listilla.print();
		System.out.println(listilla.get("Don"));
		System.out.println(listilla.size());
		
		Node<String> iOf=listilla.indexOf(0);
		if (iOf != null) {
			System.out.println(iOf.getValue()+" indexOf");
		}
		listilla.removeFirst();
		listilla.removeLast();
		listilla.removeAfter("Andrik");
		listilla.removeBefore("y");
		System.out.println("");
		listilla.print();
		
		listilla.clean();
		System.out.println("");
		listilla.print();
	}

}
