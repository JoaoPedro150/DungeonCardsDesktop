public class Jogo
{
    private static CardInteragivel cards[][];
    private static Heroi heroi;
    private static int pontuacao;
    
    private static void movimento(int y, int x) {
        CardInteragivel card = cards[y][x];
        
        
        cards[y][x].interagir(heroi);
    }
}
