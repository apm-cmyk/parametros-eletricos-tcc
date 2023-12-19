package utilitarios;

import gui.NewProjectController;

public class CalculoGeometrico extends NewProjectController {
	

	//Dados Entrada Usuário
	int numFase;
	int numParaRaio;
	protected double [] distanciaFaseX = new double[3];
	protected double [] distanciaFaseY = new double [3];
	protected double [] distanciaParaRaioX; 
	protected double [] distanciaParaRaioY;
	protected double [] distanciaFaseX2;
	protected double [] distanciaFaseY2;
	
	protected static double resistenciaFase;
	protected static double resistenciaParaRaio;
	protected static double resistividadeSolo;
	protected static double frequencia;
	protected double flechaFase;
	protected double flechaParaRaio;
	protected static boolean transposicao;
	protected static boolean efeitoSolo;
	protected double rmgMagnetico;
	protected double rmgEletrostatico;
	protected double rmgParaRaio;
	
	//Dados Calculados Pela Classe
	private	double [] hFase; 
	private	double [] hFase2; 
	private double [] distanciaFaseParaRaio; 
	private double [] distanciaFase2ParaRaio;
	private double [] distanciaFase; 
	private double [] hParaRaio; 
	private double [] distanciaFaseImagem;
	private double [] distanciaFaseImagemParaRaio; 
	private double [] distanciaImagem2ParaRaio;
	private double [] distanciaFase1Imagem2;
	private double [] distanciaFase1Fase2; 
	private double [] distanciaImagemParaRaioRS;
	private double hm;
	private double hmr;
	private double Dm;
	private double Dm2;
	private double Dmi;
	private double Dmi2;
	private double Dmr;
	private double Dms;
	private double Dmr2;
	private double dmr;
	private double dmr2;
	private double Dmrs;
	private double drs;
	private double dms;
	
	
	public CalculoGeometrico(int numFase,int numParaRaio,double [] distanciaFaseX,double [] distanciaFaseY,double [] distanciaParaRaioX, 
			double [] distanciaParaRaioY,double resistenciaFase,double resistenciaParaRaio,
			double resistividadeSolo, double frequencia, double flechaFase, double flechaParaRaio, boolean transposicao, 
			boolean efeitoSolo, double rmgMagnetico, double rmgEletrostatico, double rmgParaRaio, 
			double[] distanciaFaseX2, double[] distanciaFaseY2) {
		
		
		this.distanciaFaseX2 = distanciaFaseX2;
		this.distanciaFaseY2 = distanciaFaseY2;
		this.numFase=numFase;
		this.numParaRaio=numParaRaio;
		this.distanciaFaseX=distanciaFaseX;
		this.distanciaFaseY=distanciaFaseY;
		this.distanciaParaRaioX=distanciaParaRaioX;
		this.distanciaParaRaioY=distanciaParaRaioY;
		this.flechaFase=flechaFase;
		this.flechaParaRaio=flechaParaRaio;
		this.rmgMagnetico =rmgMagnetico;
		this.rmgEletrostatico=rmgEletrostatico;
		this.rmgParaRaio=rmgParaRaio;
		CalculoGeometrico.resistenciaFase=resistenciaFase;
		CalculoGeometrico.resistenciaParaRaio=resistenciaParaRaio;
		CalculoGeometrico.resistividadeSolo =resistividadeSolo;
		CalculoGeometrico.frequencia=frequencia;
		CalculoGeometrico.transposicao =transposicao;
		CalculoGeometrico.efeitoSolo =efeitoSolo;
		
		hFase2 = new double[numFase];
		hFase = new double[numFase];
		distanciaFaseParaRaio = new double[numFase*numParaRaio];
		distanciaFase2ParaRaio = new double[numFase*numParaRaio];
		distanciaFase = new double[numFase*(numFase-1)];
		hParaRaio = new double[numParaRaio];
		distanciaFaseImagem = new double[numFase*(numFase-1)];
		distanciaFaseImagemParaRaio = new double[numFase*numParaRaio];
		distanciaImagem2ParaRaio = new double[numFase*numParaRaio];
		distanciaFase1Imagem2 = new double[numFase*numFase]; 
		distanciaFase1Fase2 = new double[numFase*numFase];
		distanciaImagemParaRaioRS = new double[numParaRaio];
		alturaFases();
		distanciaFase();
		alturaParaRaio();
		alturaMediaFase();
		dmgFases();
		distanciaFaseImagem(); 
		dmgFasesImagem(); 
		
		if (numParaRaio ==1 || numParaRaio ==2) {
			distanciaFaseParaRaio();
			alturaMediaParaRaio();
			dmgFasesParaRaio();	
			distanciaImagemParaRaio();
			dmgImagemParaRaio();
			
		}
		
		if (numParaRaio>1) {
		distanciaParaRaioRS();
		distanciaImagemParaRaioRS();
		dmgImagemParaRaioRS();
	
		}
		
		
		
		//tem fazer isso aqui se for circuito duplo
		if (distanciaFaseX2 != null) {
			alturaFases2();
			
			if (numParaRaio>1)
			System.out.println("entrou aqui");
			distanciaFase2ParaRaio();	
			distanciaImagem2ParaRaio();
			distanciaFase1Imagem2() ;
			distanciaFase1Fase2();
			dmgFasesImagem2();
			dmgFases2();
			dmgFases2ParaRaio();
			dmgImagem2ParaRaio();	
		}
}
	
	
	
	
	
	
	//-------------------------------Métodos------------------ 
	
	
	
	public int getNumFase() {
		return numFase;
	}



	public Double getRmgMagnetico() {
		return rmgMagnetico;
	}


	public static Boolean getTransposicao() {
		return transposicao;
	}



	public Double getRmgEletrostatico() {
		return rmgEletrostatico;
	}


	public static Double getResistividadeSolo() {
		return resistividadeSolo;
	}



	public Double getRmgParaRaio() {
		return rmgParaRaio;
	}



	public int getNumParaRaio() {
		return numParaRaio;
	}



	public double[] getDistanciaFaseX() {
		return distanciaFaseX;
	}



	public double[] getDistanciaFaseY() {
		return distanciaFaseY;
	}



	public double[] getDistanciaParaRaioX() {
		return distanciaParaRaioX;
	}



	public double[] getDistanciaParaRaioY() {
		return distanciaParaRaioY;
	}



	public static Double getResistenciaFase() {
		return resistenciaFase;
	}

	public static Double getResistenciaParaRaio() {
		return resistenciaParaRaio;
	}



	public static Double getFrequencia() {
		return frequencia;
	}


	public Double getFlechaFase() {
		return flechaFase;
	}


	public boolean isTransposicao() {
		return transposicao;
	}
	
	public static Boolean getEfeitoSolo() {
		return efeitoSolo;
	}


	public double[] gethFase() {
		return hFase;
	}
	public double[] gethFase2() {
		return hFase2;
	}


	public double[] getDistanciaFaseParaRaio() {
		return distanciaFaseParaRaio;
	}

	public double[] getDistanciaFase2ParaRaio() {
		return distanciaFase2ParaRaio;
	}

	
	public double[] getDistanciaFaseImagemParaRaio() {
		return distanciaFaseImagemParaRaio;
	}


	public double[] getDistanciaFase() {
		return distanciaFase;
	}



	public double[] gethParaRaio() {
		return hParaRaio;
	}



	public double[] getDistanciaFaseImagem() {
		return distanciaFaseImagem;
	}


	

	public double[] getDistanciaFase1Imagem2() {
		return distanciaFase1Imagem2;
	}

	public double[] getDistanciaFase1Fase2() {
		return distanciaFase1Fase2;
	}


	public double getHm() {
		return hm;
	}

	public double getHmr() {
		return hmr;
	}



	public double getDm() {
		return Dm;
	}


	public double getDmi() {
		return Dmi;
	}


	public double getDm2() {
		return Dm2;
	}


	public double getDmi2() {
		return Dmi2;
	}


	public double getDmgImagemParaRaio() {
		return Dmr;
	}

	public double getDmgImagem2ParaRaio() {
		return Dmr2;
	}
	
	public double getDmgFaseParaRaio() {
		return dmr;
	}
	
	public double getDmgFase2ParaRaio() {
		return dmr2;
	}
	
	public double getDrs() {
		return drs;
	}

	public double getDmrs() {
		return Dmrs;
	}

	public double getDms() {
		return dms;
	}
	
	public double getDms2() {
		return Dms;
	}
	
//------------------------------Metodos--------------------

	
	

	public void alturaFases() {	
		
		//Metodo Testado em 31/08/2023 ok!
		for (int i=0;i<numFase;i++) {
		hFase[i] = distanciaFaseY[i] -0.7*flechaFase; 
		
		}
		
	}
	
	public void alturaFases2() {	
		
		//Metodo Testado em 31/08/2023 ok!
		for (int i=0;i<numFase;i++) {
		hFase2[i] = distanciaFaseY2[i] -0.7*flechaFase; 
		
		}
		
	}
	
	public void alturaParaRaio() {	
		
		//verificar se a flecha do para raio seria a mesma da fase
		//Metodo Testado em 31/08/2023 ok!
		for (int i=0;i<numParaRaio;i++) {
		hParaRaio[i] = distanciaParaRaioY[i] -0.7*flechaParaRaio; 
	
		}
	}
	
	
	public void distanciaFase() {	
		
		int x = 0;
		for (int i=0;i<distanciaFaseX.length;i++) {
			for (int j=0;j<distanciaFaseX.length;j++) {
			if(j!=i) {
			distanciaFase[x] = Math.sqrt(Math.pow(distanciaFaseX[i]-distanciaFaseX[j], 2)+Math.pow(distanciaFaseY[i]-distanciaFaseY[j], 2));	
			x++;
			}
		
			}
			
		}
	}
					
	public void distanciaFaseParaRaio() {
		int x = 0;
		for (int j=0;j<numParaRaio;j++) {
			for (int i=0;i<numFase;i++) {
			distanciaFaseParaRaio[x] = Math.sqrt(Math.pow(distanciaFaseX[i]-distanciaParaRaioX[j], 2)+Math.pow(distanciaFaseY[i]-distanciaParaRaioY[j], 2));	
			x++;
			}
			
		}

	}
	
	public void distanciaFase2ParaRaio() {
		int x = 0;
		for (int j=0;j<numParaRaio;j++) {
			for (int i=0;i<numFase;i++) {
			distanciaFase2ParaRaio[x] = Math.sqrt(Math.pow(distanciaFaseX2[i]-distanciaParaRaioX[j], 2)+Math.pow(distanciaFaseY2[i]-distanciaParaRaioY[j], 2));	
			x++;
			}
			
		}

	}
	
	
	public void distanciaFaseImagem() {	
		
		int x = 0;
		for (int i=0; i<distanciaFaseX.length;i++) {
			for (int j=0; j<distanciaFaseX.length; j++) {
				if (i !=j) {
				distanciaFaseImagem[x] = Math.sqrt(Math.pow(distanciaFaseX[i]-distanciaFaseX[j], 2)+Math.pow(distanciaFaseY[i]+distanciaFaseY[j], 2));
				x++;
				}
			}
		}
		
	}
	
	public void distanciaImagemParaRaio() {

		int x = 0;
		for (int j=0;j<numParaRaio;j++) {
			for (int i=0;i<numFase;i++) {
			
			distanciaFaseImagemParaRaio[x] = Math.sqrt(Math.pow(distanciaParaRaioX[j]-distanciaFaseX[i], 2)+Math.pow(distanciaParaRaioY[j]+distanciaFaseY[i], 2));
			x++;
			
			}
		}
			
	}
	
	
	public void distanciaImagem2ParaRaio() {

		int x = 0;
		for (int j=0;j<numParaRaio;j++) {
			for (int i=0;i<numFase;i++) {
			
			distanciaImagem2ParaRaio[x] = Math.sqrt(Math.pow(distanciaParaRaioX[j]-distanciaFaseX2[i], 2)+Math.pow(distanciaParaRaioY[j]+distanciaFaseY2[i], 2));
			x++;
			
			}
		}
			
	}


	
	public void distanciaFase1Imagem2() {


		int x = 0;
		for (int i=0; i<distanciaFaseX.length;i++) {
			for (int j=0; j<distanciaFaseX.length; j++) {
				
				distanciaFase1Imagem2[x] = Math.sqrt(Math.pow(distanciaFaseX[i]-distanciaFaseX2[j], 2)+Math.pow(distanciaFaseY[i]+distanciaFaseY2[j], 2));
				System.out.println(distanciaFase1Imagem2[x]);
				x++;
				
			}
		}
		
			
	}
	
	public void distanciaFase1Fase2() {


		int x = 0;
		for (int i=0; i<distanciaFaseX.length;i++) {
			for (int j=0; j<distanciaFaseX2.length; j++) {
				distanciaFase1Fase2[x] = Math.sqrt(Math.pow(distanciaFaseX[i]-distanciaFaseX2[j], 2)+Math.pow(distanciaFaseY[i]-distanciaFaseY2[j], 2));
				x++;
				
			}
		}
		
			
	}
	
	public void distanciaParaRaioRS() {
		
		
		drs = Math.sqrt(Math.pow(distanciaParaRaioX[0]-distanciaParaRaioX[1], 2)+Math.pow(distanciaParaRaioY[0]-distanciaParaRaioY[1], 2));
	
	}
	
	public void distanciaImagemParaRaioRS() {
		int j=1;
		

		for (int i=0; i<numParaRaio;i++) {	
		distanciaImagemParaRaioRS[i] = Math.sqrt(Math.pow(distanciaParaRaioX[i]-distanciaParaRaioX[j], 2)+Math.pow(distanciaParaRaioY[i]+distanciaParaRaioY[j], 2));
		System.out.println("distanciaImagemParaRaioRS");

		System.out.println(distanciaImagemParaRaioRS[i]);
		j=0;
		}
		System.out.println("fim");

		
	}
	
	public void alturaMediaFase() {

		double h = 1;
		for(int i=0;i<numFase;i++) {
			h *= hFase[i];
		}
		hm = Math.pow(h,1.0/numFase);	
	
	}
	
	public void alturaMediaParaRaio() {

		double h = 1;
		for(int i=0;i<numParaRaio;i++) {
			h *= hParaRaio[i];
		}
		hmr = Math.pow(h,1.0/numParaRaio);	
	
	}
	
	
	public void dmgFases() {
	
		double distancia = 1;
		for (int i=0;i<distanciaFase.length;i++) {
			distancia *= distanciaFase[i];
		}
		Dm = Math.pow(distancia,1.0/distanciaFase.length);
		
	}
	
	public void dmgFases2() {
		
		double distancia = 1;
		for (int i=0;i<distanciaFase1Fase2.length;i++) {
			distancia *= distanciaFase1Fase2[i];
		}
		Dm2 = Math.pow(distancia,1.0/distanciaFase1Fase2.length);
		
	}
	
	public void dmgFasesImagem() {
	
	//organizado para qualquer numero de fase	
		double distancia = 1;
		for (int i=0;i<distanciaFaseImagem.length;i++) {
			distancia *= distanciaFaseImagem[i];
		}
		Dmi = Math.pow(distancia,1.0/distanciaFaseImagem.length);
		
	}

	public void dmgFasesImagem2() {
		
		//organizado para qualquer numero de fase	
			double distancia = 1;
			for (int i=0;i<distanciaFase1Imagem2.length;i++) {
				distancia *= distanciaFase1Imagem2[i];
			}
			Dmi2 = Math.pow(distancia,1.0/distanciaFase1Imagem2.length);
			
		}
	
	
	public void dmgFasesParaRaio() {
		
		double distancia1 = 1;
		double distancia2 = 1;
		for (int i=0;i<3;i++) {
			distancia1 *= distanciaFaseParaRaio[i];	
		}
		dmr = (Math.pow(distancia1,1.0/3));
		
		if (distanciaFaseParaRaio.length > 3) {
		for (int i=3;i<6;i++) {
			distancia2 *= distanciaFaseParaRaio[i];	
		}
		dms = (Math.pow(distancia2,1.0/3));

		}
	}	
	
	public void dmgFases2ParaRaio() {
		
		double distancia = 1;
		for (int i=0;i<distanciaFase2ParaRaio.length;i++) {
			distancia *= distanciaFase2ParaRaio[i];	
			}
		
		dmr2 = (Math.pow(distancia,1.0/distanciaFase2ParaRaio.length));
		
	}	
	
	public void dmgImagemParaRaio() {
		
		double distancia1 = 1;
		double distancia2 = 1;
		for (int i=0;i<distanciaFaseImagemParaRaio.length/2;i++) {
			distancia1 *= distanciaFaseImagemParaRaio[i];

		}
		Dmr = Math.pow(distancia1,1.0/distanciaFaseImagemParaRaio.length/2);
		
		if(distanciaFaseImagemParaRaio.length>3) {
		for (int i=3;i<distanciaFaseImagemParaRaio.length;i++) {
				distancia2 *= distanciaFaseImagemParaRaio[i];

			}
			Dms = Math.pow(distancia2,1.0/distanciaFaseImagemParaRaio.length/2);
				
		}
		
	}
	
	
	
	public void dmgImagem2ParaRaio() {
		
		double distancia = 1;
		for (int i=0;i<distanciaImagem2ParaRaio.length;i++) {
			distancia *= distanciaImagem2ParaRaio[i];

		}
		Dmr2 = Math.pow(distancia,1.0/distanciaImagem2ParaRaio.length);
		
		
		
	}

	public void dmgImagemParaRaioRS() {
		
		double distancia = 1;
		for (int i=0;i<numParaRaio;i++) {
			distancia *= distanciaImagemParaRaioRS[i];

		}
		Dmrs = Math.pow(distancia,1.0/numParaRaio);
		
		
		
	}
	





	
	
	
}
