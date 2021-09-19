import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;
import com.l2fprod.common.swing.JCollapsiblePane;
import com.l2fprod.common.swing.JButtonBar;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;


public class ProjektowanieAplikacji {

	private JFrame frame;
	private JTextField textField;
	public JTable table;
	private JDateChooser dateChooser;
	public JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjektowanieAplikacji window = new ProjektowanieAplikacji();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjektowanieAplikacji() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 396);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Plik");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmZapisz = new JMenuItem("Zapisz");
		mntmZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zapiszDoPliku();
			}
		});
		mnNewMenu.add(mntmZapisz);
		
		JMenuItem mntmZamknij = new JMenuItem("Zamknij");
		mntmZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmZamknij);
		
		JMenu mnEdycja = new JMenu("Edycja");
		menuBar.add(mnEdycja);
		
		JSlider suwak_k = new JSlider();
		suwak_k.setMinorTickSpacing(1);
		suwak_k.setValue(1);
		suwak_k.setSnapToTicks(true);
		suwak_k.setPaintTicks(true);
		suwak_k.setMinimum(1);
		suwak_k.setMaximum(5);
		
		JSlider suwak_r = new JSlider();
		suwak_r.setMinorTickSpacing(1);
		suwak_r.setValue(1);
		suwak_r.setPaintTicks(true);
		suwak_r.setSnapToTicks(true);
		suwak_r.setMinimum(1);
		suwak_r.setMaximum(5);
		
		
	//jtext area wyswietla wyniki dzialan z jlist
		
		JTextArea textArea = new JTextArea();
		
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		
		JMenuItem mntmZapiszWKomrce = new JMenuItem("Zapisz w komorce");
		mntmZapiszWKomrce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int a = Integer.parseInt(textField.getText());
					int x = suwak_k.getValue()-1;
					int y = suwak_r.getValue()-1;
					table.setValueAt(a, y, x);
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(frame, "Podaj wartosc liczbowa");
				}
			}
		});
		mnEdycja.add(mntmZapiszWKomrce);
		
		JMenuItem mntmZerujTabel = new JMenuItem("Zeruj tabele");
		mntmZerujTabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeruj();
			}
		});
		mnEdycja.add(mntmZerujTabel);
		
		JMenu mnDzialania = new JMenu("Dzialania");
		menuBar.add(mnDzialania);
		
		JMenuItem mntmSuma = new JMenuItem("Suma");
		mntmSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i,j;
				float sum = 0;
				for (i=0; i<5; i++) {
					for (j=0; j<5; j++) {
						sum = sum + (int)table.getValueAt(i, j);
					}
				}
				String sumResult = String.valueOf(sum);
				textArea.setText("Suma elementow tablicy: " + sumResult);
			}
		});
		mnDzialania.add(mntmSuma);
		
		JMenuItem mntmsrednia = new JMenuItem("Srednia");
		mntmsrednia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i,j;
				float sum = 0;
				float avg;
				for (i=0; i<5; i++) {
					for (j=0; j<5; j++) {
						sum = sum + (int)table.getValueAt(i, j);
					}
				}
				avg = sum/25;
				String avgResult = String.valueOf(avg);
				textArea.setText("srednia wartosc elementow: " + avgResult);
			}
		});
		mnDzialania.add(mntmsrednia);
		
		JMenuItem mntmMaxmin = new JMenuItem("Max/min");
		mntmMaxmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i,j,min,max;
				int value;
				min = (int) table.getValueAt(0, 0);
				max = (int) table.getValueAt(0, 0);
				for (i=0; i<5; i++) {
					for (j=0; j<5; j++) {
						value = (int) table.getValueAt(i, j);
						if (min > value) {
							min = value;
						}
						if (max < value) {
							max = value;
						}
					}
				}
				textArea.setText("Najwieksza wartosc: " + max +  "\nNajmniejsza wartosc: " + min);
			}
		});
		mnDzialania.add(mntmMaxmin);
		
		JMenu mnHelp = new JMenu("Pomoc");
		menuBar.add(mnHelp);
		
		JMenuItem mnVersion = new JMenuItem("Version");
		mnVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Aplikacja okienkowa ver 1.0");
			}
		});
		
		mnHelp.add(mnVersion);
		
		JMenuItem mnAbout = new JMenuItem("About");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Pawel Zarzycki, Politechnika Koszalinska");
			}
		});
		mnHelp.add(mnAbout);
		
		
		//tworzymy wykres
		JMenu mnWykres = new JMenu("Wykres");
		menuBar.add(mnWykres);
		
		JMenuItem mnWykres1 = new JMenuItem("Pokaz wykres");
		mnWykres1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				zapiszDoPliku();
				
			    try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/Pawel/Downloads/projektowanie_aplikacji(1)/Dane.txt"))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            String[] lineSplitted = line.split("[ \\|]+"); //za pomoca uzycia delimiterow udalo mi sie posegregowac intigery
			            String label = lineSplitted[0];
			            
			            DefaultCategoryDataset dataset= new DefaultCategoryDataset();
						dataset.setValue(new Double(lineSplitted[0]), "1", "1");
						dataset.setValue(new Double(lineSplitted[1]), "2", "2");
						dataset.setValue(new Double(lineSplitted[2]), "3", "3");
						dataset.setValue(new Double(lineSplitted[3]), "4", "4");
						dataset.setValue(new Double(lineSplitted[4]), "5", "5");
						dataset.setValue(new Double(lineSplitted[5]), "6", "6");
						dataset.setValue(new Double(lineSplitted[6]), "7", "7");
						dataset.setValue(new Double(lineSplitted[7]), "8", "8");
						dataset.setValue(new Double(lineSplitted[8]), "9", "9");
						dataset.setValue(new Double(lineSplitted[9]), "10", "10");
						dataset.setValue(new Double(lineSplitted[10]), "11", "11");
						dataset.setValue(new Double(lineSplitted[11]), "12", "12");
						dataset.setValue(new Double(lineSplitted[12]), "13", "13");
						dataset.setValue(new Double(lineSplitted[13]), "14", "14");
						dataset.setValue(new Double(lineSplitted[14]), "15", "15");
						dataset.setValue(new Double(lineSplitted[15]), "16", "16");
						dataset.setValue(new Double(lineSplitted[16]), "17", "17");
						dataset.setValue(new Double(lineSplitted[17]), "18", "18");
						dataset.setValue(new Double(lineSplitted[18]), "19", "19");
						dataset.setValue(new Double(lineSplitted[19]), "20", "20");
						dataset.setValue(new Double(lineSplitted[20]), "21", "21");
						dataset.setValue(new Double(lineSplitted[21]), "22", "22");
						dataset.setValue(new Double(lineSplitted[22]), "23", "23");
						dataset.setValue(new Double(lineSplitted[23]), "24", "24");
						dataset.setValue(new Double(lineSplitted[24]), "25", "25");
			            
						JFreeChart chart=ChartFactory.createBarChart("Histogram pionowy", "Miejsce liczby w tablicy", "Wartosc liczby", dataset, PlotOrientation.VERTICAL, false, true, false);
						CategoryPlot p=chart.getCategoryPlot();
						p.setRangeGridlinePaint(Color.black);
						ChartFrame frame=new ChartFrame("Wykres",chart);
						frame.setVisible(true);
						frame.setSize(950,400);				
						
			        }

			    } catch (IOException e1) {
			        e1.printStackTrace();
			    }
				
				
			}
		});
		mnWykres.add(mnWykres1);
		
		
		frame.getContentPane().setLayout(null);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(10, 17, 443, 280);
				frame.getContentPane().add(panel_1);
				
				JLabel lblLiczba = new JLabel("Liczba:");
				
				textField = new JTextField();
				textField.setColumns(10);
				
				JButton btnZapisz = new JButton("Zapisz");
				btnZapisz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int a = Integer.parseInt(textField.getText());
							int x = suwak_k.getValue()-1;
							int y = suwak_r.getValue()-1;
							table.setValueAt(a, y, x);
						}
						catch(Exception e) {
							JOptionPane.showMessageDialog(frame, "Podaj wartosc liczbowa");
						}
					}
				});
				
				JButton btnZeruj = new JButton("Zeruj");
				btnZeruj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zeruj();		
					}
				});
				
				
				
				JLabel lblKolumna = new JLabel("Kolumna:");
				
				JLabel lblRzd = new JLabel("Rzad:");
				
				
				
				table = new JTable(5,5);
				table.setBorder(new LineBorder(new Color(0, 0, 0)));
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setRowSelectionAllowed(false);
				table.setCellSelectionEnabled(false);
				
				
				//tworzymy jlist 
				JList list = new JList();
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {"Suma elementow", "Srednia elementow", "Wartosc max i min"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						float sum = 0;
						int i,j;
						float avg = 0;
						float min = 0;
						float max = 0;
						float value;
						if (list.getSelectedValue()=="Suma elementow") {				
							for (i=0; i<5; i++) {
								for (j=0; j<5; j++) {
									sum = sum + (int)table.getValueAt(i, j);
								}
							}
							String sumResult = String.valueOf(sum);
							textArea.setText("Suma elementow tablicy: " + sumResult);
						}
						else if (list.getSelectedValue()=="Srednia elementow") {
							for (i=0; i<5; i++) {
								for (j=0; j<5; j++) {
									sum = sum + (int)table.getValueAt(i, j);
								}
							}
							avg = sum/25;
							String avgResult = String.valueOf(avg);
							textArea.setText("srednia wartosc elementow: " + avgResult);
						}
						else if (list.getSelectedValue()=="Wartosc max i min") {
							min = (int) table.getValueAt(0, 0);
							max = (int) table.getValueAt(0, 0);
							for (i=0; i<5; i++) {
								for (j=0; j<5; j++) {
									value = (int) table.getValueAt(i, j);
									if (min > value) {
										min = value;
									}
									if (max < value) {
										max = value;
									}
								}
							}
							textArea.setText("Najwieksza wartosc: " + max +  "\nNajmniejsza wartosc: " + min);
						}
					}
				});
				
				JButton btnZapiszTablic = new JButton("Zapisz tablice");
				btnZapiszTablic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						zapiszDoPliku();
					}
				});
				
				
				
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
													.addComponent(lblLiczba)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_1.createSequentialGroup()
													.addComponent(btnZapisz)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnZeruj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
											.addGap(113))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblKolumna)
											.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addGap(78))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(suwak_k, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRzd)
										.addComponent(suwak_r, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(18)
											.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(65)
											.addComponent(btnZapiszTablic)))
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLiczba)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnZapisz)
										.addComponent(btnZeruj))
									.addGap(12)
									.addComponent(lblKolumna)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(suwak_k, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRzd)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(suwak_r, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(32)
									.addComponent(list, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(btnZapiszTablic)
									.addGap(17)))
							.addGap(14))
				);
				panel_1.setLayout(gl_panel_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 637, 16);
		frame.getContentPane().add(toolBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 307, 637, 31);
		frame.getContentPane().add(panel);		
		
		JTaskPane taskPane = new JTaskPane();
		taskPane.setBackground(Color.GRAY);
		taskPane.setBounds(463, 17, 174, 290);
		frame.getContentPane().add(taskPane);
		
		JTaskPaneGroup Opcje = new JTaskPaneGroup();
		Opcje.setTitle("Opcje");
		taskPane.add(Opcje);
		Opcje.setExpanded(true);
		
		DefaultTipModel spis_porad = new DefaultTipModel();
		spis_porad.add(new DefaultTip("tip1","Tresc pierwszej porady."));
		spis_porad.add(new DefaultTip("tip2","Tresc drugiej porady."));
		JTipOfTheDay porady = new JTipOfTheDay(spis_porad);
		porady.showDialog(porady);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 17, 443, 280);
		frame.getContentPane().add(panel_2);
		
		JLabel lblData = new JLabel("Data");
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		
		dateChooser = new JDateChooser();
		
		JButton btnPobierzDat = new JButton("Pobierz date");
		btnPobierzDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				textArea_1.setText(df.format(dateChooser.getDate()));
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblData)
							.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPobierzDat)))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(8)
					.addComponent(lblData)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPobierzDat)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		panel_1.setVisible(true);
		panel_2.setVisible(false);
		
		double rand;
		int number;
		
		for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) {
            	rand = Math.random() *100;
            	number = (int) rand;
                table.setValueAt(number, i, j);
            }
        }
		Opcje.add(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "Tablica");
				putValue(Action.SHORT_DESCRIPTION, "test");
			}
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
			}
		});
		Opcje.add(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "Kalendarz");
				putValue(Action.SHORT_DESCRIPTION, "test2");
			}
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		
	}
	
	public void zapiszDoPliku() {
		try {
			File plik = new File ("Dane.txt");
			if (!plik.exists()) {
				plik.createNewFile();
			}
			FileWriter zapis = new FileWriter(plik.getAbsoluteFile());
			BufferedWriter bufor = new BufferedWriter(zapis);
			for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    bufor.write(table.getValueAt(i, j) + " ");
                }
                bufor.write("| ");
            }
			bufor.close();
			JOptionPane.showMessageDialog(frame, "Plik zapisany");
		}
		catch (Exception exception) {
			JOptionPane.showMessageDialog(frame, "Nieudana proba zapisu");
		}
	}
	
	public void zeruj() {
		for (int i = 0; i < 5; i++) { 
            for (int j = 0; j < 5; j++) {
                table.setValueAt(0, i, j);
            }
        }
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
