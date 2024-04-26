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

    public int adicionarProduto(Produto produto) {
        carrinhoDeCompras.add(produto);
        return carrinhoDeCompras.indexOf(produto);
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



}
