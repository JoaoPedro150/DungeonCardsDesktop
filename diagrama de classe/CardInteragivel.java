
/**
 * Classe abstrata CardInteragivel - Representa todos os card interagiveis do 
 * jogo.
 * 
 */
public abstract class CardInteragivel
{
    /**
     * Método interagir - Representa a interação entre um card de ataque 
     * (card que causa dano) e o objeto atual.
     *
     * @param card que irá interagir com o objeto atual.
     * @return um card, caso o objeto atual seja um card mutavel (card que 
     * se transforma em outro quando morre), ou null caso contrário.
     */
    public abstract Card receberAtaque(CardDeAtaque card);
    /**
     * Método interagir - Representa a interação entre o heroi e o objeto
     * atual.
     *
     * @param card que irá interagir com o objeto atual.
     * @return um card, caso o objeto atual seja um card mutavel (card que 
     * se transforma em outro quando morre), ou null caso contrário.
     */
    public abstract Card interagir(Heroi heroi);
}
