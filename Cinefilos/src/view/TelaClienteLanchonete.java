package view;

import model.lanchonete.ProdutoLanchonete;

import java.util.List;

public class TelaClienteLanchonete {

  public void exibirProdutos() {
    List<ProdutoLanchonete> produtos = ProdutoLanchonete.getListaProdutos();

    if (produtos.isEmpty()) {
      System.out.println("Não há produtos disponíveis no momento.");
    } else {
      System.out.println("Produtos Disponíveis na Lanchonete:");
      for (ProdutoLanchonete produto : produtos) {
        System.out.println(produto);
      }
    }
  }
}
