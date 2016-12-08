import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * M1INFO Université du Havre
 * TP RESEAU
 * Transimission en bande de passe
 * @author Florian Alline
 *
 */
public class Interface extends JFrame implements ActionListener{
	
	private ButtonGroup gp;
	private JRadioButton nrz;
	private JRadioButton nrzi;
	private JRadioButton manchester;
	private JRadioButton manchesterDiff;
	private JRadioButton miller;
	private JTextField tfBinaire;
	private JButton valider;
	private PanelGraph pG;

	public Interface () {
		
		this.setSize(800, 450);
		this.setTitle("Transmission en bande de passe");
		this.setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gp = new ButtonGroup();
		
		/*NRZ, NRZI, MAnchester, Machester diff, Miller*/
		
		nrz = new JRadioButton("NRZ");
		manchester = new JRadioButton("Manchester");
		manchesterDiff = new JRadioButton("ManchesterDiff");
		miller = new JRadioButton("Miller");
		
		gp.add(nrz);
		gp.add(manchester);
		gp.add(manchesterDiff);
		gp.add(miller);
		
		nrz.setSelected(true);
		
		tfBinaire = new JTextField();
		
		valider = new JButton("Valider");
		valider.addActionListener(this);
		
		JPanel pNord = new JPanel(new GridLayout(1,6));
		pNord.add(nrz);
		pNord.add(manchester);
		pNord.add(manchesterDiff);
		pNord.add(miller);
		pNord.add(tfBinaire);
		pNord.add(valider);
		
		this.add(pNord,BorderLayout.NORTH);
		
		pG = new PanelGraph();
		
		this.add(pG, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == valider) {
			
			if(!tfBinaire.getText().equals("")) {
				
				boolean verif = false;
				
				String[] codeB = tfBinaire.getText().split("");
				
				for (int x = 1; x < codeB.length; x++) {
					
					if(!codeB[x].equals("0") && !codeB[x].equals("1")) verif = true;
				}
				
				
				if(verif) {
					
					tfBinaire.setBorder(BorderFactory.createLineBorder(Color.RED));
					
				}
				else {
					tfBinaire.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					pG.setCode(tfBinaire.getText());
			
					if(nrz.isSelected()) {
						
						pG.setChoix("nrz");
						this.repaint();
					}
					else if (manchester.isSelected()) {
						pG.setChoix("manchester");
						this.repaint();
					}
					else if (manchesterDiff.isSelected()) {
						pG.setChoix("manchesterDiff");
						this.repaint();
					}
					else {
						pG.setChoix("miller");
						this.repaint();
					}
				
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Interface();
	}

}
