package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import engine.component.ALU;
import engine.component.Clock;
import engine.component.Clock.Engine;
import engine.component.Memory;
import engine.component.MicroProcessor;
import engine.memory.Register32;
import engine.memory.RegisterMemory;
import engine.parser.Assembler;
import engine.parser.instruction.Instruction;

@SuppressWarnings("serial")
public class DataPath extends JFrame {
	private Clock clock;
	private engine.DataPath dataPath;

	private boolean simulating;

	private JPanel pnlRegisterMemoryR0;
	private JLabel txtRegisterMemoryR0;
	private JPanel pnlRegisterMemoryR1;
	private JLabel txtRegisterMemoryR1;
	private JPanel pnlRegisterMemoryR2;
	private JLabel txtRegisterMemoryR2;
	private JPanel pnlRegisterMemoryR3;
	private JLabel txtRegisterMemoryR3;
	private JPanel pnlRegisterMemoryR4;
	private JLabel txtRegisterMemoryR4;
	private JPanel pnlRegisterMemoryR5;
	private JLabel txtRegisterMemoryR5;
	private JPanel pnlRegisterMemoryR6;
	private JLabel txtRegisterMemoryR6;
	private JPanel pnlRegisterMemoryR7;
	private JLabel txtRegisterMemoryR7;
	private JLabel txtRegisterMemoryW;

	private JLabel txtRegisterA;
	private JLabel txtRegisterAE;
	private JLabel txtRegisterB;
	private JLabel txtRegisterBE;
	private JLabel txtALU;
	private JLabel txtRegisterF;
	private JLabel txtRegisterFE;

	private JTextArea txtAsm;
	private JButton btnSimulate;

	public DataPath() {
		getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setTitle("DataPathSim");
		setBounds(100, 100, 455, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(6, 6, 209, 130);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		pnlRegisterMemoryR0 = new JPanel();
		pnlRegisterMemoryR0.setBackground(new Color(255, 255, 0));
		panel_1.add(pnlRegisterMemoryR0);
		pnlRegisterMemoryR0.setLayout(new BorderLayout(0, 0));

		JLabel lblR = new JLabel("R0:");
		lblR.setFont(lblR.getFont().deriveFont(lblR.getFont().getStyle() | Font.BOLD));
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR0.add(lblR, BorderLayout.WEST);

		txtRegisterMemoryR0 = new JLabel("0");
		txtRegisterMemoryR0.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR0.add(txtRegisterMemoryR0, BorderLayout.CENTER);

		pnlRegisterMemoryR4 = new JPanel();
		pnlRegisterMemoryR4.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR4);
		pnlRegisterMemoryR4.setLayout(new BorderLayout(0, 0));

		JLabel lblR_4 = new JLabel("R4:");
		lblR_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_4.setFont(lblR_4.getFont().deriveFont(lblR_4.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR4.add(lblR_4, BorderLayout.WEST);

		txtRegisterMemoryR4 = new JLabel("0");
		txtRegisterMemoryR4.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR4.add(txtRegisterMemoryR4, BorderLayout.CENTER);

		pnlRegisterMemoryR1 = new JPanel();
		pnlRegisterMemoryR1.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR1);
		pnlRegisterMemoryR1.setLayout(new BorderLayout(0, 0));

		JLabel lblR_1 = new JLabel("R1:");
		lblR_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_1.setFont(lblR_1.getFont().deriveFont(lblR_1.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR1.add(lblR_1, BorderLayout.WEST);

		txtRegisterMemoryR1 = new JLabel("0");
		txtRegisterMemoryR1.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR1.add(txtRegisterMemoryR1, BorderLayout.CENTER);

		pnlRegisterMemoryR5 = new JPanel();
		pnlRegisterMemoryR5.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR5);
		pnlRegisterMemoryR5.setLayout(new BorderLayout(0, 0));

		JLabel lblR_5 = new JLabel("R5:");
		lblR_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_5.setFont(lblR_5.getFont().deriveFont(lblR_5.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR5.add(lblR_5, BorderLayout.WEST);

		txtRegisterMemoryR5 = new JLabel("0");
		txtRegisterMemoryR5.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR5.add(txtRegisterMemoryR5, BorderLayout.CENTER);

		pnlRegisterMemoryR2 = new JPanel();
		pnlRegisterMemoryR2.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR2);
		pnlRegisterMemoryR2.setLayout(new BorderLayout(0, 0));

		JLabel lblR_2 = new JLabel("R2:");
		lblR_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_2.setFont(lblR_2.getFont().deriveFont(lblR_2.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR2.add(lblR_2, BorderLayout.WEST);

		txtRegisterMemoryR2 = new JLabel("0");
		txtRegisterMemoryR2.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR2.add(txtRegisterMemoryR2, BorderLayout.CENTER);

		pnlRegisterMemoryR6 = new JPanel();
		pnlRegisterMemoryR6.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR6);
		pnlRegisterMemoryR6.setLayout(new BorderLayout(0, 0));

		JLabel lblR_6 = new JLabel("R6:");
		lblR_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_6.setFont(lblR_6.getFont().deriveFont(lblR_6.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR6.add(lblR_6, BorderLayout.WEST);

		txtRegisterMemoryR6 = new JLabel("0");
		txtRegisterMemoryR6.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR6.add(txtRegisterMemoryR6, BorderLayout.CENTER);

		pnlRegisterMemoryR3 = new JPanel();
		pnlRegisterMemoryR3.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR3);
		pnlRegisterMemoryR3.setLayout(new BorderLayout(0, 0));

		JLabel lblR_3 = new JLabel("R3:");
		lblR_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_3.setFont(lblR_3.getFont().deriveFont(lblR_3.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR3.add(lblR_3, BorderLayout.WEST);

		txtRegisterMemoryR3 = new JLabel("0");
		txtRegisterMemoryR3.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR3.add(txtRegisterMemoryR3, BorderLayout.CENTER);

		pnlRegisterMemoryR7 = new JPanel();
		pnlRegisterMemoryR7.setBackground(Color.WHITE);
		panel_1.add(pnlRegisterMemoryR7);
		pnlRegisterMemoryR7.setLayout(new BorderLayout(0, 0));

		JLabel lblR_8 = new JLabel("R7:");
		lblR_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_8.setFont(lblR_8.getFont().deriveFont(lblR_8.getFont().getStyle() | Font.BOLD));
		pnlRegisterMemoryR7.add(lblR_8, BorderLayout.WEST);

		txtRegisterMemoryR7 = new JLabel("0");
		txtRegisterMemoryR7.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRegisterMemoryR7.add(txtRegisterMemoryR7, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel label_1 = new JLabel("Register Memory");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(label_1);

		txtRegisterMemoryW = new JLabel("W");
		txtRegisterMemoryW.setBackground(Color.RED);
		txtRegisterMemoryW.setOpaque(true);
		txtRegisterMemoryW.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(txtRegisterMemoryW, BorderLayout.EAST);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_11.setBounds(227, 6, 105, 31);
		getContentPane().add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));

		JLabel lblA = new JLabel("A:");
		lblA.setFont(lblA.getFont().deriveFont(lblA.getFont().getStyle() | Font.BOLD));
		panel_11.add(lblA, BorderLayout.WEST);

		txtRegisterA = new JLabel("0");
		txtRegisterA.setOpaque(true);
		txtRegisterA.setBackground(Color.WHITE);
		txtRegisterA.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(txtRegisterA, BorderLayout.CENTER);

		txtRegisterAE = new JLabel("E");
		txtRegisterAE.setBackground(Color.RED);
		txtRegisterAE.setOpaque(true);
		panel_11.add(txtRegisterAE, BorderLayout.EAST);

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_12.setBounds(344, 6, 105, 31);
		getContentPane().add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		JLabel lblB = new JLabel("B:");
		lblB.setFont(lblB.getFont().deriveFont(lblB.getFont().getStyle() | Font.BOLD));
		panel_12.add(lblB, BorderLayout.WEST);

		txtRegisterB = new JLabel("0");
		txtRegisterB.setOpaque(true);
		txtRegisterB.setBackground(Color.WHITE);
		txtRegisterB.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(txtRegisterB, BorderLayout.CENTER);

		txtRegisterBE = new JLabel("E");
		txtRegisterBE.setBackground(Color.RED);
		txtRegisterBE.setOpaque(true);
		panel_12.add(txtRegisterBE, BorderLayout.EAST);

		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBounds(227, 39, 222, 63);
		getContentPane().add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		JLabel lblA_1 = new JLabel("<html><b>A<br>L<br>U<b></html>");
		lblA_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblA_1.setFont(lblA_1.getFont().deriveFont(lblA_1.getFont().getStyle() | Font.BOLD));
		panel_14.add(lblA_1, BorderLayout.WEST);

		txtALU = new JLabel("F = 1");
		txtALU.setHorizontalAlignment(SwingConstants.CENTER);
		txtALU.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.add(txtALU, BorderLayout.CENTER);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_13.setBounds(227, 105, 222, 31);
		getContentPane().add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		JLabel lblF = new JLabel("F:");
		lblF.setFont(lblF.getFont().deriveFont(lblF.getFont().getStyle() | Font.BOLD));
		panel_13.add(lblF, BorderLayout.WEST);

		txtRegisterF = new JLabel("0");
		txtRegisterF.setOpaque(true);
		txtRegisterF.setBackground(Color.WHITE);
		txtRegisterF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_13.add(txtRegisterF, BorderLayout.CENTER);

		txtRegisterFE = new JLabel("E");
		txtRegisterFE.setBackground(Color.RED);
		txtRegisterFE.setOpaque(true);
		panel_13.add(txtRegisterFE, BorderLayout.EAST);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 148, 443, 12);
		getContentPane().add(separator);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(6, 172, 443, 234);
		getContentPane().add(scrollPane);

		txtAsm = new JTextArea();
//		txtAsm.setEnabled(false);
		txtAsm.setFont(new Font(Font.MONOSPACED, txtAsm.getFont().getStyle(), txtAsm.getFont().getSize()));
		txtAsm.setText("MOV1 R1\nMOV2 R2\nADD  R1, R2\n");
		scrollPane.setViewportView(txtAsm);

		btnSimulate = new JButton("Simulate");
		btnSimulate.setBounds(6, 418, 443, 29);
		btnSimulate.setEnabled(false);
		btnSimulate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("Simulate")) {
					btnSimulate.setText("Stop");

					simulate();

					clock.start();
				} else {
					btnSimulate.setText("Simulate");

					clock.stop();
				}
			}
		});
		getContentPane().add(btnSimulate);
	}

	private void simulate() {
		simulating = true;
		if (clock == null) {
			clock = new Clock(new Engine() {
				@Override
				public void operate(final Clock clock) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							do {
								clock.notifyOnPositiveEdge();
								clock.notifyOnNegativeEdge();

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} while (simulating);
						}
					}).start();
				}

				@Override
				public void halt() {
					simulating = false;
				}
			});

			Instruction[] instructions = new Assembler().parse(txtAsm.getText());

			Memory memory = new Memory();
			int i = 0;
			for (Instruction instruction : instructions) {
				int[] memoryData = instruction.toMemoryInstruction().toMemoryData();
				for (int j = 0; j < memoryData.length; j++) {
					memory.set(i++, memoryData[j]);
				}
			}

			MicroProcessor microProcessor = new MicroProcessor(memory, dataPath);
			clock.addListener(microProcessor);

			dataPath.clock(clock);
		}
	}

	public void attach(engine.DataPath dataPath) {
		this.dataPath = dataPath;
		btnSimulate.setEnabled(true);

		dataPath.cRegisterMemory.addListener(new RegisterMemory.Listener() {
			@Override
			public void onW(int w) {
				txtRegisterMemoryW.setBackground(w == 0b0 ? Color.RED : Color.GREEN);
			}

			@Override
			public void onS(int s) {
				pnlRegisterMemoryR0.setBackground(s == 0b000 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR1.setBackground(s == 0b001 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR2.setBackground(s == 0b010 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR3.setBackground(s == 0b011 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR4.setBackground(s == 0b100 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR5.setBackground(s == 0b101 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR6.setBackground(s == 0b110 ? Color.YELLOW : Color.WHITE);
				pnlRegisterMemoryR7.setBackground(s == 0b111 ? Color.YELLOW : Color.WHITE);
			}

			@Override
			public void onData(int i, int data) {
				switch (i) {
				case 0:
					txtRegisterMemoryR0.setText(String.valueOf(data));
					break;
				case 1:
					txtRegisterMemoryR1.setText(String.valueOf(data));
					break;
				case 2:
					txtRegisterMemoryR2.setText(String.valueOf(data));
					break;
				case 3:
					txtRegisterMemoryR3.setText(String.valueOf(data));
					break;
				case 4:
					txtRegisterMemoryR4.setText(String.valueOf(data));
					break;
				case 5:
					txtRegisterMemoryR5.setText(String.valueOf(data));
					break;
				case 6:
					txtRegisterMemoryR6.setText(String.valueOf(data));
					break;
				case 7:
					txtRegisterMemoryR7.setText(String.valueOf(data));
					break;

				default:
					break;
				}
			}
		});

		dataPath.cRegisterA.addListener(new Register32.Listener() {
			@Override
			public void onE(Register32 register, int e) {
				txtRegisterAE.setBackground(e == 0b0 ? Color.RED : Color.GREEN);
			}

			@Override
			public void onData(Register32 register, int data) {
				txtRegisterA.setText(String.valueOf(data));
			}
		});

		dataPath.cRegisterB.addListener(new Register32.Listener() {
			@Override
			public void onE(Register32 register, int e) {
				txtRegisterBE.setBackground(e == 0b0 ? Color.RED : Color.GREEN);
			}

			@Override
			public void onData(Register32 register, int data) {
				txtRegisterB.setText(String.valueOf(data));
			}
		});

		dataPath.cALU.addListener(new ALU.Listener() {
			@Override
			public void onFn(int s, int c, String fn) {
				txtALU.setText(fn);
			}
		});

		dataPath.cRegisterF.addListener(new Register32.Listener() {
			@Override
			public void onE(Register32 register, int e) {
				txtRegisterFE.setBackground(e == 0b0 ? Color.RED : Color.GREEN);
			}

			@Override
			public void onData(Register32 register, int data) {
				txtRegisterF.setText(String.valueOf(data));
			}
		});
	}
}
