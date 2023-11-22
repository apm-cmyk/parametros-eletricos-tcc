package gui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import utilitarios.CalculoGeometrico;
import utilitarios.Complexo;
import utilitarios.ComponentesSimetricos;
import utilitarios.MatrizParametro;

public class NewProjectController implements Initializable {
	

	
	@FXML
	private Button btReturnMain;
	@FXML
	private Button btGravar;
	@FXML
	private Button btCalcular;
	@FXML
    private LineChart<Number, Number> grafico;
    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;
	
	@FXML
	private TextField tfResistenciaFase;
	@FXML
	private TextField tfResistenciaParaRaio;
	@FXML
	private TextField tfResistividadeSolo;
	@FXML
	private TextField tfFrequencia;
	@FXML
	private TextField tfFlechaFase;
	@FXML
	private TextField tfFlechaParaRaio;
	@FXML
	private TextField tfRmgMagnetico;
	@FXML
	private TextField tfRmgEletrostatico;
	@FXML
	private TextField tfRmgParaRaio;
	@FXML
	private TextField tfFaseAx;
	@FXML
	private TextField tfFaseAy;
	@FXML
	private TextField tfFaseBx;
	@FXML
	private TextField tfFaseBy;
	@FXML
	private TextField tfFaseCx;
	@FXML
	private TextField tfFaseCy;
	@FXML
	private TextField tfFaseEx;
	@FXML
	private TextField tfFaseEy;
	@FXML
	private TextField tfFaseFx;
	@FXML
	private TextField tfFaseFy;
	@FXML
	private TextField tfFaseDx;
	@FXML
	private TextField tfFaseDy;
	@FXML
	private Label labelFaseF;
	@FXML
	private Label labelFaseE;
	@FXML
	private Label labelFaseD;
	
	
	@FXML
	private TextField tfParaRaioRX;
	@FXML
	private TextField tfParaRaioRY;
	@FXML
	private TextField tfParaRaioSX;
	@FXML
	private TextField tfParaRaioSY;
	@FXML
	private Label labelParaRaioR;
	@FXML
	private Label labelParaRaioS;
	
	@FXML
	private TextArea tfConsole;
	@FXML
	private CheckBox chbTransposicao;
	
	@FXML
	private CheckBox chbEfeitoSolo;
	
	@FXML
	private Pane torrePane;
	
	//Combox seleciona número de circuito
	@FXML
	private ComboBox<String> cbNumCircuito;
	@FXML
	private ObservableList<String> obsList1;
	
	
	//Combobox seleciona tipo para-raio
	@FXML
	private ComboBox<String> cbTipoParaRaio;
		
	@FXML
	private ObservableList<String> obsList2;
	
	
	//Combox número de para-raio
	@FXML
	private ComboBox<String> cbNumParaRaio;
	@FXML
	private ObservableList<String> obsList3;

	
	
	//desenho dos cabos
	
	@FXML
	private Stage stageCabos = new Stage();
	
	@FXML
	private Scene cena;
	
	//outras variaveis 
	private CalculoGeometrico calculo;
	
	private MatrizParametro matriz;
	
	private Scale zoom;
	
	/*Considerando como variavel local*/
	protected int numFase = 3;
	
	protected int numParaRaio = 1;
	
	protected Double resistenciaFase;
	protected Double resistenciaParaRaio;
	protected Double resistividadeSolo;
	protected Double frequencia; 
	protected static Double flechaFase; 
	protected Double flechaParaRaio;
	protected Double rmgMagnetico; 
	protected Double rmgEletrostatico;
	protected Double rmgParaRaio; 
	protected Boolean transposicao;
	protected Boolean efeitoSolo;
	
	protected String selectNumCircuito;
	protected String selectNumParaRaio;
	protected String selectTipoParaRaio;
	
	protected Double faseAx;
	protected Double faseBx;
	protected Double faseCx;
	
    private StackPane containerZoom;

	
	double[] distanciaFaseX = new double[numFase];
	
	protected Double faseAy;
	protected Double faseBy;
	protected Double faseCy;
	
	double[] distanciaFaseY = new double[numFase];
	
	Double paraRaioX;
	Double paraRaioY;
	
	double[] distanciaParaRaioX = new double[numParaRaio];
	double[] distanciaParaRaioY = new double[numParaRaio];
	
	
	protected Double faseDx;
	protected Double faseEx;
	protected Double faseFx;
	
	double[] distanciaFaseX2 = new double[numFase];
	
	private static final double ZOOM_FACTOR = 1.1; // Fator de zoom
	
	protected Double faseDy;
	protected Double faseEy;
	protected Double faseFy;
	
	double[] distanciaFaseY2 = new double[numFase];
	
	Double paraRaioXS;
	Double paraRaioYS;
	
	
	double[] distanciaParaRaioXS = new double[numParaRaio];
	double[] distanciaParaRaioYS = new double[numParaRaio];

	
	//-------------------Métodos-----------------------//
	

	@FXML
	public void textConsole() {
		
		
		tfConsole.setText("--------------------------Parâmetro Longitudinal--------------------------"
		
				+"\nMatriz Zfase\n"
				+Complexo.toString(matriz.getMatrizZ())
				+"\nMatriz Zpara-raio e fase\n"
				+Complexo.toString(matriz.getMatrizRf1())
				+"\nMatriz Zfase e para-raio\n"
				+Complexo.toString(matriz.getMatrizFr1())
				+"\nMatriz Zpara-raio\n"
				+Complexo.toString(matriz.getMatrizRr1())
				+"\nMatriz Req (Redução de Kron)\n"
				+matriz.getR()
				+"\nMatriz Rseq (Componentes Simétricos)\n"
				+matriz.getCs1()
				+"\nMatriz Xleq (Redução de Kron)\n"
				+matriz.getXl()
				+"\nMatriz Xlseq (Componentes Simétricos)\n"
				+matriz.getCs2()
				+"\n"
				+"--------------------------Parâmetro Em Derivação--------------------------"
				+"\nMatriz Yfase\n"
				+Complexo.toString(matriz.getMatrizY())
				+"\nMatriz Ypara-raio e fase\n"
				+Complexo.toString(matriz.getMatrizRf2())
				+"\nMatriz Yfase e para-raio\n"
				+Complexo.toString(matriz.getMatrizFr2())
				+"\nMatriz Ypara-raio\n"
				+Complexo.toString(matriz.getMatrizRr2())
				+"\nMatriz Xceq (Redução de Kron)\n"
				+matriz.getXc()
				+"\nMatriz Xcseq (Componentes Simétricos)\n"
				+matriz.getCs3()
				);
	
	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void graphicDesign() {
		
	grafico.getData().clear();	
	ComponentesSimetricos m1 = matriz.getCs1();
	Complexo[][] valor1 = m1.getMatrizSimetrica();
		
		
	//elementos XL seq
	ComponentesSimetricos m2 = matriz.getCs2();
	Complexo[][] valor2 = m2.getMatrizSimetrica();
	
	//elementos Xc seq
	ComponentesSimetricos m3 = matriz.getCs3();
	Complexo[][] valor3 = m3.getMatrizSimetrica();
		
	//valor sem frequencia
	
	double v1 = valor2[0][0].getReal()/frequencia;
	double v2 = valor3[0][0].getReal()/frequencia;
	double v3 = valor2[2][2].getReal()/frequencia;
	double v4 = valor3[2][2].getReal()/frequencia;		
	
	Complexo zpos = new Complexo();
	zpos.setReal(valor1[0][0].getReal());
	
	Complexo cpos = new Complexo();
	cpos.setReal(0);
	
	Complexo z0 = new Complexo();
	z0.setReal(valor1[2][2].getReal());
	
	Complexo c0 = new Complexo();
	c0.setReal(0);
	
		
		 xAxis.setLabel("Frequência (Hz)");
		 yAxis.setLabel("| Zc | (ohm/km)");

		
		 grafico.setTitle("Gráfico Impedância Característica Absoluta Zc (ohm/km)");
		
		
		XYChart.Series<Number, Number> impedanciaZero = new XYChart.Series<>();
		XYChart.Series<Number, Number> impedanciaPos = new XYChart.Series<>();


		
		impedanciaZero.setName("|Zc|seq0 (ohm/km)");
		impedanciaPos.setName("|Zc|seq+ (ohm/km)");

	
		
		for (int i=2; i<2000; i+=1) {
	
			zpos.setImag(v1*i);
			cpos.setImag(v2*i);
			z0.setImag(v3*i);
			c0.setImag(v4*i);
	
			Complexo a = zpos.div(cpos);
			Complexo b = z0.div(c0);
			Complexo zPos;
			Complexo zZero;
			zPos = a.sqrt();
			zZero = b.sqrt();
			
			double parteRealPos = zPos.getReal();
			double parteImagPos = zPos.getImag();
			double parteRealZero = zZero.getReal();
			double parteImagZero = zZero.getImag();
				
			
			impedanciaPos.getData().add(new XYChart.Data<>(i,Math.sqrt(parteRealPos*parteRealPos+(parteImagPos)*(parteImagPos))));			
			impedanciaZero.getData().add(new XYChart.Data<>(i,Math.sqrt(parteRealZero*parteRealZero+(parteImagZero)*(parteImagZero))));

			
		}
		
        grafico.setCreateSymbols(false);
        grafico.getData().addAll(impedanciaPos,impedanciaZero);

        
        
        
	}
	
	
	@FXML
	public void towerDesign() {
		
		//Criação do Painel da torre
		torrePane = new Pane();
		
		
		zoom = new Scale(1, 1);
        //torrePane.getTransforms().add(zoom);
		
        
        	//criação das circle
			Circle faseA = new Circle();
			Circle faseB = new Circle();
			Circle faseC = new Circle();
			Circle faseAImagem = new Circle();
			Circle faseBImagem = new Circle();
			Circle faseCImagem = new Circle();
			Circle ParaRaio = new Circle();
			Circle faseD = new Circle();
			Circle faseE = new Circle();
			Circle faseF = new Circle();
			Circle faseDImagem = new Circle();
			Circle faseEImagem = new Circle();
			Circle faseFImagem = new Circle();
			Circle ParaRaio2 = new Circle();

			
			

			//Zoom nos Circle
			faseA.getTransforms().add(zoom);
			faseB.getTransforms().add(zoom);
			faseC.getTransforms().add(zoom);
			faseD.getTransforms().add(zoom);
			faseE.getTransforms().add(zoom);
			faseF.getTransforms().add(zoom);
			
			
			faseAImagem.getTransforms().add(zoom);
			faseBImagem.getTransforms().add(zoom);
			faseCImagem.getTransforms().add(zoom);
			faseDImagem.getTransforms().add(zoom);
			faseEImagem.getTransforms().add(zoom);
			faseFImagem.getTransforms().add(zoom);
			
			
			ParaRaio.getTransforms().add(zoom);
			ParaRaio2.getTransforms().add(zoom);

			
			//Criação Label
			Label textA = new Label();
			Label textB = new Label();
			Label textC = new Label();
			Label textAImg = new Label();
			Label textBImg = new Label();
			Label textCImg = new Label();
			Label textParaRaio1 = new Label();
			
			
			//Zoom Label
			textA.getTransforms().add(zoom);
			textB.getTransforms().add(zoom);
			textC.getTransforms().add(zoom);
			textAImg.getTransforms().add(zoom);
			textBImg.getTransforms().add(zoom);
			textCImg.getTransforms().add(zoom);
			textParaRaio1.getTransforms().add(zoom);

			
			Line linhaAB = new Line();
			Line linhaAC = new Line();
			Line linhaBC = new Line();
			Line linhaABI = new Line();
			Line linhaACI = new Line();
			Line linhaBCI = new Line();
			Line linhaBAI = new Line();
			Line linhaCAI = new Line();
			Line linhaCBI = new Line();
			Line linhaParaRaioA = new Line();
			Line linhaParaRaioB = new Line();
			Line linhaParaRaioC = new Line();
			Line linhaSolo = new Line();
			
			linhaAB.getTransforms().add(zoom);
			linhaAC.getTransforms().add(zoom);
			linhaBC.getTransforms().add(zoom);
			linhaABI.getTransforms().add(zoom);
			linhaACI.getTransforms().add(zoom);
			linhaBCI.getTransforms().add(zoom);
			linhaBAI.getTransforms().add(zoom);
			linhaCAI.getTransforms().add(zoom);
			linhaCBI.getTransforms().add(zoom);
			linhaParaRaioA.getTransforms().add(zoom);
			linhaParaRaioB.getTransforms().add(zoom);
			linhaParaRaioC.getTransforms().add(zoom);
			
			Integer alturaSolo = 400;
			
			linhaSolo.setStartX(0);
			linhaSolo.setStartY(alturaSolo);
			linhaSolo.setEndX(stageCabos.getMaxWidth());
			linhaSolo.setEndY(alturaSolo);
			linhaSolo.setFill(Color.TRANSPARENT);
			linhaSolo.setStroke(Color.PURPLE);
		
			
			linhaABI.setStartX(faseAx);
			linhaABI.setStartY(alturaSolo-faseAy);
			linhaABI.setEndX(faseBx);
			linhaABI.setEndY(alturaSolo+faseBy);
			linhaABI.setFill(Color.RED);
			linhaABI.setStroke(Color.RED);
			
			linhaACI.setStartX(faseAx);
			linhaACI.setStartY(alturaSolo-faseAy);
			linhaACI.setEndX(faseCx);
			linhaACI.setEndY(alturaSolo+faseCy);
			linhaACI.setFill(Color.RED);
			linhaACI.setStroke(Color.RED);
			
			linhaBCI.setStartX(faseBx);
			linhaBCI.setStartY(alturaSolo-faseBy);
			linhaBCI.setEndX(faseCx);
			linhaBCI.setEndY(alturaSolo+faseCy);
			linhaBCI.setFill(Color.MAGENTA);
			linhaBCI.setStroke(Color.MAGENTA);
			
			linhaBAI.setStartX(faseBx);
			linhaBAI.setStartY(alturaSolo-faseBy);
			linhaBAI.setEndX(faseAx);
			linhaBAI.setEndY(alturaSolo+faseAy);
			linhaBAI.setFill(Color.MAGENTA);
			linhaBAI.setStroke(Color.MAGENTA);
			
			linhaCAI.setStartX(faseCx);
			linhaCAI.setStartY(alturaSolo-faseCy);
			linhaCAI.setEndX(faseAx);
			linhaCAI.setEndY(alturaSolo+faseAy);
			linhaCAI.setFill(Color.BLUE);
			linhaCAI.setStroke(Color.BLUE);
			
			
			linhaCBI.setStartX(faseCx);
			linhaCBI.setStartY(alturaSolo-faseCy);
			linhaCBI.setEndX(faseBx);
			linhaCBI.setEndY(alturaSolo-faseBy);
			linhaCBI.setFill(Color.WHITE);
			linhaCBI.setStroke(Color.WHITE);
			
			
			linhaAB.setStartX(faseAx);
			linhaAB.setStartY(alturaSolo-faseAy);
			linhaAB.setEndX(faseBx);
			linhaAB.setEndY(alturaSolo-faseBy);
			linhaAB.setFill(Color.WHITE);
			linhaAB.setStroke(Color.WHITE);
			
			linhaAC.setStartX(faseAx);
			linhaAC.setStartY(alturaSolo-faseAy);
			linhaAC.setEndX(faseCx);
			linhaAC.setEndY(alturaSolo-faseCy);
			linhaAC.setFill(Color.YELLOW);
			linhaAC.setStroke(Color.YELLOW);

			
			linhaBC.setStartX(faseBx);
			linhaBC.setStartY(alturaSolo+faseBy);
			linhaBC.setEndX(faseCx);
			linhaBC.setEndY(alturaSolo-faseCy);
			linhaBC.setFill(Color.YELLOW);
			linhaBC.setStroke(Color.YELLOW);
			
			faseA.setCenterX(faseAx);
			faseA.setCenterY(alturaSolo-faseAy);
			faseA.setRadius(10);
			faseA.setFill(Color.RED);
			faseA.setStroke(Color.BLACK);
			faseAImagem.setCenterX(faseAx);
			faseAImagem.setCenterY(alturaSolo+faseAy);
			faseAImagem.setRadius(10);
			faseAImagem.setFill(Color.RED);
			faseAImagem.setStroke(Color.BLACK);
			
			faseD.setCenterX(faseDx);
			faseD.setCenterY(alturaSolo-faseDy);
			faseD.setRadius(10);
			faseD.setFill(Color.RED);
			faseA.setStroke(Color.BLACK);
			faseDImagem.setCenterX(faseDx);
			faseDImagem.setCenterY(alturaSolo+faseDy);
			faseDImagem.setRadius(10);
			faseDImagem.setFill(Color.RED);
			faseDImagem.setStroke(Color.BLACK);
			
			faseE.setCenterX(faseEx);
			faseE.setCenterY(alturaSolo-faseEy);
			faseE.setRadius(10);
			faseE.setFill(Color.MAGENTA);
			faseE.setStroke(Color.BLACK);
			faseEImagem.setCenterX(faseEx);
			faseEImagem.setCenterY(alturaSolo+faseEy);
			faseEImagem.setRadius(10);
			faseEImagem.setFill(Color.MAGENTA);
			faseEImagem.setStroke(Color.BLACK);
			
			faseF.setCenterX(faseFx);
			faseF.setCenterY(alturaSolo-faseFy);
			faseF.setRadius(10);
			faseF.setFill(Color.BLUE);
			faseF.setStroke(Color.BLACK);
			faseFImagem.setCenterX(faseFx);
			faseFImagem.setCenterY(alturaSolo+faseFy);
			faseFImagem.setRadius(10);
			faseFImagem.setFill(Color.BLUE);
			faseFImagem.setStroke(Color.BLACK);
			
			
			
			textB.setLayoutX(faseBx-12);
			textB.setLayoutY(alturaSolo-faseBy-30);
			textB.setRotate(0);
			textB.setText("fase B");
			textB.setTextFill(Color.DARKGREEN);
			textB.setBackground(Background.fill(Color.WHITE));
			
			textC.setLayoutX(faseCx-12);
			textC.setLayoutY(alturaSolo-faseCy-30);
			textC.setRotate(0);
			textC.setText("fase C");
			textC.setTextFill(Color.DARKGREEN);
			textC.setBackground(Background.fill(Color.WHITE));	
		
			textA.setLayoutX(faseAx-12);
			textA.setLayoutY(alturaSolo-faseAy);
			textA.setText("fase A");
			textA.setTextFill(Color.DARKGREEN);
			textA.setBackground(Background.fill(Color.WHITE));
			
			textAImg.setLayoutX(faseAx-12);
			textAImg.setLayoutY(alturaSolo+faseAy-30);
			textAImg.setText("ImgA");
			textAImg.setTextFill(Color.DARKGREEN);
			textAImg.setBackground(Background.fill(Color.WHITE));
			
			textBImg.setLayoutX(faseBx-12);
			textBImg.setLayoutY(alturaSolo+faseBy-30);
			textBImg.setText("ImgB");
			textBImg.setTextFill(Color.DARKGREEN);
			textBImg.setBackground(Background.fill(Color.WHITE));
			
			textCImg.setLayoutX(faseCx-12);
			textCImg.setLayoutY(alturaSolo+faseCy-30);
			textCImg.setText("ImgC");
			textCImg.setTextFill(Color.DARKGREEN);
			textCImg.setBackground(Background.fill(Color.WHITE));
			
			
			textParaRaio1.setLayoutX(paraRaioX-12);
			textParaRaio1.setLayoutY(alturaSolo-paraRaioY-30);
			textParaRaio1.setText("PR");
			textParaRaio1.setTextFill(Color.DARKGREEN);
			textParaRaio1.setBackground(Background.fill(Color.WHITE));
			
			
			
			
			
			
			linhaParaRaioB.setStartX(paraRaioX);
			linhaParaRaioB.setStartY(alturaSolo-paraRaioY);
			linhaParaRaioB.setEndX(faseBx);
			linhaParaRaioB.setEndY(alturaSolo-faseBy);
			linhaParaRaioB.setFill(Color.YELLOW);
			linhaParaRaioB.setStroke(Color.YELLOW);
			
			linhaParaRaioC.setStartX(paraRaioX);
			linhaParaRaioC.setStartY(alturaSolo-paraRaioY);
			linhaParaRaioC.setEndX(faseCx);
			linhaParaRaioC.setEndY(alturaSolo-faseCy);
			linhaParaRaioC.setFill(Color.YELLOW);
			linhaParaRaioC.setStroke(Color.YELLOW);

			linhaParaRaioA.setStartX(paraRaioX);
			linhaParaRaioA.setStartY(alturaSolo-paraRaioY);
			linhaParaRaioA.setEndX(faseAx);
			linhaParaRaioA.setEndY(alturaSolo-faseAy);
			linhaParaRaioA.setFill(Color.YELLOW);
			linhaParaRaioA.setStroke(Color.YELLOW);

			
				
			faseB.setCenterX(faseBx);
			faseB.setCenterY(alturaSolo-faseBy);
			faseB.setRadius(10);
			faseB.setFill(Color.MAGENTA);
			faseB.setStroke(Color.BLACK);
			
			faseBImagem.setCenterX(faseBx);
			faseBImagem.setCenterY(alturaSolo+faseBy);
			faseBImagem.setRadius(10);
			faseBImagem.setFill(Color.MAGENTA);
			faseBImagem.setStroke(Color.BLACK);
			
			faseC.setCenterX(faseCx);
			faseC.setCenterY(alturaSolo-faseCy);
			faseC.setRadius(10);
			faseC.setFill(Color.BLUE);
			faseC.setStroke(Color.BLACK);
			
			faseCImagem.setCenterX(faseCx);
			faseCImagem.setCenterY(alturaSolo+faseCy);
			faseCImagem.setRadius(10);
			faseCImagem.setFill(Color.BLUE);
			faseCImagem.setStroke(Color.BLACK);
			
			ParaRaio.setCenterX(paraRaioX);
			ParaRaio.setCenterY(alturaSolo-paraRaioY);
			ParaRaio.setRadius(10);
			ParaRaio.setFill(Color.YELLOW);
			ParaRaio.setStroke(Color.BLACK);
			
			CheckBox checkBox = new CheckBox("fluxo magnético fase e Imagem");
	        checkBox.setLayoutX(50); 
	        checkBox.setLayoutY(50); 
			
			torrePane.getChildren().add(faseA);
			torrePane.getChildren().add(faseB);
			torrePane.getChildren().add(faseC);
			torrePane.getChildren().add(faseD);
			torrePane.getChildren().add(faseE);
			torrePane.getChildren().add(faseF);
			torrePane.getChildren().add(faseDImagem);
			torrePane.getChildren().add(faseEImagem);
			torrePane.getChildren().add(faseFImagem);
			torrePane.getChildren().add(faseAImagem);
			torrePane.getChildren().add(faseBImagem);
			torrePane.getChildren().add(faseCImagem);
			torrePane.getChildren().add(linhaAB);
			torrePane.getChildren().add(linhaAC);
			torrePane.getChildren().add(linhaBC);
			torrePane.getChildren().add(linhaABI);
			torrePane.getChildren().add(linhaACI);
			torrePane.getChildren().add(linhaBCI);
			torrePane.getChildren().add(linhaBAI);
			torrePane.getChildren().add(linhaCAI);
			torrePane.getChildren().add(linhaCBI);
			torrePane.getChildren().add(ParaRaio);
			torrePane.getChildren().add(linhaParaRaioA);
			torrePane.getChildren().add(linhaParaRaioB);
			torrePane.getChildren().add(linhaParaRaioC);
			torrePane.getChildren().add(linhaSolo);
			torrePane.getChildren().add(textA);
			torrePane.getChildren().add(textB);
			torrePane.getChildren().add(textC);
			torrePane.getChildren().add(textAImg);
			torrePane.getChildren().add(textBImg);
			torrePane.getChildren().add(textCImg);
			torrePane.getChildren().add(textParaRaio1);
			torrePane.setBackground(Background.fill(Color.BLACK));
			
		
	        Pane area = new Pane();
	        area.setPrefSize(50, 50); 
	        area.setLayoutX(50); 
	        area.setLayoutY(50);
	        area.getChildren().add(checkBox);

	        containerZoom = new StackPane();
	        containerZoom.getChildren().add(torrePane);

	        VBox root = new VBox();
	        root.getChildren().addAll(torrePane,area);

			
			cena = new Scene(root,320,510);
			cena.setFill(Color.BLACK);

			cena.setOnScroll(event -> {
	            double delta = event.getDeltaY();
	            
	            double zoomPivotX = event.getX();
	            double zoomPivotY = event.getY();
	            
	            if (delta > 0) {
	                // Zoom in (aumenta o fator de escala)
	                zoom.setX(zoom.getX() * ZOOM_FACTOR);
	                zoom.setY(zoom.getY() * ZOOM_FACTOR);
	            } else {
	                // Zoom out (diminui o fator de escala)
	                zoom.setX(zoom.getX() / ZOOM_FACTOR);
	                zoom.setY(zoom.getY() / ZOOM_FACTOR);
	            }
	            
	            
	            zoom.setPivotX(zoomPivotX);
	            zoom.setPivotY(zoomPivotY);
	            
	            containerZoom.getTransforms().clear();
	            containerZoom.getTransforms().add(zoom);
	            
			});
			
			 // Adicionar listener para o CheckBox
	        checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
	        	linhaABI.setVisible(newVal);
	        	linhaACI.setVisible(newVal);
	        	linhaBAI.setVisible(newVal);
	        	linhaBCI.setVisible(newVal);
	        	linhaCAI.setVisible(newVal);
	        	linhaCBI.setVisible(newVal);

	        });
			
			//stageCabos.setResizable(false);
			
			stageCabos.setScene(cena);
			stageCabos.setTitle("Diagrama Condutores e Imagem");
			stageCabos.show();
		
	}
	
	

	@FXML
	public  void getDadosView() {
		
		
	try {
		
		resistenciaFase = Double.parseDouble(tfResistenciaFase.getText());
		resistenciaParaRaio = Double.parseDouble(tfResistenciaParaRaio.getText());
		resistividadeSolo = Double.parseDouble(tfResistividadeSolo.getText());
		frequencia = Double.parseDouble(tfFrequencia.getText());
		flechaFase = Double.parseDouble(tfFlechaFase.getText());
		flechaParaRaio = Double.parseDouble(tfFlechaParaRaio.getText());
		rmgMagnetico = Double.parseDouble(tfRmgMagnetico.getText());
		rmgEletrostatico = Double.parseDouble(tfRmgEletrostatico.getText());
		rmgParaRaio = Double.parseDouble(tfRmgParaRaio.getText());
		transposicao = chbTransposicao.isSelected();
		efeitoSolo = chbEfeitoSolo.isSelected();
		
		faseAx = Double.parseDouble(tfFaseAx.getText());
		faseBx = Double.parseDouble(tfFaseBx.getText());
		faseCx = Double.parseDouble(tfFaseCx.getText());		
		
		distanciaFaseX[0]= faseAx;
		distanciaFaseX[1]= faseBx;
		distanciaFaseX[2]= faseCx;
		
		faseAy = Double.parseDouble(tfFaseAy.getText());
		faseBy = Double.parseDouble(tfFaseBy.getText());
		faseCy = Double.parseDouble(tfFaseCy.getText());
	
		distanciaFaseY[0]= faseAy;
		distanciaFaseY[1]= faseBy;
		distanciaFaseY[2]= faseCy;
		
		//Isso aqui já vai obrigar a selecionar (se não cai no catch)
		selectNumParaRaio = cbNumParaRaio.getValue();
		selectTipoParaRaio = cbTipoParaRaio.getValue();
		selectNumCircuito = cbNumCircuito.getValue();
		
				
			
		if(selectNumParaRaio=="UM") {
		paraRaioX = Double.parseDouble(tfParaRaioRX.getText());
		paraRaioY = Double.parseDouble(tfParaRaioRY.getText());
		distanciaParaRaioX[0]= paraRaioX;
		distanciaParaRaioY[0]= paraRaioY;
		numParaRaio =1;
		
		
		} if(selectNumParaRaio=="DOIS") {
		paraRaioX = Double.parseDouble(tfParaRaioRX.getText());
		paraRaioY = Double.parseDouble(tfParaRaioRY.getText());
		paraRaioXS = Double.parseDouble(tfParaRaioSX.getText());
		paraRaioYS = Double.parseDouble(tfParaRaioSY.getText());
		distanciaParaRaioX[0]= paraRaioX;
		distanciaParaRaioY[0]= paraRaioY;
		distanciaParaRaioX[1]= paraRaioXS;
		distanciaParaRaioY[1]= paraRaioYS;
		numParaRaio =2;
		}
		
		
		
		if(selectNumCircuito=="DUPLO") {
			faseDx = Double.parseDouble(tfFaseDx.getText());
			faseEx = Double.parseDouble(tfFaseEx.getText());
			faseFx = Double.parseDouble(tfFaseFx.getText());		
			
			distanciaFaseX2[0]= faseDx;
			distanciaFaseX2[1]= faseEx;
			distanciaFaseX2[2]= faseFx;
			
			faseDy = Double.parseDouble(tfFaseDy.getText());
			faseEy = Double.parseDouble(tfFaseEy.getText());
			faseFy = Double.parseDouble(tfFaseFy.getText());
		
			distanciaFaseY2[0]= faseDy;
			distanciaFaseY2[1]= faseEy;
			distanciaFaseY2[2]= faseFy;
			
		}
		
		
		@SuppressWarnings("unused")
		boolean a = false;
		a = selectNumParaRaio.isEmpty();
		a = selectNumCircuito.isEmpty();
		a = selectTipoParaRaio.isEmpty();

	
		}catch(Exception e) {
			
			System.out.println(e);
			Alert alerta = new Alert(AlertType.NONE);
			alerta.setAlertType(AlertType.ERROR);
			alerta.setHeaderText("Preencher Campos Vazios! \n");
			alerta.setTitle("Mensagem de Erro - Parametros Elétricos:");
			alerta.show();
			
		}
	
	}
		
	@FXML
	public void onBtReturnMainAction() {
	Main.changeScene("ReturnMain");
	}
	
	
	@FXML
	public void onBtGravar() throws IOException {
	
		try {
			
		String diretorioUsuario = System.getProperty("user.home");

	  
	    SimpleDateFormat formatoDataHora = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataHoraAtual = formatoDataHora.format(new Date());

        String nomeArquivo = "resultado-parametros-lt_" + dataHoraAtual + ".txt";
        String caminhoArquivo = diretorioUsuario + "\\" + nomeArquivo;

	    
		FileWriter arquivo = new FileWriter(caminhoArquivo);
		PrintWriter gravarArquivo = new PrintWriter(arquivo);
		gravarArquivo.println("Matriz Zfase");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizZ()));
		gravarArquivo.println("Matriz Zpara-raio e fase");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizRf1()));
		gravarArquivo.println("Matriz Zfase e para-raio");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizFr1()));
		gravarArquivo.println("Matriz Zpara-raio");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizRr1()));
		gravarArquivo.println("Matriz Req (Redução de Kron)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getR());
		gravarArquivo.println("Matriz Rseq (Componentes Simétricos)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getCs1());
		gravarArquivo.println("Matriz Xleq (Redução de Kron)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getXl());
		gravarArquivo.println("Matriz Xleq (Componentes Simétricos)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getCs2());
		gravarArquivo.println("Matriz Yfase");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizY()));
		gravarArquivo.println("Matriz Ypara-raio e fase");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizRf2()));
		gravarArquivo.println("Matriz Yfase e para-raio");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizFr2()));
		gravarArquivo.println("Matriz Ypara-raio");
		gravarArquivo.println();
		gravarArquivo.println(Complexo.toString(matriz.getMatrizRr2()));
		gravarArquivo.println("Matriz Xceq (Redução de Kron)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getXc());
		gravarArquivo.println("Matriz Xcseq (Componentes Simétricos)");
		gravarArquivo.println();
		gravarArquivo.println(matriz.getCs3());
		arquivo.close();
		Alert alerta = new Alert(AlertType.NONE);
		alerta.setAlertType(AlertType.CONFIRMATION);
		alerta.setHeaderText("Arquivo txt gravado com sucesso!");
		alerta.setTitle("Gravação de arquivo txt");
		alerta.show();
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	
	@FXML
	public void onBtCalcular() throws IOException {
		
		//Selecionar dados da View digitados pelo usuário
		getDadosView();
		
	
		//Calcular distancias 
		calculo = new CalculoGeometrico(numFase,numParaRaio, distanciaFaseX,distanciaFaseY,distanciaParaRaioX, 
				distanciaParaRaioY, resistenciaFase, resistenciaParaRaio,resistividadeSolo,frequencia, flechaFase, 
				flechaParaRaio, transposicao, efeitoSolo,rmgMagnetico, rmgEletrostatico, rmgParaRaio, 
				distanciaFaseX2, distanciaFaseY2);

		//Criar Matriz dos Parâmetros
		setMatriz(new MatrizParametro(calculo, selectNumCircuito, selectNumParaRaio, selectTipoParaRaio));	
		
		//Apresentar resultados na Interface
		graphicDesign();
		towerDesign();
		textConsole();
		
		
		
}
		
	

	@FXML
	public void comboBoxNumParaRaio() {
	
		List<String> listaNumParaRaio = new ArrayList<>();
		listaNumParaRaio.add("ZERO");
		listaNumParaRaio.add("UM");
		listaNumParaRaio.add("DOIS");
		obsList3 = FXCollections.observableArrayList(listaNumParaRaio);
		cbNumParaRaio.setItems(obsList3);
		
		
	}
	
	
	@FXML
	public void comboBoxTipoCircuito() {
	
		List<String> listaNumCircuito = new ArrayList<>();
		listaNumCircuito.add("SIMPLES");
		listaNumCircuito.add("DUPLO");
		obsList1 = FXCollections.observableArrayList(listaNumCircuito);
		cbNumCircuito.setItems(obsList1);
		
		
	}
	
	@FXML
	public void comboBoxNumCircuitoAction() {
		try {
			if (cbNumCircuito.getValue().equals("SIMPLES")) {
				tfFaseEy.setVisible(false);
				tfFaseEx.setVisible(false);
				tfFaseFy.setVisible(false);
				tfFaseFx.setVisible(false);
				tfFaseDy.setVisible(false);
				tfFaseDx.setVisible(false);
				labelFaseE.setVisible(false);
				labelFaseF.setVisible(false);
				labelFaseD.setVisible(false);
					
				
			} if (cbNumCircuito.getValue().equals("DUPLO")) {
				tfFaseEy.setVisible(true);
				tfFaseEx.setVisible(true);
				tfFaseFy.setVisible(true);
				tfFaseFx.setVisible(true);
				tfFaseDy.setVisible(true);
				tfFaseDx.setVisible(true);
				labelFaseE.setVisible(true);
				labelFaseF.setVisible(true);
				labelFaseD.setVisible(true);
				
				
				
			} 
			}catch(java.lang.NullPointerException e) {
			
			}		
	}

	
	@FXML
	public void comboBoxNumParaRaioAction() {
		try {
			if (cbNumParaRaio.getValue().equals("ZERO")) {
				tfParaRaioRX.setVisible(false);
				tfParaRaioRY.setVisible(false);
				tfParaRaioSX.setVisible(false);
				tfParaRaioSY.setVisible(false);
				labelParaRaioR.setVisible(false);
				labelParaRaioS.setVisible(false);				
				
			} if (cbNumParaRaio.getValue().equals("UM")) {
				tfParaRaioRX.setVisible(true);
				tfParaRaioRY.setVisible(true);
				tfParaRaioSX.setVisible(false);
				tfParaRaioSY.setVisible(false);
				labelParaRaioR.setVisible(true);
				labelParaRaioS.setVisible(false);
				
				
				
			} if (cbNumParaRaio.getValue().equals("DOIS")) {
				
				tfParaRaioRX.setVisible(true);
				tfParaRaioRY.setVisible(true);
				tfParaRaioSX.setVisible(true);
				tfParaRaioSY.setVisible(true);
				labelParaRaioR.setVisible(true);
				labelParaRaioS.setVisible(true);	
				
				
			} 
			}catch(java.lang.NullPointerException e) {
			
			}		
	}
	
	
	
	@FXML
	public void comboBoxTipoParaRaio() {
	
		List<String> listaTipoParaRaio = new ArrayList<>();
		listaTipoParaRaio.add("ISOLADO");
		listaTipoParaRaio.add("MULTI ATERRADO");
		obsList2 = FXCollections.observableArrayList(listaTipoParaRaio);
		cbTipoParaRaio.setItems(obsList2);
		
	}
	

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Constraints.setTextFieldDouble(tfFlechaFase);
		Constraints.setTextFieldDouble(tfFlechaParaRaio);
		Constraints.setTextFieldDouble(tfResistenciaFase);
		Constraints.setTextFieldDouble(tfResistenciaParaRaio);
		Constraints.setTextFieldDouble(tfResistividadeSolo);
		Constraints.setTextFieldDouble(tfFrequencia);
		Constraints.setTextFieldDouble(tfRmgMagnetico);
		Constraints.setTextFieldDouble(tfRmgEletrostatico);
		Constraints.setTextFieldDouble(tfRmgParaRaio);
		Constraints.setTextFieldDouble(tfFaseAx);
		Constraints.setTextFieldDouble(tfFaseAy);
		Constraints.setTextFieldDouble(tfFaseBx);
		Constraints.setTextFieldDouble(tfFaseBy);
		Constraints.setTextFieldDouble(tfFaseCx);
		Constraints.setTextFieldDouble(tfFaseCy);
		Constraints.setTextFieldDouble(tfParaRaioRX);
		Constraints.setTextFieldDouble(tfParaRaioRY);
	
		Constraints.setTextFieldMaxLength(tfFlechaFase, 12);
		Constraints.setTextFieldMaxLength(tfFlechaParaRaio, 12);
		Constraints.setTextFieldMaxLength(tfResistenciaFase, 12);
		Constraints.setTextFieldMaxLength(tfResistenciaParaRaio, 12);
		Constraints.setTextFieldMaxLength(tfResistividadeSolo, 12);
		Constraints.setTextFieldMaxLength(tfFrequencia, 12);
		Constraints.setTextFieldMaxLength(tfRmgMagnetico, 12);
		Constraints.setTextFieldMaxLength(tfRmgEletrostatico, 12);
		Constraints.setTextFieldMaxLength(tfRmgParaRaio, 12);
		Constraints.setTextFieldMaxLength(tfFaseAx, 12);
		Constraints.setTextFieldMaxLength(tfFaseAy, 12);
		Constraints.setTextFieldMaxLength(tfFaseBx, 12);
		Constraints.setTextFieldMaxLength(tfFaseBy, 12);
		Constraints.setTextFieldMaxLength(tfFaseCx, 12);
		Constraints.setTextFieldMaxLength(tfFaseCy, 12);
		Constraints.setTextFieldMaxLength(tfFaseBy, 12);
		Constraints.setTextFieldMaxLength(tfParaRaioRX, 12);
		Constraints.setTextFieldMaxLength(tfParaRaioRY, 12);
	
		
	}

	public MatrizParametro getMatriz() {
		return matriz;
	}


	public void setMatriz(MatrizParametro matriz) {
		this.matriz = matriz;
	}

}