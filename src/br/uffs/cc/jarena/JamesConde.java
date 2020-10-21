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
		/*
		if(podeDividir() && getEnergia() >= 900 && getX() > 100 && getY() > 100) {
			divide();
			setDirecao(geraDirecaoAleatoria());
		}*/

		if((MeuContadorAuxiliar % 16) == 0 && isParado()){
			setDirecao(geraDirecaoAleatoria());
		}
	}
		
	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
			para();
			System.out.println(getX() + "|" + getY());
			(this.getX() +"|" + this.getY());
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
	
	public void MovePara(int x, int y){

		this.alvo_x = x;
		this.alvo_y = y;
		
		if( getX() > alvo_x ){
			setDirecao(ESQUERDA);
		}
		else if ( getX() < alvo_x){
			setDirecao(DIREITA);
		}
		else if ( getY() > alvo_y ) {
			setDirecao(CIMA);
		} 
		else if( getY() < alvo_y){
			setDirecao(BAIXO);
		}
		
	}

	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
		System.out.println("Ganhei essa luta! Da proxima vez tente ser bom, nao seja apenas voce.");
	}
	
	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
		String aa = msg; 
		String Coordenadas[] = new String[2];
		//100|200|3000|Agente02
		Coordenadas = aa.split("|");
		//100 [0]
		//200 [1]
		
		int meuX =Integer.parseInt(Coordenadas[0]);
		int meuY =Integer.parseInt(Coordenadas[1]);
		//100 int 
		//200 int
		MovePara(meuX,meuY);
		
	}
	
	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "James Conde".
		return "James Conde";
	}
}
