package asgn2GUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private DepartingTrain dt_carriages = new DepartingTrain();
	
	private JList<String> lst_createCarriage_rollingStocks;
	private JFormattedTextField tf_createCarriage_weight;
	private JFormattedTextField tf_createCarriage_passengers;
	private JLabel l_createCarriage_passengers;
	private JButton btn_createCarriage_add;
	private JScrollPane trainScrollPane;
	private JPanel trainPanel;
	private int carriageCount = 0;
	
	public MainFrame(String title, int width, int height)
	{
		super(title);
		
		setSize(width, height);
		
		//Set layout manager
		setLayout(null);
		
		//Create swing components
		
		//Borders
		TitledBorder b_createCarriage = BorderFactory.createTitledBorder("Create Carriage");
		b_createCarriage.setTitleJustification(TitledBorder.CENTER);
		
		TitledBorder b_removeCarriage = BorderFactory.createTitledBorder("Remove Carriage");
		b_removeCarriage.setTitleJustification(TitledBorder.CENTER);
		
		TitledBorder b_boardPassengers = BorderFactory.createTitledBorder("Board Passengers");
		b_boardPassengers.setTitleJustification(TitledBorder.CENTER);
		
		TitledBorder b_reset = BorderFactory.createTitledBorder("Reset");
		b_reset.setTitleJustification(TitledBorder.CENTER);
		
		TitledBorder b_currentCarriage = BorderFactory.createTitledBorder("Current Carriage");
		b_currentCarriage.setTitleJustification(TitledBorder.CENTER);
		
		//Scroll Window
		TitledBorder b_trainDisplay = BorderFactory.createTitledBorder("Trains");
		b_trainDisplay.setTitleJustification(TitledBorder.CENTER);
		
		//Train Window
		TitledBorder b_train = BorderFactory.createTitledBorder("Train");
		b_train.setTitleJustification(TitledBorder.CENTER);
		
		//Border labels
		JLabel l_createCarriage = new JLabel("");
		l_createCarriage.setBorder(b_createCarriage);
		l_createCarriage.setBounds((int)(0.0f * width), (int)((2.0f/3.0f) * height), (int)((1.0f/3.0f) * width), (int)((1.0f/3.0f) * height));
		
		JLabel l_removeCarriage = new JLabel("");
		l_removeCarriage.setBorder(b_removeCarriage);
		l_removeCarriage.setBounds((int)((1.0f/3.0f) * width), (int)((2.0f/3.0f) * height), (int)((1.0f/3.0f) * width), (int)((1.0f/3.0f) * height));
		
		JLabel l_boardPassengers = new JLabel("");
		l_boardPassengers.setBorder(b_boardPassengers);
		l_boardPassengers.setBounds((int)((2.0f/3.0f) * width), (int)((2.0f/3.0f) * height), (int)((1.0f/3.0f) * width), (int)((1.0f/6.0f) * height));
		
		JLabel l_reset = new JLabel("");
		l_reset.setBorder(b_reset);
		l_reset.setBounds((int)((2.0f/3.0f) * width), (int)((5.0f/6.0f) * height), (int)((1.0f/3.0f) * width), (int)((1.0f/6.0f) * height));
		
		JLabel l_currentCarriage = new JLabel("");
		l_currentCarriage.setBorder(b_currentCarriage);
		l_currentCarriage.setBounds((int)((2.0f/3.0f) * width), (int)(0.0f * height), (int)((1.0f/3.0f) * width), (int)((1.0f/3.0f) * height));
		
		JLabel l_train = new JLabel("");
		l_train.setBorder(b_train);
		l_train.setBounds((int)((2.0f/3.0f) * width), (int)((1.0f/3.0f) * height), (int)((1.0f/3.0f) * width), (int)((1.0f/3.0f) * height));
		
		//Lists
		lst_createCarriage_rollingStocks = new JList<String>(new String[]{"Locomotive"});
		lst_createCarriage_rollingStocks.setBounds((int)(0.005f * width), (int)((13.0f/18.0f) * height), (int)((1.0f/11.0f) * width), (int)((2.0f/9.0f) * height));
		lst_createCarriage_rollingStocks.setSelectedIndex(0);
		
		//Labels
			//weight
		JLabel l_createCarriage_weight = new JLabel("Weight [non-negative]", JLabel.LEFT);
		l_createCarriage_weight.setVerticalAlignment(JLabel.CENTER);
		l_createCarriage_weight.setBounds((int)((1.0f/9.0f) * width), (int)((13.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));

		l_createCarriage_passengers = new JLabel("Power Class [1-9]['D'|'E'|'S']", JLabel.LEFT);
		l_createCarriage_passengers.setVerticalAlignment(JLabel.CENTER);
		l_createCarriage_passengers.setBounds((int)((1.0f/9.0f) * width), (int)((15.0f/18.0f) * height), (int)((1.0f/5.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_weight = new JLabel("Weight:", JLabel.LEFT);
		l_train_weight.setVerticalAlignment(JLabel.CENTER);
		l_train_weight.setBounds((int)((17.0f/24.0f) * width), (int)((17.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_power = new JLabel("Power:", JLabel.LEFT);
		l_train_power.setVerticalAlignment(JLabel.CENTER);
		l_train_power.setBounds((int)((17.0f/24.0f) * width), (int)((19.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_powerFree = new JLabel("Power Free:", JLabel.LEFT);
		l_train_powerFree.setVerticalAlignment(JLabel.CENTER);
		l_train_powerFree.setBounds((int)((17.0f/24.0f) * width), (int)((21.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_passengers = new JLabel("Passengers:", JLabel.LEFT);
		l_train_passengers.setVerticalAlignment(JLabel.CENTER);
		l_train_passengers.setBounds((int)((17.0f/24.0f) * width), (int)((23.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_seats = new JLabel("Seats:", JLabel.LEFT);
		l_train_seats.setVerticalAlignment(JLabel.CENTER);
		l_train_seats.setBounds((int)((17.0f/24.0f) * width), (int)((25.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_availableSeats = new JLabel("Available Seats:", JLabel.LEFT);
		l_train_availableSeats.setVerticalAlignment(JLabel.CENTER);
		l_train_availableSeats.setBounds((int)((17.0f/24.0f) * width), (int)((27.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_canMove = new JLabel("Can Move:", JLabel.LEFT);
		l_train_canMove.setVerticalAlignment(JLabel.CENTER);
		l_train_canMove.setBounds((int)((17.0f/24.0f) * width), (int)((29.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_weight = new JLabel("0", JLabel.RIGHT);
		l_train_input_weight.setVerticalAlignment(JLabel.CENTER);
		l_train_input_weight.setBounds((int)((17.0f/24.0f) * width), (int)((17.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_power = new JLabel("0", JLabel.RIGHT);
		l_train_input_power.setVerticalAlignment(JLabel.CENTER);
		l_train_input_power.setBounds((int)((17.0f/24.0f) * width), (int)((19.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_powerFree = new JLabel("0", JLabel.RIGHT);
		l_train_input_powerFree.setVerticalAlignment(JLabel.CENTER);
		l_train_input_powerFree.setBounds((int)((17.0f/24.0f) * width), (int)((21.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_passengers = new JLabel("0", JLabel.RIGHT);
		l_train_input_passengers.setVerticalAlignment(JLabel.CENTER);
		l_train_input_passengers.setBounds((int)((17.0f/24.0f) * width), (int)((23.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_seats = new JLabel("0", JLabel.RIGHT);
		l_train_input_seats.setVerticalAlignment(JLabel.CENTER);
		l_train_input_seats.setBounds((int)((17.0f/24.0f) * width), (int)((25.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_availableSeats = new JLabel("0", JLabel.RIGHT);
		l_train_input_availableSeats.setVerticalAlignment(JLabel.CENTER);
		l_train_input_availableSeats.setBounds((int)((17.0f/24.0f) * width), (int)((27.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JLabel l_train_input_canMove = new JLabel("YES", JLabel.RIGHT);
		l_train_input_canMove.setVerticalAlignment(JLabel.CENTER);
		l_train_input_canMove.setBounds((int)((17.0f/24.0f) * width), (int)((29.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Buttons
		btn_createCarriage_add = new JButton("Add");
		btn_createCarriage_add.setBounds((int)((2.05f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/10.0f) * width), (int)((1.0f/18.0f) * height));
		btn_createCarriage_add.setEnabled(false);
		
		JButton btn_removeCarriage_remove = new JButton("Remove");
		btn_removeCarriage_remove.setBounds((int)((7.0f/18.0f) * width), (int)((29.0f/36.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		JButton btn_boardPassengers_board = new JButton("Board");
		btn_boardPassengers_board.setBounds((int)((21.0f/24.0f) * width), (int)((26.0f/36.0f) * height), (int)((3.0f/36.0f) * width), (int)((1.0f/18.0f) * height));
		
		JButton btn_reset_reset = new JButton("Reset");
		btn_reset_reset.setBounds((int)((17.0f/24.0f) * width), (int)((32.0f/36.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Formatted text fields
			//Weight
		tf_createCarriage_weight = new JFormattedTextField();
		tf_createCarriage_weight.setValue(new Integer(0));
		tf_createCarriage_weight.setBounds((int)((1.0f/9.0f) * width), (int)((14.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Passengers
		tf_createCarriage_passengers = new JFormattedTextField();
		tf_createCarriage_passengers.setValue(new String("0"));
		tf_createCarriage_passengers.setBounds((int)((1.0f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Board passengers
		final JFormattedTextField tf_boardPassengers_passengers = new JFormattedTextField();
		tf_boardPassengers_passengers.setValue(new Integer(0));
		tf_boardPassengers_passengers.setHorizontalAlignment(JTextField.RIGHT);
		tf_boardPassengers_passengers.setBounds((int)((17.0f/24.0f) * width), (int)((26.0f/36.0f) * height), (int)((3.0f/18.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Scroll panes
		trainPanel = new JPanel();
		trainPanel.setLayout(null);
		trainPanel.setPreferredSize(new Dimension(220, (int)(2.0f/3.0f * height) - 20));
		
		trainScrollPane = new JScrollPane(trainPanel);
		trainScrollPane.setBounds(0, 0, (int)(2.0f/3.0f * width), (int)(2.0f/3.0f * height));
		
		//Add swing components to content pane
		add(l_createCarriage);
		add(l_removeCarriage);
		add(l_boardPassengers);
		add(l_reset);
		add(l_currentCarriage);
		add(l_train);
		add(l_createCarriage_weight);
		add(l_createCarriage_passengers);
		add(l_train_weight);
		add(l_train_power);
		add(l_train_powerFree);
		add(l_train_passengers);
		add(l_train_seats);
		add(l_train_availableSeats);
		add(l_train_canMove);
		
		add(l_train_input_weight);
		add(l_train_input_power);
		add(l_train_input_powerFree);
		add(l_train_input_passengers);
		add(l_train_input_seats);
		add(l_train_input_availableSeats);
		add(l_train_input_canMove);
		
		add(btn_createCarriage_add);
		add(btn_removeCarriage_remove);
		add(btn_boardPassengers_board);
		add(btn_reset_reset);
		
		add(lst_createCarriage_rollingStocks);
		
		add(tf_createCarriage_weight);
		add(tf_createCarriage_passengers);
		add(tf_boardPassengers_passengers);
		
		add(trainScrollPane);
		
		//Add behaviors
		lst_createCarriage_rollingStocks.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(e.getValueIsAdjusting())
				{
					btn_createCarriage_add.setEnabled(CheckTextValues(lst_createCarriage_rollingStocks.getSelectedValue().toString()));
					
					if(lst_createCarriage_rollingStocks.getSelectedValue().equals("Passenger Car"))
					{
						l_createCarriage_passengers.setText("Number of seats [non-negative]");
					}
					else if (lst_createCarriage_rollingStocks.getSelectedValue().equals("Freight Car"))
					{
						l_createCarriage_passengers.setText("Goods type ['D'|'G'|'R']");
					}
				}
			}
		});
		
		tf_createCarriage_weight.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent arg0) { }
			
			@Override
			public void keyReleased(KeyEvent arg0) { update(); }
			
			@Override
			public void keyPressed(KeyEvent arg0) { }
			
			public void update()
			{
				btn_createCarriage_add.setEnabled(CheckTextValues(lst_createCarriage_rollingStocks.getSelectedValue().toString()));
			}
		});
		
		tf_createCarriage_passengers.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent arg0) { }
			
			@Override
			public void keyReleased(KeyEvent arg0) { update(); }
			
			@Override
			public void keyPressed(KeyEvent arg0) { }
			
			public void update()
			{
				btn_createCarriage_add.setEnabled(CheckTextValues(lst_createCarriage_rollingStocks.getSelectedValue().toString()));
			}
		});
		
		//Add button behavior
			//Add carriage
		btn_createCarriage_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//make sure an option is selected
				if(lst_createCarriage_rollingStocks.getSelectedIndex() >= 0)
				{
					AddCarriage(lst_createCarriage_rollingStocks.getSelectedValue().toString());
				}
			}
		});
	}
	
	private void AddCarriage(String selected)
	{
		//Create a new train label to add to the GUI
		JLabel trainLabel = new JLabel("");
		trainLabel.setHorizontalAlignment(JLabel.CENTER);
		trainLabel.setOpaque(true);
		trainLabel.setBounds(carriageCount * 210 + 10, 50, 200, 100);
		trainPanel.add(trainLabel);
		carriageCount++;
		trainPanel.setPreferredSize(new Dimension(carriageCount * 210 + 10, trainPanel.getPreferredSize().height));
		
		switch(selected)
		{
		case "Locomotive":
			ChangeList(new String[]{"Passenger Car", "Freight Car"});
			try 
			{
				dt_carriages.addCarriage(new Locomotive(
						Integer.parseInt(tf_createCarriage_weight.getText().toString()),
						tf_createCarriage_passengers.getText().toString()
						));
				
				trainLabel.setText("Locomotive");
				trainLabel.setBackground(new Color(255,255,0));
				
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Passenger Car":
			try {
				dt_carriages.addCarriage(new PassengerCar(
						Integer.parseInt(tf_createCarriage_weight.getText().toString()),
						Integer.parseInt(tf_createCarriage_passengers.getText().toString())
						));
				
				//Set up train label as passenger car
				trainLabel.setText("Passenger Car");
				trainLabel.setBackground(new Color(255,0,0));
				
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Freight Car":
			ChangeList(new String[]{"Freight Car"});
			try {
				dt_carriages.addCarriage(new FreightCar(
						Integer.parseInt(tf_createCarriage_weight.getText().toString()),
						tf_createCarriage_passengers.getText().toString()
						));
				
				//Set up train label as freight car
				trainLabel.setText("Freight Car");
				trainLabel.setBackground(new Color(0,0,255));
				
			} catch (TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		}
		
		RollingStock car = dt_carriages.firstCarriage();
		while (car != null)
		{
			System.out.println(car.toString());
			car = dt_carriages.nextCarriage();
		}
	}
	
	private Boolean CheckTextValues(String selected)
	{
		try
		{
			if(Integer.parseInt(tf_createCarriage_weight.getText().toString()) >= 0)
			{
				String str;
				switch(selected.toString())
				{
				case "Locomotive":
					str = tf_createCarriage_passengers.getText().toUpperCase();
					if(str.length() == 2)
					{
						if(Integer.valueOf(str.substring(0, 1)) >= 1 || Integer.valueOf(str.substring(0, 1)) <= 9)
						{
							if(str.substring(1).equals("S") || str.substring(1).equals("D") || str.substring(1).equals("E"))
							{
								return true;
							}
						}
					}
					break;
				case "Passenger Car":
					if(Integer.parseInt(tf_createCarriage_passengers.getText()) >= 0)
					{
						return true;
					}
					break;
				case "Freight Car":
					str = tf_createCarriage_passengers.getText().toUpperCase();
					if(str.length() == 1)
					{
						if(str.equals("G") || str.equals("R") || str.equals("D"))
						{
							return true;
						}
					}
					break;
				}
			}
		}
		catch (NumberFormatException e) { }
		return false;
	}
	
	private void ChangeList(String[] list)
	{
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		for(String s : list)
		{
			newModel.addElement(s);
		}
		lst_createCarriage_rollingStocks.setModel(newModel);
		lst_createCarriage_rollingStocks.setSelectedIndex(0);
		validate();
		repaint();
	}
}