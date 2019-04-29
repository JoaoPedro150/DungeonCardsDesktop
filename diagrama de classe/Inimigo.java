public abstract class Inimigo extends CardInterativo
{
    private static int qtdInimigos = 0;
    
    public Inimigo() {
        qtdInimigos++;
    }
    public static void diminuirInimigos() {
        qtdInimigos--;
    }
}
