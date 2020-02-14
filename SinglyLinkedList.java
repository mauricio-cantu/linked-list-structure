package singlylinkedlist;

public class SinglyLinkedList<E> implements List<E> {

	protected Node<E> head;
	protected Node<E> tail;
	protected int numElements;

	public SinglyLinkedList() {
		head = tail = null;
		numElements = 0;
	}

	public int numElements() {
		return numElements;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public boolean isFull() {
		// uma lista com alocação dinâmica nunca estará cheia!
		return false;
	}

	public void insertFirst(E element) {
		// cria um novo nó e o torna o novo "head"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}
		numElements++;
	}

	public void insertLast(E element) {
		// cria um novo nó e o torna o novo "tail"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			tail = newNode;
		}
		numElements++;
	}

	public E removeFirst() {
		// verifica se há pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda referência temporária ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else
			// ...senão, o segundo elemento passa a ser o "head"
			head = head.getNext();

		numElements--;
		return element;
	}

	public E removeLast() {
		if (isEmpty())
			throw new UnderflowException();
		// guarda uma referência temporária ao elemento sendo removido
		E element = tail.getElement();
		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...senão, é necessário percorrer o encadeamento
			// até chegar ao nó imediatamente anterior ao último...
			Node<E> prev = head;
			while (prev.getNext() != tail)
				prev = prev.getNext();

			// ...para poder torná-lo o novo "tail"
			tail = prev;
			prev.setNext(null);
		}
		numElements--;
		return element;
	}

	public void insert(E element, int pos) {
		// verifica se a posição é válida
		if (pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: inserção no início...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou inserção no final
			insertLast(element);
		else { // caso geral: inserção no meio da lista
			// localiza o nó imediatamente anterior à posição
			// onde o novo será inserido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();
			// cria um novo nó e o posiciona logo após "prev",
			// ajustando os apontamentos e o total de elementos
			Node<E> newNode = new Node<E>(element);
			newNode.setNext(prev.getNext());
			prev.setNext(newNode);
			numElements++;
		}
	}

	public E remove(int pos) {
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: remoção do início...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements - 1) // ... ou remoção do final
			return removeLast();
		else { // caso geral: remoção do meio da lista
			// localiza o nó imediatamente anterior à posição
			// de onde o elemento será removido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();
			// guarda uma ref. temporária ao elemento sendo removido
			E element = prev.getNext().getElement();
			// ajusta o encadeamento "pulando" o nó sendo removido
			prev.setNext(prev.getNext().getNext());
			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	public int search(E element) {
		// percorre o encadeamento até encontrar o elemento
		Node<E> current = head;
		int i = 0;
		while (current != null) {
			if (element.equals(current.getElement()))
				return i;
			i++;
			current = current.getNext();
		}

		// se chegar até aqui, é porque não encontrou
		return -1;
	}

	public E get(int pos) {
		// verifica se a posi‹o Ž v‡lida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento atŽ chegar ao elemento
		Node<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();

		return current.getElement();
	}

	public String toString() {
		String s = "";

		Node<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}

}
