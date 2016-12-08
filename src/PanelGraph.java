import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * M1INFO Université du Havre
 * TP RESEAU
 * Transimission en bande de passe
 * @author Florian Alline
 *
 */
public class PanelGraph extends JPanel {

	private final int TLIGNE = 50;
	private int LIGNE;
	private final int COLONE = 200;
	private final int BAS = 300;
	private final int HAUT = 100;

	private String choix;
	private String code;

	public PanelGraph() {

		super();
		choix = "";
		code = "";
	}

	@Override
	public void paintComponent(Graphics g) {

		if (code.length() > 0)
			LIGNE = (int) (TLIGNE / (code.length() * 0.08));
		else
			LIGNE = TLIGNE;

		g.drawString("nV", 10, 100);
		g.drawString("0V", 10, 200);
		g.drawString("-nV", 10, 300);

		if (choix.equals("nrz")) {

			String[] codeB = code.split("");

			int xBase = 50;
			int yBase = 0;

			tracerPointille(xBase, g);

			for (int x = 1; x < codeB.length; x++) {

				if (x == 1) {

					if (codeB[x].equals("0")) {

						yBase = BAS;

						int[] tab = traitHorizontal(xBase, yBase, g);

						xBase = tab[0];

					} else {

						yBase = HAUT;

						int[] tab = traitHorizontal(xBase, yBase, g);

						xBase = tab[0];

					}

				} else {

					if (codeB[x - 1].equals(codeB[x])) {

						int[] tab = traitHorizontal(xBase, yBase, g);
						xBase = tab[0];
					} else {

						if (codeB[x - 1].equals("0")) {

							int tab[] = traitVerticaleMont(xBase, yBase, g);
							yBase = tab[1];

							int tab2[] = traitHorizontal(xBase, yBase, g);
							xBase = tab2[0];
						} else {

							int tab[] = traitVerticaleDesc(xBase, yBase, g);
							yBase = tab[1];

							int tab2[] = traitHorizontal(xBase, yBase, g);
							xBase = tab2[0];
						}

					}
				}

				tracerPointille(xBase, g);
			}
		} else if (choix.equals("manchester")) {

			String[] codeB = code.split("");

			int xBase = 50;
			int yBase = 0;

			tracerPointille(xBase, g);

			for (int x = 1; x < codeB.length; x++) {

				if (x == 1) {

					if (codeB[x].equals("0")) {

						yBase = BAS;

						int[] tab = marcheMont(xBase, yBase, g);

						xBase = tab[0];
						yBase = tab[1];

					} else {

						yBase = HAUT;

						int[] tab = marcheDesc(xBase, yBase, g);

						xBase = tab[0];
						yBase = tab[1];

					}

				} else {

					if (codeB[x - 1].equals(codeB[x])) {

						if (codeB[x - 1].equals("0") && x - 1 != 1) {

							int tab[] = traitVerticaleDesc(xBase, yBase, g);
							yBase = tab[1];

							int[] tab2 = marcheMont(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];
						} else {

							int tab[] = traitVerticaleMont(xBase, yBase, g);
							yBase = tab[1];

							int[] tab2 = marcheDesc(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];
						}
					} else {

						if (codeB[x - 1].equals("0")) {

							int[] tab = marcheDesc(xBase, yBase, g);

							xBase = tab[0];
							yBase = tab[1];
						} else {

							int[] tab = marcheMont(xBase, yBase, g);

							xBase = tab[0];
							yBase = tab[1];
						}

					}
				}
				tracerPointille(xBase, g);
			}

		} else if (choix.equals("manchesterDiff")) {

			String[] codeB = code.split("");

			int xBase = 50;
			int yBase = 0;

			tracerPointille(xBase, g);

			for (int x = 1; x < codeB.length; x++) {

				if (x == 1) {

					if (codeB[x].equals("0")) {

						yBase = BAS;

						int[] tab = marcheMont(xBase, yBase, g);

						xBase = tab[0];
						yBase = tab[1];

					} else {

						yBase = HAUT;

						int[] tab = marcheDesc(xBase, yBase, g);

						xBase = tab[0];
						yBase = tab[1];

					}

				} else {

					if (codeB[x].equals("0")) {

						if (yBase == HAUT) {

							int tab[] = traitVerticaleDesc(xBase, yBase, g);
							yBase = tab[1];

							int[] tab2 = marcheMont(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];
						} else {

							int tab[] = traitVerticaleMont(xBase, yBase, g);
							yBase = tab[1];

							int[] tab2 = marcheDesc(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];

						}
					} else {

						if (yBase == HAUT) {

							int[] tab2 = marcheDesc(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];

						} else {

							int[] tab2 = marcheMont(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];
						}
					}
				}

				tracerPointille(xBase, g);
			}

		} else if (choix.equals("miller")) {

			int cpt = 0;

			String[] codeB = code.split("");

			int xBase = 50;
			int yBase = 0;

			tracerPointille(xBase, g);

			for (int x = 1; x < codeB.length; x++) {

				if (x == 1) {

					yBase = HAUT;

					if (codeB[x].equals("0")) {

						if (x + 1 < codeB.length) {

							if (!codeB[x + 1].equals("0"))
								cpt = 0;
							else
								cpt++;
						}

						int[] tab = traitHorizontal(xBase, yBase, g);
						xBase = tab[0];

					} else {

						int[] tab = marcheDesc(xBase, yBase, g);

						xBase = tab[0];
						yBase = tab[1];
					}

				} else {

					if (codeB[x].equals("0")) {

						cpt++;

						if (cpt == 2) {

							if (yBase == HAUT) {

								int[] tab = traitVerticaleDesc(xBase, yBase, g);
								yBase = tab[1];

								int[] tab2 = traitHorizontal(xBase, yBase, g);
								xBase = tab2[0];

								int[] tab3 = traitVerticaleMont(xBase, yBase, g);
								yBase = tab3[1];
							} else {

								int[] tab = traitVerticaleMont(xBase, yBase, g);
								yBase = tab[1];

								int[] tab2 = traitHorizontal(xBase, yBase, g);
								xBase = tab2[0];

								int[] tab3 = traitVerticaleDesc(xBase, yBase, g);
								yBase = tab3[1];
							}

							cpt = 0;

						} else {

							if (x + 1 < codeB.length)
								if (!codeB[x + 1].equals("0"))
									cpt = 0;

							int[] tab = traitHorizontal(xBase, yBase, g);
							xBase = tab[0];

						}
					} else {

						if (yBase == HAUT) {

							int[] tab2 = marcheDesc(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];

						} else {

							int[] tab2 = marcheMont(xBase, yBase, g);

							xBase = tab2[0];
							yBase = tab2[1];
						}

					}
				}

				tracerPointille(xBase, g);
			}
		}

	}

	private void tracerPointille(int x, Graphics g) {
		int nb = ((BAS - HAUT) / 10) / 2 + 40;
		int yTemp = HAUT - 20;
		while (yTemp < BAS + 50) {
			g.drawLine(x, yTemp, x, yTemp + 10);
			yTemp = yTemp + 20;
		}
	}

	public int[] marcheDesc(int x, int y, Graphics g) {

		int nouvX = x + LIGNE / 2;
		g.drawLine(x, y, nouvX, y);
		int nouvY = y + COLONE;
		g.drawLine(nouvX, y, nouvX, nouvY);
		int nouvX2 = nouvX + LIGNE / 2;
		g.drawLine(nouvX, nouvY, nouvX2, nouvY);

		int[] tab = new int[] { nouvX2, nouvY };

		return tab;
	}

	public int[] marcheMont(int x, int y, Graphics g) {

		int nouvX = x + LIGNE / 2;
		g.drawLine(x, y, nouvX, y);
		int nouvY = y - COLONE;
		g.drawLine(nouvX, y, nouvX, nouvY);
		int nouvX2 = nouvX + LIGNE / 2;
		g.drawLine(nouvX, nouvY, nouvX2, nouvY);

		int[] tab = new int[] { nouvX2, nouvY };

		return tab;
	}

	public int[] traitHorizontal(int x, int y, Graphics g) {

		int nouvX = x + LIGNE;
		g.drawLine(x, y, nouvX, y);
		int[] tab = new int[] { nouvX, y };
		return tab;

	}

	public int[] traitVerticaleDesc(int x, int y, Graphics g) {

		int nouvY = y + COLONE;
		g.drawLine(x, y, x, nouvY);
		int[] tab = new int[] { x, nouvY };
		return tab;
	}

	public int[] traitVerticaleMont(int x, int y, Graphics g) {

		int nouvY = y - COLONE;
		g.drawLine(x, y, x, nouvY);
		int[] tab = new int[] { x, nouvY };
		return tab;
	}

	public void setChoix(String newChoix) {
		choix = newChoix;
	}

	public void setCode(String c) {
		code = c;
	}
}
