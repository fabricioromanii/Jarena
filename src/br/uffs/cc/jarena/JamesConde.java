package br.uffs.cc.jarena;

public class JamesConde extends Agente {

	public int ContadorAuxiliar;
	public int ContadorDoJogo;
	public int alvo_x;
	public int alvo_y;

	public JamesConde(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		ContadorAuxiliar = 5;
		ContadorDoJogo = 0;
		setDirecao(BAIXO);
	}

	public void pensa() {
		// Aqui dentro de pensa() fica toda a "Inteligencia" do James.
		movimenta();
		ContadorDoJogo++;
	}

	public void movimenta() {
		// Dentro de movimenta() é aonde o James faz toda a sua movimentação;
		// O que essa classe possui poderia ir pra dentro de pensa(), mas separamos pra
		// ficar mais organizado.
		if (ContadorDoJogo == 10) {
			// Ele começa indo para baixo, e depois de 20 tempos comeca a ir para a direita;
			setDirecao(DIREITA);
		}

		if ((ContadorDoJogo % 4) == 0) {
			// aqui, os meus agentes estarao indo para a direita quando entrarem nessa
			// condição.
			// o que ela fará é mandar um por vez para baixo, para que eles se espalhem e
			// dominem o mapa;
			if (ContadorAuxiliar <= 33) {
				if (getId() == ContadorAuxiliar && getX() > 10) {
					setDirecao(BAIXO);
					System.out.println("Estou indo para baixo");
				}
				ContadorAuxiliar++;
			}
		}

		if (!podeMoverPara(getDirecao())) {
			// Como não conseguimos nos mover, vamos escolher uma direção nova.
			setDirecao(geraDirecaoAleatoria());
			System.out.println("bati numa parede, mudando direcao!");
		}

		if (podeDividir() && getEnergia() >= 900 && getX() > 50 && getY() > 50) {
			// se ele pode se dividir, tem energia>900 e ja passou de uma certa parte do
			// mapa, entao ele divide;
			divide();
			setDirecao(geraDirecaoAleatoria());
		}

		if ((ContadorDoJogo % 16) == 0 && isParado()) {
			// Nao vai deixar nenhum agente parado por mais de 16 tempos.
			// Sempre que isso acontecer, essa condição dará a ele uma nova direção;
			setDirecao(geraDirecaoAleatoria());
		}
	}

	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
		// Quando ele recebe energia, para. Para conseguir aproveitar o maximo possivel.
		para();
		System.out.println(getX() + "," + getY());
		String ox = Integer.toString(getX());
		String oy = Integer.toString(getY());
		// Quando recebe energia, ele manda uma mensagens com suas coordenadas aos
		// agentes mais proximos.
		enviaMensagem(ox + "," + oy);
	}

	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).
		// o que essa condição diz é que se ele tem energia suficiente para ganhar entao
		// se manterá parado.
		// Se ele nao tem energia suficiente, vai correr.
		if (getEnergia() > energiaRestanteInimigo) {
			para();
			System.out.println("Agente " + getId() + " esta tomando dano.");
		} else {
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " vai fugir da luta.");
		}
	}

	public void MovePara(int x, int y) {
		// Move para ele recebe como parametro os numeros da coordenada de um agente que
		// esta recebendo energia;
		// o que eu quero fazer é que meus agentes se movam para esse lugar no mapa.
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
		// Essa funcao vai receber do usuario uma mensagem que contem suas coordenadas;
		// é chamada sempre que algum deles recebe energia;
		String[] Coordenadas = msg.split(",");
		int meuX = Integer.parseInt(Coordenadas[0]);
		int meuY = Integer.parseInt(Coordenadas[1]);
		System.out.println("Opa! Ali tem energia, vou mudar de direção!");
		// Depois dessa mensagem ser dividida e convertida para um numero, eu quero que
		// os agentes proximos vao para essa posição.
		// É ai que entra a funcao MovePara();
		MovePara(meuX, meuY);
	}

	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "James Conde".
		return "James Conde";
	}
}
