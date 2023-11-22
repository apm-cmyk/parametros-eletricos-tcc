package utilitarios;



public class ComponentesSimetricos {

	protected Complexo [][] matrizTransformacao;
	protected Complexo [][] matrizTransformacaoInversa;
	protected Complexo [][] Aeq;
	protected Complexo [][] Acs;
	protected Complexo [][] mult;
	
	public ComponentesSimetricos(double[][] ds) {
		
		this.Aeq = new Complexo[ds.length][ds[0].length];
		
		
		//inicializando os objetos
		for(int i=0; i<ds.length;i++) {
			for(int j=0; j<ds[0].length; j++) {
				
				this.Aeq[i][j] = new Complexo();
				
			}
		}
		
		
		for(int i=0; i<ds.length;i++) {
			for(int j=0; j<ds[0].length; j++) {
				
				this.Aeq[i][j].setReal(ds[i][j]);
				this.Aeq[i][j].setImag(0.0);
			}
		}
		
		matrizTransformacao = new Complexo[Aeq.length][Aeq[0].length];
		
		//inicializando os objetos
				for(int i=0; i<Aeq.length;i++) {
					for(int j=0; j<Aeq[0].length; j++) {
						
						this.matrizTransformacao[i][j] = new Complexo();
						
					}
				}
		
		
		matrizTransformacaoInversa = new Complexo[Aeq.length][Aeq[0].length];
				
				//inicializando os objetos
			for(int i=0; i<Aeq.length;i++) {
				for(int j=0; j<Aeq[0].length; j++) {
								
				this.matrizTransformacaoInversa[i][j] = new Complexo();
								
				}
			}
						
			
		mult = new Complexo[Aeq.length][Aeq[0].length];
			
			//inicializando os objetos
			for(int i=0; i<Aeq.length;i++) {
				for(int j=0; j<Aeq[0].length; j++) {
					
					this.mult[i][j] = new Complexo();
					
				}
			}	
				
				
		Acs = new Complexo[Aeq.length][Aeq[0].length];
		
		//inicializando os objetos
		for(int i=0; i<Aeq.length;i++) {
			for(int j=0; j<Aeq[0].length; j++) {
				
				this.Acs[i][j] = new Complexo();
				
			}
		}

		
		
		//-0.5 +0.8660254038i ---->>> a = 1/_120°
		//-0.5 -0.8660254038i ---->>> a² = 1/_240°
		
		
		
		matrizTransformacao[0][0].setImag(0.0);
		matrizTransformacao[0][0].setReal(1.0);
		matrizTransformacao[0][1].setImag(0.0);
		matrizTransformacao[0][1].setReal(1.0);
		matrizTransformacao[0][2].setImag(0.0);
		matrizTransformacao[0][2].setReal(1.0);
		
		
		matrizTransformacao[1][0].setImag(0.0);
		matrizTransformacao[1][0].setReal(1.0);
		matrizTransformacao[1][1].setImag(-0.8660254038);
		matrizTransformacao[1][1].setReal(-0.5);
		matrizTransformacao[1][2].setImag(0.8660254038);
		matrizTransformacao[1][2].setReal(-0.5);
		
		matrizTransformacao[2][0].setImag(0.0);
		matrizTransformacao[2][0].setReal(1.0);
		matrizTransformacao[2][1].setImag(0.8660254038);
		matrizTransformacao[2][1].setReal(-0.5);
		matrizTransformacao[2][2].setImag(-0.8660254038);
		matrizTransformacao[2][2].setReal(-0.5);
		
		
		//Matriz Transformação Inversa
		
		
		
		matrizTransformacaoInversa[0][0].setImag(0.0/3.0);
		matrizTransformacaoInversa[0][0].setReal(1.0/3.0);
		matrizTransformacaoInversa[0][1].setImag(0.0/3.0);
		matrizTransformacaoInversa[0][1].setReal(1.0/3.0);
		matrizTransformacaoInversa[0][2].setImag(0.0/3.0);
		matrizTransformacaoInversa[0][2].setReal(1.0/3.0);
		
		
		matrizTransformacaoInversa[1][0].setImag(0.0/3.0);
		matrizTransformacaoInversa[1][0].setReal(1.0/3.0);
		matrizTransformacaoInversa[1][1].setImag(0.8660254038/3.0);
		matrizTransformacaoInversa[1][1].setReal(-0.5/3.0);
		matrizTransformacaoInversa[1][2].setImag(-0.8660254038/3.0);
		matrizTransformacaoInversa[1][2].setReal(-0.5/3.0);
		
		matrizTransformacaoInversa[2][0].setImag(0.0/3.0);
		matrizTransformacaoInversa[2][0].setReal(1.0/3.0);
		matrizTransformacaoInversa[2][1].setImag(-0.8660254038/3.0);
		matrizTransformacaoInversa[2][1].setReal(-0.5/3.0);
		matrizTransformacaoInversa[2][2].setImag(0.8660254038/3.0);
		matrizTransformacaoInversa[2][2].setReal(-0.5/3.0);
		
		
		
		
		
	}
	
	public Complexo[][] getMatrizSimetrica(){
		
		return Acs;
		
	}
	
	
	public void calcularComponetesSimetricos() {
		
			
		int linha = Aeq.length; 
		int coluna = matrizTransformacao[0].length;
		int n = Aeq[0].length; 
			
		
		
		for(int i=0;i<linha;i++) {
			for (int j=0; j<coluna;j++) {
				for(int k =0; k < n; k++) {
				mult[i][j] = mult[i][j].soma(Aeq[i][k].mult(matrizTransformacao[k][j]));
				}
			}
		}
		
		for(int i=0;i<linha;i++) {
			for (int j=0; j<coluna;j++) {
				for(int k =0; k < n; k++) {
				Acs[i][j] = Acs[i][j].soma(matrizTransformacaoInversa[i][k].mult(mult[k][j]));
				}
			}
		}
		
		
		
		
		
			
	}		
		public String toString() {
			

			String aux;
			aux = "";
			
			for (int i=0;i<Acs.length;i++) {
			
				aux = aux+"|";
				for(int j = 0; j<Acs[0].length;j++) {
					
					aux = aux +Acs[i][j]+" ";
				}
				aux = aux +"|";
				aux = aux + "\n";
			}
			
			return aux;
		
		
		
	}
	
	
	
}
