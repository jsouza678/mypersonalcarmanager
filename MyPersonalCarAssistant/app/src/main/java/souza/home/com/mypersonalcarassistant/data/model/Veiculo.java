package souza.home.com.mypersonalcarassistant.data.model;

public class Veiculo {
    private String nome;
    private String codigo;


    public Veiculo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }
}
