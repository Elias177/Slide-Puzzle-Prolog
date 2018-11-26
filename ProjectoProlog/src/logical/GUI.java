package logical;

import javax.swing.*;

import se.sics.jasper.SPException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class GUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicsPanel    graficos;
	private Modelo modelo = new Modelo();
	private String solucion = "";
	private int flag = 0;
	public static int pasos = 0;

	public static int getPasos() {
		return pasos;
	}

	public void setPasos(int pasos) {
		this.pasos = pasos;
	}

	public GUI() {
		JButton newGameButton = new JButton("Juego Nuevo");
		newGameButton.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		JButton findSol = new JButton("Encontrar Solucion");
		findSol.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		findSol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setSolucion(modelo.encontrarSolucion());
				//	graficos.paintComponent(getGraphics());
					setFlag(1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		newGameButton.addActionListener(new NewGameAction());

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(newGameButton);
		controlPanel.add(findSol);

		graficos = new GraphicsPanel();

		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.NORTH);
		this.add(graficos, BorderLayout.CENTER);
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int i) {
		this.flag = i;
	}

	class GraphicsPanel extends JPanel implements MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final int ROWS = 3;
		private static final int COLS = 3;

		private static final int CELL_SIZE = 80; // Pixels
		private Font incrementado;

		public void mouseClicked (MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered (MouseEvent e) {}
		public void mouseExited  (MouseEvent e) {}


		public GraphicsPanel() {
			incrementado = new Font("OCR A Extended", Font.BOLD, CELL_SIZE/2);
			this.setPreferredSize(
					new Dimension(CELL_SIZE * COLS, CELL_SIZE*ROWS));
			this.setBackground(Color.black);
			this.addMouseListener(this);
		}


		public void paintComponent(Graphics g) {
			int x = 0;
			int y = 0;
			super.paintComponent(g);
			for (int r=0; r<ROWS; r++) {
				for (int c=0; c<COLS; c++) {
					x = c * CELL_SIZE;
					y = r * CELL_SIZE;
					String text = modelo.getCara(r, c);
					if (text != null) {
						g.setColor(Color.white);
						g.fillRect(x+27, y+2, CELL_SIZE-1, CELL_SIZE-1);
						g.setColor(Color.black);
						g.setFont(incrementado);
						g.drawString(text, x+50, y+55);
					}



				}
			}
			if(getFlag() != 0){
				int i = 0;
				int cont = 0;
				for (String retval: getSolucion().split(",")) {
					retval = retval.replaceAll("[^0-9]", "");
					for (int r=0; r<ROWS; r++) {
						for (int c=0; c < COLS; c++) {
							if (retval.charAt(i) != '0') {
								x = c * CELL_SIZE;
								y = r * CELL_SIZE;
								g.setColor(Color.green);
								g.fillRect(x+27, y+2, CELL_SIZE-1, CELL_SIZE-1);
								g.setColor(Color.black);
								g.setFont(incrementado);
								g.drawString(String.valueOf(retval.charAt(i)), x+50, y+55);
								
							

							}else if(retval.charAt(i) == '0') {
								x = c * CELL_SIZE;
								y = r * CELL_SIZE;
								g.setColor(Color.black);
								g.fillRect(x+27, y+2, CELL_SIZE-1, CELL_SIZE-1);
								g.setColor(Color.white);
								g.setFont(incrementado);


							}
							if(i!=8)
								i++;
							else
								i=0;
							

						}
						
}
			cont++;
			System.out.println(cont);
			}
				setPasos(cont);
				setFlag(0);
				Dialogo j = new Dialogo();
				j.setVisible(true);
				j.setModal(true);
			}
			
				
			
		}




		public void mousePressed(MouseEvent e) {
			int col = e.getX()/CELL_SIZE;
			int fil = e.getY()/CELL_SIZE;

			if (!modelo.moveFicha(fil, col)) {

				Toolkit.getDefaultToolkit().beep();
			}

			this.repaint();
			if(modelo.ganadoJuego() == true){
				JOptionPane.showMessageDialog(graficos,
						"Haz ganado!",
						"	Victoria!",
						JOptionPane.OK_OPTION);
				modelo.reset();
			}
		}


	}

	public class NewGameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			modelo.reset();
			graficos.repaint();
		}
	}

}