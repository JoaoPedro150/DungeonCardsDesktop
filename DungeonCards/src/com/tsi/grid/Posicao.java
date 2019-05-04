package com.tsi.grid;

/**Esta classe é utlizada para representar coordenadas X e Y dentro do grid de jogo. Seja para representar e mover o cursor, 
 * o herói ou que mais for necessitado.
 * @see Grid*/
public class Posicao {
	private int x, y;

	public Posicao(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Posicao() {
		
	}

	
	public int getX() {
		return x;
	}

	public void addX(int incremento) {
		x += incremento;
	}
	
	public void addY(int incremento) {
		y += incremento;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**Compara se as coordenadas x e y da posição representada pelo objeto <i>this</i> com outro objeto <i>Posicao</i>
	 * são iguais. 
	 * @param posicao objeto posição a ser comparado
	 * @return <b>true</b> se x e y são iguais em ambas as posições, <b>false</b> se são diferentes
	 */
	public boolean comparar(Posicao posicao) {
		return (this.x == posicao.x && posicao.y == this.y) ? true : false;
	}
	
	/**Compara se as coordenadas x e y da posição representada pelo objeto <i>this</i> com outro objeto <i>Posicao</i> faz
	 * com as posições sejam <i>adjacentes</i>. Ou seja, se são vizinhas. Em outras palavras, se a maior diferença entre as coordenadas
	 * x e y é 1.
	 * <pre>Exemplos:
	 * 0 [0][1][2]
	 * 1 [0][1][2]
	 * 2 [0][1][2]
	 * 
	 * Considerando a matriz acima, sendo [x, y]:
	 * 	As coordenadas [0, 1] e [2, 2] <b>não</b> são adjacentes.
	 * 	As coordenadas [0, 2] e [1, 2] são adjacentes, vizinhas.
	 * </pre>
	 * @param posicao objeto posição a ser comparado
	 * @return <b>true</b> se x e y são adjacentes, <b>false</b> caso contrário e <b>null</b> se as posições forem iguais.
	 *
	 */
	public Boolean isAdjacente(Posicao posicao) {
		if(comparar(posicao)) return null;
		
		
		int diferencaX = Math.abs(posicao.x - this.x), diferencaY = Math.abs(posicao.y - this.y);
		if(diferencaX == 1 && diferencaY == 1) return false;
		if(diferencaX <= 1)
			if(diferencaY <= 1)
				return true;
		
		return false;	
	}

	@Override
	public String toString() {
		return String.format("[%d][%d]", x, y);
	}
	
	
	
	
	
	
	
	
	
}
