public abstract class Inimigo extends CardInteragivel
{
    private static int qtdInimigos = 0;
    
    public Inimigo() {
        qtdInimigos++;
    }
    public static void diminuirInimigos() {
        qtdInimigos--;
    }
}
