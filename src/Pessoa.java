public class Pessoa implements Comparable<Pessoa> {
    private final int id;
    private final String nome;
    private final int idade;
    private final double salario;

    public Pessoa(int id, String nome, int idade, double salario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    // Apenas o ID entra na comparação!
    @Override
    public int compareTo(Pessoa outra) {
        return Integer.compare(this.id, outra.id);
    }
}