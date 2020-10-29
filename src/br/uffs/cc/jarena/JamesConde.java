package br.uffs.cc.jarena;

public class JamesConde extends Agente {

	int MeuContador;
	int MeuContadorAuxiliar;
	public int alvo_x;
	public int alvo_y;
	public int distanciaX;
	public int distanciaY;
	int THC;
	int THCaux;

	public JamesConde(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		MeuContador = 5;
		MeuContadorAuxiliar = 0;
		setDirecao(BAIXO);
	}

	public void pensa() {
		// if (estou_tomando_dano) { //entra no modo corrida this.modo_fuga = true; }
		movimenta();
		MeuContadorAuxiliar++;

	}

	public void movimenta() {
		// movimenta faz a movimentação do agente;
		if (getY() == 20 && getX() == 0) {
			setDirecao(DIREITA);
		}

		if ((MeuContadorAuxiliar % 4) == 0) { // em teoria, vai fazer com que so seja executado a cada 3 tempos;
			if (MeuContador <= 33) {
				// pega o id de 5 a 33, que é o minimo e o maximo do id do meu agente.
				if (getId() == MeuContador && getX() > 10) {
					setDirecao(BAIXO);
					System.out.println("Estou indo para baixo");
				}
				MeuContador++;
			}
		}

		if (!podeMoverPara(getDirecao())) {
			// Como não conseguimos nos mover, vamos escolher uma direção nova.
			setDirecao(geraDirecaoAleatoria());
			System.out.println("bati numa parede, mudando direcao!");
		}
		/*
		 * if(podeDividir() && getEnergia() >= 900 && getX() > 100 && getY() > 100) {
		 * divide(); setDirecao(geraDirecaoAleatoria()); }
		 */
		if ((MeuContadorAuxiliar % 16) == 0 && isParado()) {
			setDirecao(geraDirecaoAleatoria());
		}
		/*
		 * if (getX() > 600 || getY() > 30) { MeuContador = 5;
		 * 
		 * if (MeuContador <= 33) { if (getId() == MeuContador) { THC = getX(); int
		 * ContAux = MeuContador + 2; if (getId() == ContAux) { THCaux = getX(); } if
		 * ((THC - THCaux) < 25) { setDirecao(geraDirecaoAleatoria());
		 * System.out.println("temo junto"); } }
		 * 
		 * } MeuContador++; }
		 */
	}

	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
		para();
		System.out.println(getX() + "," + getY());
		String ox = Integer.toString(getX());
		String oy = Integer.toString(getY());
		enviaMensagem(ox + "," + oy);

	}

	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).

		if (getEnergia() > energiaRestanteInimigo) {
			para();
			System.out.println("Agente " + getId() + " esta tomando dano.");
		} else {
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " vai fugir da luta.");

		}
	}

	public void MovePara(int x, int y) {

		if (getX() > x) {
			setDirecao(ESQUERDA);
		} else if (getX() < x) {
			setDirecao(DIREITA);
		} else if (getY() > y) {
			setDirecao(CIMA);
		} else if (getY() < y) {
			setDirecao(BAIXO);
		}

	}

	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
		System.out.println("Ganhei essa luta! Da proxima vez tente ser bom, nao seja apenas voce.");
	}

	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.

		String[] Coordenadas = msg.split(",");
		int meuX = Integer.parseInt(Coordenadas[0]);
		int meuY = Integer.parseInt(Coordenadas[1]);

		// System.out.println(meuX);
		// System.out.println(meuY);

		MovePara(meuX, meuY);

	}

	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "James Conde".
		return "James Conde";
	}
}
