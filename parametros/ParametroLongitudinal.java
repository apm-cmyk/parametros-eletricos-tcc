package parametros;


import utilitarios.CalculoGeometrico;
import utilitarios.Complexo;

public class ParametroLongitudinal {
	
	
	private double w;
	private double De;
	
	static private double correcaoSolo = 0.000988;

	public ParametroLongitudinal() {
		
		this.w = 2*Math.PI*CalculoGeometrico.getFrequencia();
		this.De = 658.368*Math.sqrt(CalculoGeometrico.getResistividadeSolo()/CalculoGeometrico.getFrequencia());
		
	}
	
	
	public Complexo calculoLongitudinalProprio(double h, double Ds, double hm) {
		
		
		if ((CalculoGeometrico.getTransposicao()==false)&&(CalculoGeometrico.getEfeitoSolo()==false)) 
			return new Complexo(CalculoGeometrico.getResistenciaFase(),w*0.0002*Math.log(2*h/Ds));
		if ((CalculoGeometrico.getTransposicao()==false)&&(CalculoGeometrico.getEfeitoSolo()==true)) 
			return new Complexo(CalculoGeometrico.getResistenciaFase()+(correcaoSolo*CalculoGeometrico.getFrequencia()),w*0.0002*Math.log(De/Ds));
		if ((CalculoGeometrico.getTransposicao()==true)&&(CalculoGeometrico.getEfeitoSolo()==false)) 	
			return new Complexo(CalculoGeometrico.getResistenciaFase(),w*0.0002*Math.log(2*hm/Ds));
		else
			return new Complexo(CalculoGeometrico.getResistenciaFase()+(correcaoSolo*CalculoGeometrico.getFrequencia()),w*0.0002*Math.log(De/Ds)); //esse é preciso verificar

	}
	
	public Complexo calculoLongitudinalMutuo(double D, double d, double Dmi, double Dm) {
		
		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == false))
			return new Complexo(0,w*0.0002*Math.log(D/d));
		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == true))
			return new Complexo(correcaoSolo*CalculoGeometrico.getFrequencia(),w*0.0002*Math.log(De/d));
		if ((CalculoGeometrico.getTransposicao() == true) && (CalculoGeometrico.getEfeitoSolo() == false))
			return new Complexo(0,w*0.0002*Math.log(Dmi/Dm));	
		else
			return new Complexo(correcaoSolo*CalculoGeometrico.getFrequencia(),w*0.0002*Math.log(De/Dm));	

}

	
	public Complexo calculoLongitudinalProprioParaRaio(double hr, double Dr) {

		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == false))
			return new Complexo(CalculoGeometrico.getResistenciaParaRaio(),w*0.0002*Math.log(2*hr/Dr));
		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == true))
			return new Complexo(CalculoGeometrico.getResistenciaParaRaio()+(correcaoSolo*CalculoGeometrico.getFrequencia()),w*0.0002*Math.log(De/Dr));
		if ((CalculoGeometrico.getTransposicao() == true) && (CalculoGeometrico.getEfeitoSolo() == false))
			return new Complexo(CalculoGeometrico.getResistenciaParaRaio(),w*0.0002*Math.log(2*hr/Dr));
		else
			
			return new Complexo(CalculoGeometrico.getResistenciaParaRaio()+(correcaoSolo*CalculoGeometrico.getFrequencia()),w*0.0002*Math.log(De/Dr));
		
	}
	
	public Complexo calculoLongitudinalMutuoParaRaio(double hr, double dr, double Dmr, double dmr) {

		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == false))
			return new Complexo(0,w*0.0002*Math.log(2*hr/dr));
		if ((CalculoGeometrico.getTransposicao()==false) && (CalculoGeometrico.getEfeitoSolo() == true))
			return new Complexo(correcaoSolo*CalculoGeometrico.getFrequencia(),w*0.0002*Math.log(De/dr));
		if ((CalculoGeometrico.getTransposicao()==true) && (CalculoGeometrico.getEfeitoSolo() == false))
			//O D deveria ser DMG entre para raio e imagem
			return new Complexo(0,w*0.0002*Math.log(Dmr/dmr));

		else
			
			return new Complexo(correcaoSolo*CalculoGeometrico.getFrequencia(),w*0.0002*Math.log(De/dmr)); 
		
	}
	
	
	
	
}
