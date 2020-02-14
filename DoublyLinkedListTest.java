package singlylinkedlist;

public class DoublyLinkedListTest {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();
		try {
			lista.insertFirst(1);
			lista.insertFirst(2);
			lista.insertLast(4);
			System.out.println("Lista = " + lista);

			while (!lista.isEmpty())
				System.out.println("Item = " + lista.removeFirst() + " foi removido!");
		} catch (UnderflowException e) {
			e.printStackTrace();
		}
	}

}
