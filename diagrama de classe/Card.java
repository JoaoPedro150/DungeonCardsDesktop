/**
 * Classe abstrata Card - Contém os atributos em todos comum dos cards
 */
public abstract class Card
{
    /**
     * Nome do Card
     */
    private String nome;
    
    /**
     * Valor do Card, pode representar qualquer valor necessário ao card.
     */
    private int valor;
    
    /**
     * Uma mensagem informativa sobre o card.
     */
    private String info;
    
    /**
     * Local em disco do som de interação do card.
     */
    private String pathSom;
    
    /**
     * Tipo do card, bom ou ruim (seria melhor um enum)
     */
    private String tipo;
    
    private int x;
    private int y;
    
    public abstract void novaRodada();
}
