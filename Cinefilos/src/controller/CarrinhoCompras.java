package controller;

import model.Produto;
import model.cinema.ProdutoIngressoCinema;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

public class CarrinhoCompras implements Serializable {

    private static final long serialVersionUID = 1L;


    NivelFidelidade tipoCliente;
    LocalDateTime data = LocalDateTime.now();
    GerenciaSistema gerenciaSistema = new GerenciaSistema();
    GerenciaCinema gerenciaCinema = new GerenciaCinema();
    List<Produto> carrinhoDeCompras = new ArrayList<>();

    public CarrinhoCompras() {
    }



    public CarrinhoCompras(NivelFidelidade nivelFidelidade, LocalDateTime data, GerenciaSistema gerenciaSistema, GerenciaCinema gerenciaCinema, List<Produto> carrinhoDeCompras) {
        this.tipoCliente = nivelFidelidade;
        this.data = data;
        this.gerenciaSistema = gerenciaSistema;
        this.gerenciaCinema = gerenciaCinema;
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public List<Produto> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void adicionarProduto(String codigo, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (codigo.startsWith("C") || codigo.startsWith("c")){
            for (int i = 0; i < quantidade; i++){
                try{
                    Produto produto = GerenciaCinema.getIngressoPorCodigo(codigo);
                    if (produto.getQuantidade() < quantidade){
                        throw new IndexOutOfBoundsException("Não existe ingressos suficientes");
                    }
                    carrinhoDeCompras.add(produto);
                    GerenciaCinema.removeIngressoPorCodigo(codigo); // TODO se o cliente deslogar fazer os itens do carrinho voltar para seus lugares.
                }catch (IllegalArgumentException iae){
                    throw iae;
                }
            }
            for (int i = 0; i < quantidade; i++){
            }

        } else if (codigo.startsWith("L")|| codigo.startsWith("l")) {
            boolean produtoExiste = false;
            for (ProdutoLanchonete produtoLanchonete: GerenciaLanchonete.getProdutosDisponiveis()){
                if (produtoLanchonete.getCodigo().equalsIgnoreCase(codigo)){
                    produtoExiste = true;
                    if (produtoLanchonete.getQuantidade() < quantidade){
                        throw new IndexOutOfBoundsException("Não existe "+ produtoLanchonete.getNome() +" suficientes");
                    }
                    carrinhoDeCompras.add(new ProdutoLanchonete(produtoLanchonete.getNome(),produtoLanchonete.getPreco() * quantidade,1,produtoLanchonete.getCodigo()));
                    GerenciaLanchonete.getProdLanchonetePorCodigo(codigo).setQuantidade(GerenciaLanchonete.getProdLanchonetePorCodigo(codigo).getQuantidade() - quantidade);
                }
            }
            if (!produtoExiste){
                throw new IllegalArgumentException("Produto não existe");
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
        if (carrinhoDeCompras.isEmpty()){
            throw new NoSuchElementException("Carrinho está vazio!");
        }
        this.tipoCliente = ((Cliente) Sistema.getLOGADO()).getProgramaFidelidade().getFidelidade();
        gerenciaSistema.adicionaCompras(this);
        double total = valorComDesconto();
        carrinhoDeCompras.clear();
        return total;
    }

    public double valorComDesconto(){
        double total = valorTotalCarrinhoCompras();
        if (total<= 0){
            throw new IllegalArgumentException("Não há produtos no carrinho!");
        }
        if (total >= tipoCliente.getValorInicialDesconto()){
            return total* tipoCliente.getValorDesconto(); /////////
        }
        return total;
    }

    public void cancelarCompra(){
        for (Produto produto: carrinhoDeCompras){
            if (produto.getCodigo().startsWith("C")){
                for (List<ProdutoIngressoCinema> lista01: GerenciaCinema.getIngressosDoCinema()){
                    for (ProdutoIngressoCinema produtoIngresso: lista01){
                        if (produtoIngresso.getCodigo().equals(produto.getCodigo())){
                            //lista01.add((ProdutoIngressoCinema) produto);
                        }
                    }
                }
            } else if (produto.getCodigo().startsWith("L")) {
                for (ProdutoLanchonete prodLanchonete: GerenciaLanchonete.getProdutosDisponiveis()){
                    if (prodLanchonete.getCodigo().equals(produto.getCodigo())){
                        prodLanchonete.setQuantidade(prodLanchonete.getQuantidade() + produto.getQuantidade());
                    }
                }
            }
        }
        carrinhoDeCompras.clear();
    }

    public Produto buscaPorCodigo(int codigo){
        return null;
    }

    public LocalDateTime getData() {
        return data;
    }

    public NivelFidelidade getTipoCliente() {
        return tipoCliente;
    }
    public GerenciaSistema getGerenciaSistema() {
        return gerenciaSistema;
    }

    public GerenciaCinema getGerenciaCinema() {
        return gerenciaCinema;
    }

    @Override
    public String toString() {
        return "CarrinhoCompras{" +
                "tipoCliente=" + tipoCliente +
                ", data=" + data +
                ", gerenciaSistema=" + gerenciaSistema +
                ", gerenciaCinema=" + gerenciaCinema +
                ", carrinhoDeCompras=" + carrinhoDeCompras +
                " "+carrinhoDeCompras.size()+'}';
    }
}
