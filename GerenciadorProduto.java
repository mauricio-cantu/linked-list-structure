package singlylinkedlist;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GerenciadorProduto {

	private SinglyLinkedList<Produto> produtos = new SinglyLinkedList<>();
	private static final String SEP = "--------------------------";
	private Produto p;

	public boolean adicionarProduto(int id, String nome, double preco) {
		p = new Produto(id, nome, preco);
		if (produtos.search(p) != -1)
			return false;
		produtos.insertLast(p);
		return true;
	}

	public Produto buscar(int idProd) {
		p = new Produto(idProd, null, 0);
		int pos = produtos.search(p);
		return pos == -1 ? null : produtos.get(pos);
	}

	public boolean remover(int idProd) {
		p = new Produto(idProd, null, 0);
		try {
			produtos.remove(produtos.search(p));
			return true;
		} catch (IndexOutOfBoundsException ie) {
			return false;
		}
	}

	public void listar() {
		System.out.println(SEP);
		if (produtos.numElements() == 0)
			System.out.println("Lista de produtos vazia!");
		else
			System.out.println(produtos.toString());
		System.out.println(SEP);
	}

	public boolean saveFile(String nomeArq) {
		try {
			FileOutputStream fos = new FileOutputStream("C:/Users/Mauricio/Documents/" + nomeArq + ".txt");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			for (int i = 0; i < produtos.numElements(); i++) {
				os.writeObject(produtos.get(i));
			}
			os.close();
			return true;
		} catch (IOException ie) {
			return false;
		}
	}

	public boolean openFile(String nomeArq) {
		while (!produtos.isEmpty())
			produtos.removeLast();

		try {
			FileInputStream fis = new FileInputStream("C:/Users/Mauricio/Documents/" + nomeArq + ".txt");
			ObjectInputStream is = new ObjectInputStream(fis);
			Object ob;
			while ((ob = is.readObject()) != null) {
				produtos.insertLast((Produto) ob);
			}
		} catch (EOFException ignore) {
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;

	}

}
