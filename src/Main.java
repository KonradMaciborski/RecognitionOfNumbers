
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class Main extends ApplicationFrame {

	static int epoki = 0;
	static List<Double> listaBledowSieci = new ArrayList<>();

	public Main(String chartTitle) {
		super("Konrad Maciborski s16013 - NAI2");
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Epoki", "Blad Sieci", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < epoki; i++) {
			dataset.addValue(listaBledowSieci.get(i), "", "" + i);
		}
		return dataset;
	}

	static final double alfa = 0.1;

	public static void main(String... args) {

		double[] wektorWag1 = new double[24];
		double[] wektorWag2 = new double[24];
		double[] wektorWag3 = new double[24];

		for (int i = 0; i < 24; i++) {

			wektorWag1[i] = ThreadLocalRandom.current().nextDouble(-4d, 4d);
			wektorWag2[i] = ThreadLocalRandom.current().nextDouble(-4d, 4d);
			wektorWag3[i] = ThreadLocalRandom.current().nextDouble(-4d, 4d);

		}

		System.out.println(Arrays.toString(wektorWag1));
		System.out.println(Arrays.toString(wektorWag2));
		System.out.println(Arrays.toString(wektorWag3));

		double b1 = ThreadLocalRandom.current().nextDouble(-4d, 4d);
		double b2 = ThreadLocalRandom.current().nextDouble(-4d, 4d);
		double b3 = ThreadLocalRandom.current().nextDouble(-4d, 4d);

		System.out.println("b1 = " + b1 + ", b2 = " + b2 + ", b3 = " + b3 + ", alfa = " + alfa);

		int count = 0;
		double[] netTab = new double[3];
		byte y1 = 0;
		byte y2 = 0;
		byte y3 = 0;

		final byte zero = 0;
		final byte jeden = 1;

		double bladSieci = 0;

		while (true) {
			epoki++;
			count = 0;
			bladSieci = 0;

			System.out.println("Epoka: " + epoki);

			// 1
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Zero.zero1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);

			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 0");

			if (y1 == 0 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Zero.zero1, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Zero.zero1, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Zero.zero1, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}

			// 2
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Zero.zero2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);

			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 0");

			if (y1 == 0 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Zero.zero2, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Zero.zero2, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Zero.zero2, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}

			// 3
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Zero.zero3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);

			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 0");

			if (y1 == 0 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Zero.zero3, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Zero.zero3, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Zero.zero3, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}

			// 4
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Jeden.jeden1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 1 ");
			if (y1 == 0 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Jeden.jeden1, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Jeden.jeden1, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Jeden.jeden1, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			// 5
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Jeden.jeden2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 1 ");
			if (y1 == 0 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Jeden.jeden2, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Jeden.jeden2, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Jeden.jeden2, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}

			// 6
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Jeden.jeden3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 0 1 ");
			if (y1 == 0 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Jeden.jeden3, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Jeden.jeden3, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Jeden.jeden3, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			// 7
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Dwa.dwa1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 0 ");
			if (y1 == 0 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Dwa.dwa1, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Dwa.dwa1, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Dwa.dwa1, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			// 8
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Dwa.dwa2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 0 ");
			if (y1 == 0 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Dwa.dwa2, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Dwa.dwa2, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Dwa.dwa2, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			// 9
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Dwa.dwa2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 0 ");
			if (y1 == 0 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Dwa.dwa2, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Dwa.dwa2, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Dwa.dwa2, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//10
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Trzy.trzy1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 1 ");
			if (y1 == 0 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Trzy.trzy1, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Trzy.trzy1, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Trzy.trzy1, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//11
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Trzy.trzy2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 1 ");
			if (y1 == 0 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Trzy.trzy2, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Trzy.trzy2, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Trzy.trzy2, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//12
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Trzy.trzy3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 0 1 1 ");
			if (y1 == 0 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 0) {
					wektorWag1 = aktualizujWagi(wektorWag1, Trzy.trzy3, zero, y1);
					b1 = aktualizujProg(zero, y1, b1);
					bladSieci += 0.5 * Math.pow(zero - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Trzy.trzy3, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Trzy.trzy3, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//13
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Cztery.cztery1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 0 ");
			if (y1 == 1 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Cztery.cztery1, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Cztery.cztery1, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Cztery.cztery1, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//14
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Cztery.cztery2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 0 ");
			if (y1 == 1 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Cztery.cztery2, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Cztery.cztery2, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Cztery.cztery2, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//15
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Cztery.cztery3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 0 ");
			if (y1 == 1 && y2 == 0 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Cztery.cztery3, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Cztery.cztery3, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Cztery.cztery3, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//16
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Piec.piec1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 1 ");
			if (y1 == 1 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Piec.piec1, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Piec.piec1, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Piec.piec1, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			
			//17
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Piec.piec2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 1 ");
			if (y1 == 1 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Piec.piec2, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Piec.piec2, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Piec.piec2, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//18
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Piec.piec3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 0 1 ");
			if (y1 == 1 && y2 == 0 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Piec.piec3, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 0) {
					wektorWag2 = aktualizujWagi(wektorWag2, Piec.piec3, zero, y2);
					b2 = aktualizujProg(zero, y2, b2);
					bladSieci += 0.5 * Math.pow(zero - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Piec.piec3, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//19
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Szesc.szesc1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 0 ");
			if (y1 == 1 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Szesc.szesc1, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Szesc.szesc1, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Szesc.szesc1, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//20
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Szesc.szesc2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 0 ");
			if (y1 == 1 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Szesc.szesc2, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Szesc.szesc2, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Szesc.szesc2, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//21
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Szesc.szesc3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 0 ");
			if (y1 == 1 && y2 == 1 && y3 == 0) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Szesc.szesc3, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Szesc.szesc3, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 0) {
					wektorWag3 = aktualizujWagi(wektorWag3, Szesc.szesc3, zero, y3);
					b3 = aktualizujProg(zero, y3, b3);
					bladSieci += 0.5 * Math.pow(zero - y3, 2);
				}
			}
			
			//22
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Siedem.siedem1, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 1 ");
			if (y1 == 1 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Siedem.siedem1, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Siedem.siedem1, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Siedem.siedem1, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}

			//23
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Siedem.siedem2, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 1 ");
			if (y1 == 1 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Siedem.siedem2, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Siedem.siedem2, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Siedem.siedem2, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			//24
			
			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, Siedem.siedem3, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki: " + y1 + " " + y2 + " " + y3 + ", expected: 1 1 1 ");
			if (y1 == 1 && y2 == 1 && y3 == 1) {
				count++;
			} else {
				if (y1 != 1) {
					wektorWag1 = aktualizujWagi(wektorWag1, Siedem.siedem3, jeden, y1);
					b1 = aktualizujProg(jeden, y1, b1);
					bladSieci += 0.5 * Math.pow(jeden - y1, 2);
				}
				if (y2 != 1) {
					wektorWag2 = aktualizujWagi(wektorWag2, Siedem.siedem3, jeden, y2);
					b2 = aktualizujProg(jeden, y2, b2);
					bladSieci += 0.5 * Math.pow(jeden - y2, 2);
				}
				if (y3 != 1) {
					wektorWag3 = aktualizujWagi(wektorWag3, Siedem.siedem3, jeden, y3);
					b3 = aktualizujProg(jeden, y3, b3);
					bladSieci += 0.5 * Math.pow(jeden - y3, 2);
				}
			}
			
			System.out.println("blad sieci: " + bladSieci + "");
			listaBledowSieci.add(bladSieci);
			if (count == 24) {
				break;
			}
		}

		System.out.println("epoki: " + epoki);

		Main chart = new Main("Wykres uczenia");

		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);

		while (true) {

			GUI gui = new GUI();

			while (gui.isVisible()) {
				try {
					Thread.sleep(110);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			byte[][] guess = gui.getTablicaWejsciowa();
			byte[] wektorWejsciowyGUI = new byte[24];
			int count2 = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					wektorWejsciowyGUI[count2] = guess[i][j];
					count2++;
				}
			}

			netTab = wyliczNET(wektorWag1, wektorWag2, wektorWag3, wektorWejsciowyGUI, b1, b2, b3);
			y1 = wyliczY(netTab[0]);
			y2 = wyliczY(netTab[1]);
			y3 = wyliczY(netTab[2]);
			System.out.println("Ygreki wejscie: " + y1 + " " + y2 + " " + y3);
			System.out.println("wektor wejsc z gui: " + Arrays.toString(wektorWejsciowyGUI));

			JFrame frame = new JFrame("Odpowiedź");

			if (y1 == 0 && y2 == 0 && y3 == 0) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 0!");
			} else if (y1 == 0 && y2 == 0 && y3 == 1) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 1!");
			} else if (y1 == 0 && y2 == 1 && y3 == 0) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 2!");
			} else if (y1 == 0 && y2 == 1 && y3 == 1) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 3!");
			} else if (y1 == 1 && y2 == 0 && y3 == 0) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 4!");
			} else if (y1 == 1 && y2 == 0 && y3 == 1) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 5!");
			} else if (y1 == 1 && y2 == 1 && y3 == 0) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 6!");
			} else if (y1 == 1 && y2 == 1 && y3 == 1) {
				JOptionPane.showMessageDialog(frame, "Twoja liczba to 7!");
			}

			Object[] options = { "tag", "nei ;_;" };

			int n = JOptionPane.showOptionDialog(frame, "Sprawdzić następną liczbę?",
					"brim brim tyrz tyrz tarataratara", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);

			if (n == 1) {
				break;
			}

		}
	}

	public static byte wyliczY(double y) {

		byte x;

		if (y >= 1) {
			x = 1;
		} else {
			x = 0;
		}

		return x;
	}

	public static double[] wyliczNET(double[] wektorWag1, double[] wektorWag2, double[] wektorWag3,
			byte[] wektorWejsciowy, double b1, double b2, double b3) {
		double[] netTabTemp = new double[3];

		for (int i = 0; i < wektorWag1.length; i++) {
			netTabTemp[0] += wektorWag1[i] * wektorWejsciowy[i];
		}
		for (int i = 0; i < wektorWag2.length; i++) {
			netTabTemp[1] += wektorWag2[i] * wektorWejsciowy[i];
		}
		for (int i = 0; i < wektorWag3.length; i++) {
			netTabTemp[2] += wektorWag3[i] * wektorWejsciowy[i];
		}
		netTabTemp[0] += b1;
		netTabTemp[1] += b2;
		netTabTemp[2] += b3;

		return netTabTemp;
	}

	public static double[] aktualizujWagi(double[] wektorWag, byte[] wektorWejsciowy, byte d, byte y) {

		double[] tempWektorWag = new double[24];

		for (int i = 0; i < tempWektorWag.length; i++) {
			tempWektorWag[i] = wektorWag[i] + (alfa * (d - y)) * wektorWejsciowy[i];
		}

		return tempWektorWag;
	}

	public static double aktualizujProg(byte d, byte y, double b) {
		double eb = b + alfa * (d - y);
		return eb;
	}
}