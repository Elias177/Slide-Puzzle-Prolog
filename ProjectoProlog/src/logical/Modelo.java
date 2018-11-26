package logical;


import org.jpl7.Query;
import org.jpl7.Term;



public class Modelo {

	public int getFila(Ficha t){
		return t.fila;
	}

	public int getColu(Ficha t){
		return t.colu;
	}



	private static final int FILAS = 3;
	private static final int COLS = 3;

	private Ficha[][] tablero;  
	private Ficha     fichaVacia;


	public Modelo() {
		tablero = new Ficha[FILAS][COLS];
		reset();               
	}


	String getCara(int fil, int col) {
		return tablero[fil][col].getCara();
	}

	public void reset() {



		for (int r=0; r<FILAS; r++) {
			for (int c=0; c<COLS; c++) {
				tablero[r][c] = new Ficha(r, c, "" + (r*COLS+c+1));
			}
		}





		for (int r=0; r<FILAS; r++) {
			for (int c=0; c<COLS; c++) {
				intercambiarFichas(r, c, (int)(Math.random()*FILAS)
						, (int)(Math.random()*COLS));
			}
		}

		int des = 0;
		des = (int) (Math.random()*2+1);
		if(des == 1){
			fichaVacia = tablero[0][0];
			fichaVacia.setFace(null);
			tablero[0][0].setFace(null); 
			tablero[0][1].setFace("6");
			tablero[0][2].setFace("4");
			tablero[1][0].setFace("2");
			tablero[1][1].setFace("8");
			tablero[1][2].setFace("1");
			tablero[2][0].setFace("7");
			tablero[2][1].setFace("5");
			tablero[2][2].setFace("3");
		}

		if(des == 2){
			tablero[0][0].setFace("1");
			tablero[0][1].setFace("3");
			tablero[0][2].setFace("8");
			tablero[1][0].setFace("2");
			tablero[1][1].setFace("7");
			tablero[1][2].setFace("6");
			tablero[2][0].setFace("5");
			tablero[2][1].setFace("4");
			tablero[2][2].setFace(null);
			fichaVacia = tablero[2][2];
			fichaVacia.setFace(null);
		}
	}


	public boolean moveFicha(int r, int c) {
		return vacioTablero(r, c, -1, 0) || vacioTablero(r, c, 1, 0)
				|| vacioTablero(r, c, 0, -1) || vacioTablero(r, c, 0, 1);
	}


	private boolean vacioTablero(int f, int c, int fAux, int cAux) {
		int fVecino = f + fAux;
		int cVecino = c + cAux;
		if (validaFilaCol(fVecino, cVecino) && tablero[fVecino][cVecino] == fichaVacia) {
			intercambiarFichas(f, c, fVecino, cVecino);
			return true;
		}
		return false;
	}


	public boolean validaFilaCol(int f, int c) {
		return f>=0 && f<FILAS && c>=0 && c<COLS;
	}


	private void intercambiarFichas(int f1, int c1, int f2, int c2) {
		Ficha aux = tablero[f1][c1];
		tablero[f1][c1] = tablero[f2][c2];
		tablero[f2][c2] = aux;
	}


	public boolean ganadoJuego() {
		int g = 0, cont = 1;
		int aux = 0;
		for (int r=0; r<FILAS; r++) {
			for (int c=0; c<COLS; c++) {

				if(getCara(r,c) != null)
					aux = Integer.parseInt(getCara(r,c));

				if(aux == cont)
					g++;
				cont++;

			}
		}

		if(g == 8)
			return true;
		else
			return false;
	}

	public String encontrarSolucion() throws NoSuchMethodException, InterruptedException, Exception{
		Query.hasSolution("use_module(library(jpl))");
		String t1 = "consult('idastar2.pl')";
		System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));
		
		String estado ="[";
        for (int r=0; r<FILAS; r++) {
            for (int c=0; c<COLS; c++) {
            	
            	if(getCara(r,c) == null)
                    estado += '0' + ",";
            	else
            		estado += getCara(r, c) + ",";
            }
        }
        estado = estado.substring(0,estado.length()-1) + ']';
		System.out.println("solutionAux("+ estado +", [1,2,3,4,5,6,7,8,0],S).");
		
		Term solutions = Query.oneSolution("solutionAux("+ estado +",[1,2,3,4,5,6,7,8,0],S).").get("S");
		String str = solutions.toString().replaceAll("[^0-9]", "");
		String sol = java.util.Arrays.toString(str.split("(?<=\\G.........)"));
		System.out.println(sol);
		return sol;
	}

	public void moverDerecha(int r, int c){
		intercambiarFichas(r,c,1,0);
	}

	public void moverIzquierda(int r, int c){}
	public void moverAbajo(int r, int c){}
	public void moverArriba(int r, int c){}


}



class Ficha {
	int fila;     
	int colu;     
	private String cara;  

	public Ficha(int fil, int col, String car) {
		fila = fil;
		colu = col;
		cara = car;
	}


	public void setFace(String newFace) {
		cara = newFace;
	}


	public String getCara() {
		return cara;
	}

	public boolean posicionFinal(int r, int c) {
		return r==fila && c==colu;
	}


}