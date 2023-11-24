package utilitarios;



import java.io.IOException;

import org.apache.commons.math4.legacy.linear.LUDecomposition;
import org.apache.commons.math4.legacy.linear.MatrixUtils;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.SingularMatrixException;

import parametros.ParametroDerivacao;
import parametros.ParametroLongitudinal;


public class MatrizParametro {

protected Complexo [][] matrizZ;
protected Complexo [][] matrizZ2;
protected Complexo [][] matrizY;

protected double[][] matrizZReal;
protected double [][] matrizYReal;
protected double [][] matrizZImg;
protected double [][] matrizYImg;


protected Complexo [][] matrizRf1;
protected Complexo [][] matrizFr1;
protected Complexo [][] matrizRr1;

protected double [][] matrizRf1Real;
protected double [][] matrizFr1Real;
protected double [][] matrizRr1Real;

protected double [][] matrizRf1Img;
protected double [][] matrizFr1Img;
protected double [][] matrizRr1Img;


protected Complexo [][] matrizRf2;
protected Complexo [][] matrizFr2;
protected Complexo [][] matrizRr2;

protected Complexo [][] matrizRf3;


protected double [][] matrizRf2Real;
protected double [][] matrizFr2Real;
protected double [][] matrizRr2Real;

protected double [][] matrizRf2Img;
protected double [][] matrizFr2Img;
protected double [][] matrizRr2Img;



protected double [][] Aeq1;
protected double [][] Aeq2;
protected double [][] Aeq3;
protected double [][] Aeq;
protected CalculoGeometrico dados;
private ParametroDerivacao parametro1;
private ParametroLongitudinal parametro2;
private ParametroLongitudinal parametro3;
private ReducaoKron r;
private ReducaoKron xl;
private ReducaoKron	xc;
ComponentesSimetricos cs1; 
ComponentesSimetricos cs2;
ComponentesSimetricos cs3;



private double Ds;
private double [] h;
private double hm;
private double [] D;
private double [] D2;
private double [] d;
private double [] d2;
private double [] hr; 
private double [] dr;
private double [] dr2;
private double Dmr;
private double Dms;
private double Dmr2;
private double Dr;
private double [] Dri;

private double dmr;
private double dms;
private double dmr2;


private String selectNumCircuito;
private String selectNumParaRaio;
private String selectTipoParaRaio;

public MatrizParametro(CalculoGeometrico dados, String selectNumCircuito, String selectNumParaRaio, String selectTipoParaRaio) throws IOException {
	
	
	
	this.dados = dados;
	parametro1 = new ParametroDerivacao();
	parametro2 = new ParametroLongitudinal();
	parametro3 = new ParametroLongitudinal();
	
	this.selectNumCircuito = selectNumCircuito;
	this.selectNumParaRaio = selectNumParaRaio;
	this.selectTipoParaRaio = selectTipoParaRaio;
	
	matrizZ = new Complexo [dados.getNumFase()][dados.getNumFase()];
	matrizZ2 = new Complexo [dados.getNumFase()][dados.getNumFase()];
	matrizZReal = new double [dados.getNumFase()][dados.getNumFase()];
	matrizZImg = new double [dados.getNumFase()][dados.getNumFase()];

	matrizY = new Complexo [dados.getNumFase()][dados.getNumFase()];
	matrizYReal = new double [dados.getNumFase()][dados.getNumFase()];
	matrizYImg = new double [dados.getNumFase()][dados.getNumFase()];

	
	
	matrizRf1 = new Complexo [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr1 = new Complexo [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr1 = new Complexo [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	matrizRf1Real = new double [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr1Real = new double [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr1Real = new double [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	matrizRf1Img = new double [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr1Img = new double [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr1Img = new double [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	
	matrizRf2 = new Complexo [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr2 = new Complexo [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr2 = new Complexo [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	
	matrizRf3 = new Complexo [dados.getNumFase()][dados.getNumParaRaio()];

	
	matrizRf2Real = new double [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr2Real = new double [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr2Real = new double [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	matrizRf2Img = new double [dados.getNumFase()][dados.getNumParaRaio()];
	matrizFr2Img = new double [dados.getNumParaRaio()][dados.getNumFase()];
	matrizRr2Img = new double [dados.getNumParaRaio()][dados.getNumParaRaio()];
	
	
	Aeq1 = new double [dados.getNumFase()][dados.getNumFase()];
	Aeq2 = new double [dados.getNumFase()][dados.getNumFase()];
	Aeq3 = new double [dados.getNumFase()][dados.getNumFase()];
	Aeq = new double [dados.getNumFase()][dados.getNumFase()];
	
	
	h = dados.gethFase();
	hm = dados.getHm();
	 D = dados.getDistanciaFaseImagem();
	d = dados.getDistanciaFase();
	hr = dados.gethParaRaio();
	Ds = dados.getRmgMagnetico();
	dr = dados.getDistanciaFaseParaRaio();
	Dmr = dados.getDmgImagemParaRaio();
	Dms = dados.getDms2();
	Dmr2 = dados.getDmgImagem2ParaRaio();

	dmr = dados.getDmgFaseParaRaio();
	dms = dados.getDms();
	dmr2 = dados.getDmgFase2ParaRaio();
	Dr = dados.getRmgParaRaio();
	Dri = dados.getDistanciaFaseImagemParaRaio();
	
	D2 = dados.getDistanciaFase1Imagem2();
	d2 = dados.getDistanciaFase1Fase2();
	dr2 = dados.getDistanciaFase2ParaRaio();
	
	
	//-------------------------Calcular Matrizes-----------------
	
	matrizParametroLongitudinal();
	matrizParametroDerivacao();
	
	
}





public Complexo[][] getMatrizZ() {
		return matrizZ;
}

public Complexo[][] getMatrizY() {
	return matrizY;
}

public Complexo[][] getMatrizRf1() {
	return matrizRf1;
}

public Complexo[][] getMatrizFr1() {
	return matrizFr1;
}

public Complexo[][] getMatrizRr1() {
	return matrizRr1;
}

public Complexo[][] getMatrizRf2() {
	return matrizRf2;
}

public Complexo[][] getMatrizFr2() {
	return matrizFr2;
}

public Complexo[][] getMatrizRr2() {
	return matrizRr2;
}


public String getR() {
return r.toString();
}

public String getXl() {
return xl.toString();
}

public ReducaoKron getXc() {
return xc;
}

public ComponentesSimetricos getCs1() {
return cs1;
}


public ComponentesSimetricos getCs2() {
return cs2;
}

public ComponentesSimetricos getCs3() {
return cs3;
}

//------------------Metodos------------------------------------//



	public void matrizParametroLongitudinal() {
		
		int x = 0;
		for(int i=0;i<dados.getNumFase();i++) {
			for(int j=0;j<dados.getNumFase();j++) {
					
				//Calculo Parametro Longitudinal Proprio
				if(i==j) {
			

					matrizZ[i][j] = parametro2.calculoLongitudinalProprio(h[i],Ds,hm);
					matrizZReal[i][j] = matrizZ[i][j].getReal();
					matrizZImg[i][j] = matrizZ[i][j].getImag();		

				}else if (i!=j) {
					matrizZ[i][j] = parametro2.calculoLongitudinalMutuo(D[x], d[x], dados.getDmi(), dados.getDm());
					matrizZReal[i][j] = matrizZ[i][j].getReal();
					matrizZImg[i][j] = matrizZ[i][j].getImag();
					x++;
				}
				
				
		}	
			
		}
		
		//Calculo Incluindo Parametro Longitudinal Mutuo Caso Circuito Duplo
			if(selectNumCircuito =="DUPLO") {
				int x1=0;
				for (int i1=0; i1<dados.getNumFase(); i1++) {
					for (int j1=0; j1<dados.getNumFase(); j1++) {
				matrizZ2[i1][j1] = parametro2.calculoLongitudinalMutuo(D2[x1],d2[x1],dados.getDm2(), dados.getDm2());
				matrizZ[i1][j1] = matrizZ[i1][j1].soma(matrizZ2[i1][j1]);
				matrizZReal[i1][j1] = matrizZ[i1][j1].getReal();
				matrizZImg[i1][j1] = matrizZ[i1][j1].getImag();			
				x1++;
				}
			}
			
		}
		

		//Para-Raio e Matriz Z fases
		
		for(int i=0;i<dados.getNumFase();i++) {
			for(int j=0;j<dados.getNumParaRaio();j++) {
				
				matrizRf1[i][j] = parametro3.calculoLongitudinalMutuoParaRaio(hr[j], dr[i], Dmr, dmr);
				matrizRf1Real[i][j] = matrizRf1[i][j].getReal();
				matrizRf1Img[i][j] = matrizRf1[i][j].getImag();

			}		
		}
		
		for(int i=0;i<dados.getNumParaRaio();i++) {
			for(int j=0;j<dados.getNumFase();j++) {
				
				matrizFr1[i][j] = parametro3.calculoLongitudinalMutuoParaRaio(hr[i], dr[j], Dmr, dmr);
				matrizFr1Real[i][j] = matrizFr1[i][j].getReal();
				matrizFr1Img[i][j] = matrizFr1[i][j].getImag();

			}
		}
		
		
		for(int i=0;i<dados.getNumParaRaio();i++) {
			for(int j=0;j<dados.getNumParaRaio();j++) {
				
				matrizRr1[i][j] = parametro3.calculoLongitudinalProprioParaRaio(hr[i], Dr);
				matrizRr1Real[i][j] = matrizRr1[i][j].getReal();
				matrizRr1Img[i][j] = matrizRr1[i][j].getImag();

			}
		}
		
		
		
		if (selectNumParaRaio == "UM" && selectNumCircuito =="DUPLO") {
			@SuppressWarnings("unused")
			int x1=0;
			for (int i1=0; i1<dados.getNumFase(); i1++) {
				for (int j1=0; j1<dados.getNumParaRaio(); j1++) {
			matrizRf3[i1][j1] = parametro3.calculoLongitudinalMutuoParaRaio(hr[j1], dr2[i1], Dmr2, dmr2);
			matrizRf1[i1][j1] = matrizRf1[i1][j1].soma(matrizRf3[i1][j1]);
			matrizRf1Real[i1][j1] = matrizRf1[i1][j1].getReal();
			matrizRf1Img[i1][j1] = matrizRf1[i1][j1].getImag();
			x1++;
				}
			}
		}
		
	
		if (selectNumParaRaio == "DOIS" && selectNumCircuito =="SIMPLES") {
			
		for (int i =0; i<dados.getNumParaRaio(); i++) {
			for (int j = 0; j<dados.getNumParaRaio(); j++) {
				
				if (i == j) {
				matrizRr1[i][j] = parametro3.calculoLongitudinalProprioParaRaio(hr[j], dados.getRmgParaRaio());
				
				}else {
				matrizRr1[i][j] = parametro3.calculoLongitudinalMutuoParaRaio(hr[j], dados.getDrs(), dados.getDmrs(), dados.getDrs());
				}
				
		for (int i1=0; i1<dados.getNumFase(); i1++) {
			int i2 = 3;
				matrizFr1[0][i1] = parametro3.calculoLongitudinalMutuoParaRaio(hr[0], dr[i1], Dmr, dmr);
				matrizFr1[1][i1] = parametro3.calculoLongitudinalMutuoParaRaio(1, dr[i2], Dms, dms);
				matrizRf1[i1][0] = parametro3.calculoLongitudinalMutuoParaRaio(hr[0], dr[i1], Dmr, dmr);
				matrizRf1[i1][0] = parametro3.calculoLongitudinalMutuoParaRaio(1, dr[i2], Dms, dms);
				i2++;
		}
						
			}
		}
			
		}
		
		
		if (selectNumParaRaio == "DOIS" && selectNumCircuito =="DUPLO") {
			
			
			
		}

		//Reducao de Kron
		
		
		
		if ((selectNumParaRaio =="ZERO") || (selectTipoParaRaio == "ISOLADO")) {
		
			r = new ReducaoKron();
			xl = new ReducaoKron();
			r.setAeq(matrizZReal);
			xl.setAeq(matrizZImg);
			cs1 = new ComponentesSimetricos(r.getAeq());
			cs2 = new ComponentesSimetricos(xl.getAeq());
			cs1.calcularComponetesSimetricos();
			cs2.calcularComponetesSimetricos();
			
		
		}else {
			r = new ReducaoKron(matrizZReal,matrizRr1Real,matrizRf1Real, matrizFr1Real);
			xl = new ReducaoKron(matrizZImg,matrizRr1Img,matrizRf1Img, matrizFr1Img);
			
			r.calcularReducaoKron();
			xl.calcularReducaoKron();
		
			
			//Componente Simetrico
			cs1 = new ComponentesSimetricos(r.getAeq());
			cs2 = new ComponentesSimetricos(xl.getAeq());

			cs1.calcularComponetesSimetricos();
			cs2.calcularComponetesSimetricos();
			
		}
			
}
	

	public void matrizParametroDerivacao() {
		
		int x = 0;
		for(int i=0;i<dados.getNumFase();i++) {
			for(int j=0;j<dados.getNumFase();j++) {
				
				if(i==j) {
				
					matrizY[i][j] = parametro1.calculoDerivacaoProprio(h[i],hm,dados.getRmgEletrostatico());
					matrizYReal[i][j] = matrizY[i][j].getReal();
					matrizYImg[i][j] = matrizY[i][j].getImag();
					
				}else {
					matrizY[i][j] = parametro1.calculoDerivacaoMutuo(D[x], d[x], dados.getDmi(), dados.getDm());
					matrizYReal[i][j] = matrizY[i][j].getReal();
					matrizYImg[i][j] = matrizY[i][j].getImag();
					x++;
				}
			}
		}
		
		//Para-raio e matriz P
			int x1 = 0;
				for(int i=0;i<dados.getNumFase();i++) {
					for(int j=0;j<dados.getNumParaRaio();j++) {
						
					matrizRf2[i][j] = parametro1.calculoDerivacaoMutuoParaRaio(Dri[x1], dr[x1], Dmr, dmr);
					x1++;
					matrizRf2Real[i][j] = matrizRf2[i][j].getReal();
					matrizRf2Img[i][j] = matrizRf2[i][j].getImag();
					}
					
					
				}
		
	
	
	//Para-raio e matriz P inversa
				
	int x2 = 0;
	for(int i=0;i<dados.getNumParaRaio();i++) {
		for(int j=0;j<dados.getNumFase();j++) {
			
		matrizFr2[i][j] = parametro1.calculoDerivacaoMutuoParaRaio(Dri[x2], dr[x2], Dmr, dmr);
		x2++;
		matrizFr2Real[i][j] = matrizFr2[i][j].getReal();
		matrizFr2Img[i][j] = matrizFr2[i][j].getImag();
		}
			
	}
	
	for(int i=0;i<dados.getNumParaRaio();i++) {
		for(int j=0;j<dados.getNumParaRaio();j++) {
		
		matrizRr2[i][j] = parametro1.calculoDerivacaoProprioParaRaio(hr[j], dados.getHmr(), dados.getRmgEletrostatico());
		matrizRr2Real[i][j] = matrizRr2[i][j].getReal();
		matrizRr2Img[i][j] = matrizRr2[i][j].getImag();
		
		}		
	}


	
	//Reducao de Kron
	if ((selectNumParaRaio =="ZERO") || (selectTipoParaRaio == "ISOLADO")) {
		xc = new ReducaoKron();
		xc.setAeq(matrizYImg);
		
	
	}else {
	
	xc = new ReducaoKron(matrizYImg,matrizRr2Img,matrizRf2Img, matrizFr2Img);
	xc.calcularReducaoKron();
			
	}		
	
	//calcular inversa usando Apache Commons Math
	RealMatrix matriz = MatrixUtils.createRealMatrix(xc.getAeq());
	
	 try {
         // Calcule a matriz inversa
         RealMatrix matrizInversa = new LUDecomposition(matriz).getSolver().getInverse(); 
         matrizInversa.scalarMultiply(2*Math.PI*CalculoGeometrico.getFrequencia());
         double[][] matrizAinversa = matrizInversa.getData();
       
         //Componente Simetrico
         cs3 = new ComponentesSimetricos(matrizAinversa);
         cs3.calcularComponetesSimetricos();
	 
	 
	 } catch (SingularMatrixException e) {
       System.err.println("A matriz não possui inversa.");
     }
			
}}
