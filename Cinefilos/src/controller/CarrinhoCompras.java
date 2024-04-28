package controller;

import model.Produto;
import model.cinema.ProdutoIngressoCinema;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras {
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
            for (ProdutoLanchonete produtoLanchonete: GerenciaLanchonete.getProdutosDisponiveis()){
                if (produtoLanchonete.getCodigo().equalsIgnoreCase(codigo)){
                    carrinhoDeCompras.add(new ProdutoLanchonete(produtoLanchonete.getNome(),produtoLanchonete.getPreco()* produtoLanchonete.getQuantidade(),1,produtoLanchonete.getCodigo()));
                    GerenciaLanchonete.getProdLanchonetePorCodigo(codigo).setQuantidade(GerenciaLanchonete.getProdLanchonetePorCodigo(codigo).getQuantidade() - quantidade);
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
                for (List<ProdutoIngressoCinema> lista01: gerenciaCinema.getIngressosDoCinema()){
                    for (ProdutoIngressoCinema produtoIngresso: lista01){
                        if (produtoIngresso.getCodigo().equals(produto.getCodigo())){
                            lista01.add((ProdutoIngressoCinema) produto);
                        }
                    }
                }
            } else if (produto.getCodigo().startsWith("L")) {
                for (ProdutoLanchonete prodLanchonete: GerenciaLanchonete.getProdutosDisponiveis()){
                    if (prodLanchonete.getCodigo().equals(produto.getCodigo())){
                        GerenciaLanchonete.getProdutosDisponiveis().add(GerenciaLanchonete.getProdLanchonetePorCodigo(produto.getCodigo())); // FIXME ta rodando mas acho que era pra fazer um metodo de add.
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
