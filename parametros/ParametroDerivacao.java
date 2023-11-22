package parametros;


import utilitarios.CalculoGeometrico;
import utilitarios.Complexo;

public class ParametroDerivacao {
		

	public ParametroDerivacao(){
		
	}

	public Complexo calculoDerivacaoProprio(double h, double hm, double R) {
		
		
		if (CalculoGeometrico.getTransposicao()==false)
			return new Complexo(0,18000000*Math.log(2*h/R));
		else		
			return new Complexo(0,18000000*Math.log(2*hm/R));
	
	}
	
	public Complexo calculoDerivacaoMutuo(double D, double d, double Dmi, double dm) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		
		if (CalculoGeometrico.getTransposicao()==false)
			return new Complexo(0,18000000*Math.log(D/d));
		else		
			return new Complexo(0,18000000*Math.log(Dmi/dm));
		
	}
	
	
	public Complexo calculoDerivacaoMutuoParaRaio(Double Dri, double dr, double Dmr, double dmr) {
		
		if (CalculoGeometrico.getTransposicao()==false)
			return new Complexo(0,18000000*Math.log(Dri/dr));
		else		
			return new Complexo(0,18000000*Math.log(Dmr/dmr));
	}
	
	
	public Complexo calculoDerivacaoProprioParaRaio(Double hr, double hmr, double R) {
		
		
		if (CalculoGeometrico.getTransposicao()==false)
			return new Complexo(0,18000000*Math.log(2*hr/R));
		else		
			return new Complexo(0,18000000*Math.log(2*hmr/R));
	}
			
	
			
}
