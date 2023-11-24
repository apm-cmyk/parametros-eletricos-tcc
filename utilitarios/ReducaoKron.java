package utilitarios;

public class ReducaoKron {
	
	protected double [][] Aff;
	protected double [][] Afr;
	protected double [][] Arf;
	protected double [][] Arr;
	protected double[][] Aeq;
	protected double [][] matrizMult1;
	protected double [][] matrizMult2;
	protected double[][] matrizSub;
	
	public ReducaoKron() {
		
	}
	
	public ReducaoKron(double [][] Aff, double[][] Arr, double[][] Afr, double[][] Arf) {
		
		this.Aff = new double[Aff.length][Aff[0].length];
		this.Arr = new double[Arr.length][Arr[0].length];
		this.Afr = new double[Afr.length][Afr[0].length];
		this.Arf = new double[Arf.length][Arf[0].length];
		this.matrizMult1 = new double[Afr.length][Arf[0].length];
		this.matrizMult2 = new double[Afr.length][Arf[0].length];
		
		
		
		this.Aff = Aff;
		this.Arr = Arr;
		this.Afr = Afr;
		this.Arf = Arf;
		
		
		this.Aeq = new double[Aff.length][Aff.length];
		this.matrizSub = new double[Aff.length][Aff.length];
				
	}
	
	public void setAeq(double[][] Aeq){
		
		 this.Aeq = Aeq;
	}
	
	
	public double[][] getAeq(){
		
		return Aeq;
	}
	
	public void calcularReducaoKron() {
		
		
		//Calcular inversa Arr
		Double[][] matrizI =new Double[Arr.length][Arr[0].length];
		Double aux;
		int i,j,k;
		double pivote;
		
		//Contruindo a Matriz Identidade
		for (i=0;i<Arr.length;i++) {
			for (j=0; j<Arr[0].length;j++) {
				if (i==j) {
				matrizI[i][j] = 1.0;
				}
				else {
				matrizI[i][j] = 0.0;
				}
				
			}
		}
			
		for (i=0;i<Arr.length;i++) {
			
			pivote = Arr[i][i];
			for (k=0;k<Arr.length;k++) {
				Arr[i][k]=Arr[i][k]/pivote;
				matrizI[i][k]=matrizI[i][k]/pivote;
			}
			for (j=0;j<Arr.length;j++) {
				
				if (i!=j){
					aux = Arr[j][i];
					for (k=0;k<Arr.length;k++) {
						Arr[j][k]=Arr[j][k]-aux*Arr[i][k];
						matrizI[j][k]=matrizI[j][k]-aux*matrizI[i][k];
					}
				}
				
			}
		}
		
		
		
		//Afr vezez Arr inversa
		
		for(i=0;i<Afr.length;i++) {
			for (j=0; j<Arf[0].length;j++) {
				matrizMult1[i][j] =  0.0;
			}
		}
		
		
		int linha = Afr.length; 
		int coluna = matrizI[0].length;
		int n = Afr[0].length; 
		
		
		for(i=0;i<linha;i++) {
			for (j=0; j<coluna;j++) {
				for(k =0; k < n; k++) {
				matrizMult1[i][j] = matrizMult1[i][j]+Afr[i][k]*matrizI[k][j];
				}
			}
		}
		
		
		
	//mult1 vezes Arf
		
		for(i=0;i<Afr.length;i++) {
			for (j=0; j<Arf[0].length;j++) {
				matrizMult2[i][j] =  0.0;
			}
		}
		
		
		linha = Afr.length; 
		coluna = Arf[0].length;
		n = Afr[0].length; 
		
		
		for(i=0;i<linha;i++) {
			for (j=0; j<coluna;j++) {
				for(k =0; k < n; k++) {
				matrizMult2[i][j] = matrizMult2[i][j]+matrizMult1[i][k]*Arf[k][j];
				}
			}
		}
		
		
		//Todas as multiplicacao menos Aff
		
		
		for(i=0;i<Aff.length;i++) {
			for (j=0; j<Aff[0].length;j++) {
				
				matrizSub[i][j] = Aff[i][j]-matrizMult2[i][j];
				
			}
		}
		
		Aeq = matrizSub;
		
		
	}
	
	public String toString() {
		
		String aux;
		aux = "";
		
		for (int i=0;i<Aeq.length;i++) {

			aux = aux+"|";
			for(int j = 0; j<Aeq[0].length;j++) {
				aux = aux +String.format("%.12f",Aeq[i][j])+" ";
			}
			aux = aux +"|";
			aux = aux + "\n";
		}
		
		return aux;
	
		
}
	
	
	
}
