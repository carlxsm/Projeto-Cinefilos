package controller;

import model.Produto;
import model.sistema.Sistema;
import model.sistema.usuario.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras {
    List<Produto> carrinhoDeCompras = new ArrayList<>();

    public List<Produto> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void adicionarProduto(String codigo, int quantidade) {
        if (codigo.startsWith("C") || codigo.startsWith("c")){
            for (int i = 0; i < quantidade; i++){
                try{
                    Produto produto = GerenciaCinema.getIngressoPorCodigo(codigo);
                    carrinhoDeCompras.add(produto);
                    GerenciaCinema.removeIngressoPorCodigo(codigo); // TODO se o cliente deslogar fazer os itens do carrinho voltar para seus lugares.
                }catch (IndexOutOfBoundsException iobe){
                    throw new IllegalArgumentException("Não existe produtos suficientes");
                }
            }
            for (int i = 0; i < quantidade; i++){
            }


        } else if (codigo.startsWith("L")|| codigo.startsWith("l")) {
            for (int i = 0; i < quantidade; i++){
                try{
                    carrinhoDeCompras.add(GerenciaLanchonete.getProdLanchonetePorCodigo(codigo));
                }catch (IndexOutOfBoundsException iobe){
                    throw new IllegalArgumentException("Não existe produtos suficientes");
                }
            }
        }else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }

    public void removeProduto(Produto produto) {
        try{
            carrinhoDeCompras.remove(produto);
        }catch (Exception e){
            throw new IllegalArgumentException("Esse produto não esta no carrinho");
        }
    }

    public void esvaziarCarrinho(){
        carrinhoDeCompras.clear();
    }

    public double valorTotalCarrinhoCompras(){
        double total = 0;
        for (Produto produto: carrinhoDeCompras){
            total+= produto.getPreco();
        }
        return total;
    }

    public double finalizaPedido(){
        double total = valorTotalCarrinhoCompras();
        if (total<= 0){
            throw new IllegalArgumentException("Não há produtos no carrinho!");
        }
        if (total >= ((Cliente)Sistema.getLOGADO()).getProgramaFidelidade().getFidelidade().getValorInicialDesconto()){
            return total* ((Cliente)Sistema.getLOGADO()).getProgramaFidelidade().getFidelidade().getValorDesconto();
        }
        return total;
    }

    public Produto buscaPorCodigo(int codigo){
        return null;
    }


}
