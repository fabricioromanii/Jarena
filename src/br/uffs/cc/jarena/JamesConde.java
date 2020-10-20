package br.uffs.cc.jarena;

public class JamesConde extends Agente
{

	int MeuContador;
	int MeuContadorAuxiliar;
	
    private int alvo_x;
    private int alvo_y;

	public JamesConde(Integer x, Integer y, Integer energia) {
		super(x, y, energia);			
		MeuContador = 5;
		MeuContadorAuxiliar = 0 ;  
		setDirecao(DIREITA);
        this.alvo_x = -1;
        this.alvo_y = -1;
	}
	
	public void pensa() {/*
		if (estou_tomando_dano)
        {            
            //entra no modo corrida
			this.modo_fuga = true;
		}*/
		movimenta();
		MeuContadorAuxiliar++;
	}
	
	public void movimenta(){
	//movimenta faz a movimentação do agente;
		
		if((MeuContadorAuxiliar % 4) == 0){ //em teoria, vai fazer com que so seja executado a cada 3 tempos;
			if(MeuContador <= 33 ){
				//pega o id de 5 a 33, que é o minimo e o maximo do id do meu agente.
				if (getId() == MeuContador && getX()>10){
					setDirecao(BAIXO);
					System.out.println("Estou indo para baixo");
				}
				MeuContador++;
			}
		}

		if(!podeMoverPara(getDirecao())) {
			// Como não conseguimos nos mover, vamos escolher uma direção nova.
			setDirecao(geraDirecaoAleatoria());
			System.out.println("bati numa parede, mudando direcao!");	
		}
		
		if(podeDividir() && getEnergia() >= 900 && getX() > 100 && getY() > 100) {
			divide();
			setDirecao(geraDirecaoAleatoria());
		}

		if((MeuContadorAuxiliar % 17) == 0 && isParado()){
			setDirecao(geraDirecaoAleatoria());
		}
	}
		
	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
			para();
	}
	
	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).

		if(getEnergia() > energiaRestanteInimigo){
			para();	
			System.out.println("Agente "+getId()+ " esta tomando dano.");
			}else{
				setDirecao(geraDirecaoAleatoria());
				System.out.println("Agente "+getId()+ " vai fugir da luta.");

			}

	}
	/*
	public void MovePara(int x, int y){

		this.alvo_x = x;
		this.alvo_y = y;
		
		if(alvo_x > getX()){
			setDirecao(DIREITA);
		}
		else if (alvo_x < getX()){
			setDirecao(ESQUERDA);
		}
		else if (alvo_y > getY()){
			setDirecao(BAIXO);
		}
		else(alvo_y < getY()){
			setDirecao(CIMA);
		}

	}*/

	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
		System.out.println("Ganhei essa luta! Da proxima vez tente ser bom, nao seja apenas voce.");
	}
	
	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
	}
	
	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "James Conde".
		return "James Conde";
	}
}
