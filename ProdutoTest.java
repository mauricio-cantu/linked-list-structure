package singlylinkedlist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProdutoTest {

	public static void main(String[] args) {
		Teclado in = new Teclado();
		int op = 0, idProd = 0;
		GerenciadorProduto gp = new GerenciadorProduto();

		while (op != 9) {
			System.out.println("1 – Adicionar produto\r\n" + "2 – Buscar produto\r\n" + "3 – Excluir produto\r\n"
					+ "4 – Listar produtos\r\n" + "5 – Salvar produtos\r\n" + "6 – Abrir produtos\r\n" + "9 – Sair\r\n"
					+ "Informe a opção selecionada:");
			op = in.leInt();

			switch (op) {
			case 1:
				if (gp.adicionarProduto(in.leInt("ID:"), in.leString("Nome:"), in.leDouble("Preço:")))
					System.out.println("--------------------------\nProduto adicionado!\n--------------------------");
				else
					System.out.println("--------------------------\nProduto já existente!\n--------------------------");
				break;
			case 2:
				idProd = in.leInt("Digite o ID do produto a ser buscado:");
				Produto p = gp.buscar(idProd);
				if (p != null)
					System.out.println(p);
				else
					System.out
							.println("--------------------------\nProduto não encontrado!\n--------------------------");
				break;
			case 3:
				idProd = in.leInt("Digite o ID do produto a ser excluido:");
				if (gp.remover(idProd))
					System.out.println("--------------------------\nProduto excluido!\n--------------------------\n");
				else
					System.out.println(
							"--------------------------\nProduto não encontrado!\n--------------------------\n");
				break;
			case 4:
				gp.listar();
				break;
			case 5: {
				String nomeArq = in.leString("Digite o nome do arquivo:");
				if (gp.saveFile(nomeArq))
					System.out.println(
							"--------------------------\nArquivo gerado com sucesso!\n--------------------------\n");
				else
					System.out.println(
							"--------------------------\nFalha ao gerar arquivo!\n--------------------------\n");
				break;
			}
			case 6: {
				String nomeArq = in.leString("Digite o nome do arquivo:");
				if (gp.openFile(nomeArq))
					System.out.println(
							"--------------------------\nArquivo carregado com sucesso!\n--------------------------\n");
				else
					System.out.println(
							"--------------------------\nFalha ao carregar arquivo!\n--------------------------\n");
				break;
			}
			case 9:
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

		}

		System.out.println("Programa finalizado.");
	}

}
