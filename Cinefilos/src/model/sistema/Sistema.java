package model.sistema;

import controller.CarrinhoCompras;
import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import controller.GerenciaSistema;
import model.cinema.Filme;
import model.cinema.ProdutoIngressoCinema;
import model.cinema.Sala;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.usuario.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Sistema implements Serializable{

    private static final Logger logger = Logger.getLogger("Sistema");

    public Sistema() {
    }

    private static final long serialVersionUID = 1L;


    static final String INFO_USUARIOS_CRIADOS = "infoUsuarios.dat";
    static final String INFO_TOTAL_COMPRAS = "infoTotalCompras.dat";
    static final String INFO_PRODUTOS_LANCHONETE = "infoProdutoLanchonete.dat";
    static final String INFO_SALAS_CINEMA = "infoSalasCinema.dat";
    static final String INFO_FILMES_CARTAZ = "infoFilmesEmCartaz.dat";
    static final String INFO_INGRESSOS_DISPONIVEIS = "infoIngressosDisponivel.dat";


    public static Usuario LOGADO = null;
    public static boolean statusSistema = true;

    private final static ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    public int adicionaNovoUsuario(Usuario usuario){
        usuariosCadastrados.add(usuario);
        return usuariosCadastrados.indexOf(usuario);
    }

    public static Usuario getLOGADO() {
        return LOGADO;
    }

    public void setLOGADO(Usuario LOGADO) {
        Sistema.LOGADO = LOGADO;
    }

    public static boolean verificaUsuarioExiste(String nome){

        for (Usuario user: usuariosCadastrados){
            if(user.getNome() == null){
                return false;
            }
            if(user.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    public static List<Usuario> getUsuariosCadastrados(){
        return usuariosCadastrados;
    }

    public void setStatusSistema(boolean statusSistema) {
        Sistema.statusSistema = statusSistema;
    }

    public static void salvaUsuarios() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_USUARIOS_CRIADOS)));
            out.writeObject(usuariosCadastrados);  // Salva a lista inteira de usuários cadastrados
            logger.info("Lista de usuários salva com sucesso.");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    public static void importUsuarios() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_USUARIOS_CRIADOS)));
            List<Usuario> usuariosLidos = (List<Usuario>) in.readObject();
            for (Usuario usuarioLido : usuariosLidos) {
                if (!usuariosCadastrados.contains(usuarioLido)) {
                    logger.info("Lista de usuários salva com sucesso.");
                    usuariosCadastrados.add(usuarioLido);
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void salvaTotalCompras() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_TOTAL_COMPRAS)));
            out.writeObject(GerenciaSistema.getTotalCompras());  // Salva a lista inteira de compras
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    public static void importTotalCompras() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_TOTAL_COMPRAS)));
            List<CarrinhoCompras> comprasSalvas = (List<CarrinhoCompras>) in.readObject();
            GerenciaSistema.getTotalCompras().addAll(comprasSalvas);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    public static void salvaProdutosLanchonetes() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_PRODUTOS_LANCHONETE)));
            out.writeObject(GerenciaLanchonete.getProdutosDisponiveis());  // Salva a lista inteira de produtos da lanchonete
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void importProdutosLanchonetes() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_PRODUTOS_LANCHONETE)));
            List<ProdutoLanchonete> produtosSalvos = (List<ProdutoLanchonete>) in.readObject();
            GerenciaLanchonete.getProdutosDisponiveis().addAll(produtosSalvos);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void salvaSalasCinema() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_SALAS_CINEMA)));
            out.writeObject(GerenciaCinema.getSalasCinema());  // Salva a lista inteira de salas de cinema
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void importSalasCinema() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_SALAS_CINEMA)));
            List<Sala> salasSalvas = (List<Sala>) in.readObject();
            GerenciaCinema.getSalasCinema().addAll(salasSalvas);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void salvaFilmesEmCartaz() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_FILMES_CARTAZ)));
            out.writeObject(GerenciaCinema.getFilmesEmCartaz());  // Salva a lista inteira de filmes
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void importFilmesEmCartaz() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_FILMES_CARTAZ)));
            List<Filme> filmesSalvos = (List<Filme>) in.readObject();
            GerenciaCinema.getFilmesEmCartaz().addAll(filmesSalvos);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void salvaIngressosDisponiveis() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(INFO_INGRESSOS_DISPONIVEIS)));
            out.writeObject(GerenciaCinema.getIngressosDoCinema());  // Salva a lista inteira de filmes
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void importIngressoDisponiveis() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(INFO_INGRESSOS_DISPONIVEIS)));
            List<List<ProdutoIngressoCinema>> ingressosSalvos = (List<List<ProdutoIngressoCinema>>) in.readObject();
            GerenciaCinema.getIngressosDoCinema().addAll(ingressosSalvos);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }




}
