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
import java.util.LinkedList;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	//object definitions
	private DepartingTrain dt_carriages = new DepartingTrain();
	
	//labels and lists
	private JList<String> lst_createCarriage_rollingStocks;
	private JFormattedTextField tf_createCarriage_weight;
	private JFormattedTextField tf_createCarriage_secondary;
	private JFormattedTextField tf_boardPassengers_passengers;
	private JLabel l_createCarriage_passengers;
	private JScrollPane trainScrollPane;
	private JPanel trainPanel;
	private LinkedList<JButton> trainCarriages;
	
	private JLabel l_currentCarriage_type;
	private JLabel l_currentCarriage_weight;
	private JLabel l_currentCarriage_carriageNumber;
	private JLabel l_currentCarriage_powerOutput;
	private JLabel l_currentCarriage_passengerLoad;
	private JLabel l_currentCarriage_seatsAvailable;
	private JLabel l_currentCarriage_goodsType;
	
	private JLabel l_currentCarriage_input_type;
	private JLabel l_currentCarriage_input_weight;
	private JLabel l_currentCarriage_input_carriageNumber;
	private JLabel l_currentCarriage_input_powerOutput;
	private JLabel l_currentCarriage_input_passengerLoad;
	private JLabel l_currentCarriage_input_seatsAvailable;
	private JLabel l_currentCarriage_input_goodsType;
	
	//train info labels
	private JLabel l_train_input_weight;
	private JLabel l_train_input_power;
	private JLabel l_train_input_powerFree;
	private JLabel l_train_input_passengers;
	private JLabel l_train_input_seats;
	private JLabel l_train_input_availableSeats;
	private JLabel l_train_input_canMove;
	
	
	//buttons
	private JButton btn_createCarriage_add;
	private JButton btn_removeCarriage_remove;
	private JButton btn_boardPassengers_board;
	
	//misc helping
	private int carriageCount = 0;
	private int currentCarriageIndex = 0;
	
	public MainFrame(String title, int width, int height)
	{
		super(title);
		
		setSize(width, height);
		
		trainCarriages = new LinkedList<>();
		
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
		l_createCarriage_weight.setBounds((int)((1.0f/9.0f) * width), (int)((13.0f/18.0f) * height), (int)((1.0f/4.0f) * width), (int)((1.0f/18.0f) * height));

		l_createCarriage_passengers = new JLabel("Power Class [1-9]['D'|'E'|'S']", JLabel.LEFT);
		l_createCarriage_passengers.setVerticalAlignment(JLabel.CENTER);
		l_createCarriage_passengers.setBounds((int)((1.0f/9.0f) * width), (int)((15.0f/18.0f) * height), (int)((1.0f/4.0f) * width), (int)((1.0f/18.0f) * height));
		
		
		l_currentCarriage_type = new JLabel("Type:", JLabel.LEFT);
		l_currentCarriage_type.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_type.setBounds((int)((17.0f/24.0f) * width), (int)((1.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_weight = new JLabel("Weight:", JLabel.LEFT);
		l_currentCarriage_weight.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_weight.setBounds((int)((17.0f/24.0f) * width), (int)((3.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_carriageNumber = new JLabel("Carriage Number:", JLabel.LEFT);
		l_currentCarriage_carriageNumber.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_carriageNumber.setBounds((int)((17.0f/24.0f) * width), (int)((5.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_powerOutput = new JLabel("Power Output:", JLabel.LEFT);
		l_currentCarriage_powerOutput.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_powerOutput.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_passengerLoad = new JLabel("Passenger Load:", JLabel.LEFT);
		l_currentCarriage_passengerLoad.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_passengerLoad.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_seatsAvailable = new JLabel("Seats Available:", JLabel.LEFT);
		l_currentCarriage_seatsAvailable.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_seatsAvailable.setBounds((int)((17.0f/24.0f) * width), (int)((9.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_goodsType = new JLabel("Goods Type:", JLabel.LEFT);
		l_currentCarriage_goodsType.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_goodsType.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		
		l_currentCarriage_input_type = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_type.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_type.setBounds((int)((17.0f/24.0f) * width), (int)((1.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_weight = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_weight.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_weight.setBounds((int)((17.0f/24.0f) * width), (int)((3.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_carriageNumber = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_carriageNumber.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_carriageNumber.setBounds((int)((17.0f/24.0f) * width), (int)((5.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_powerOutput = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_powerOutput.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_powerOutput.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_passengerLoad = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_passengerLoad.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_passengerLoad.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_seatsAvailable = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_seatsAvailable.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_seatsAvailable.setBounds((int)((17.0f/24.0f) * width), (int)((9.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_currentCarriage_input_goodsType = new JLabel("", JLabel.RIGHT);
		l_currentCarriage_input_goodsType.setVerticalAlignment(JLabel.CENTER);
		l_currentCarriage_input_goodsType.setBounds((int)((17.0f/24.0f) * width), (int)((7.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		
		l_currentCarriage_type.setVisible(false);
		l_currentCarriage_weight.setVisible(false);
		l_currentCarriage_carriageNumber.setVisible(false);
		l_currentCarriage_powerOutput.setVisible(false);
		l_currentCarriage_passengerLoad.setVisible(false);
		l_currentCarriage_seatsAvailable.setVisible(false);
		l_currentCarriage_goodsType.setVisible(false);
		
		
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
		
		l_train_input_weight = new JLabel("0", JLabel.RIGHT);
		l_train_input_weight.setVerticalAlignment(JLabel.CENTER);
		l_train_input_weight.setBounds((int)((17.0f/24.0f) * width), (int)((17.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_power = new JLabel("0", JLabel.RIGHT);
		l_train_input_power.setVerticalAlignment(JLabel.CENTER);
		l_train_input_power.setBounds((int)((17.0f/24.0f) * width), (int)((19.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_powerFree = new JLabel("0", JLabel.RIGHT);
		l_train_input_powerFree.setVerticalAlignment(JLabel.CENTER);
		l_train_input_powerFree.setBounds((int)((17.0f/24.0f) * width), (int)((21.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_passengers = new JLabel("0", JLabel.RIGHT);
		l_train_input_passengers.setVerticalAlignment(JLabel.CENTER);
		l_train_input_passengers.setBounds((int)((17.0f/24.0f) * width), (int)((23.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_seats = new JLabel("0", JLabel.RIGHT);
		l_train_input_seats.setVerticalAlignment(JLabel.CENTER);
		l_train_input_seats.setBounds((int)((17.0f/24.0f) * width), (int)((25.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_availableSeats = new JLabel("0", JLabel.RIGHT);
		l_train_input_availableSeats.setVerticalAlignment(JLabel.CENTER);
		l_train_input_availableSeats.setBounds((int)((17.0f/24.0f) * width), (int)((27.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		l_train_input_canMove = new JLabel("Yes", JLabel.RIGHT);
		l_train_input_canMove.setVerticalAlignment(JLabel.CENTER);
		l_train_input_canMove.setBounds((int)((17.0f/24.0f) * width), (int)((29.0f/48.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Buttons
		btn_createCarriage_add = new JButton("Add");
		btn_createCarriage_add.setBounds((int)((2.1f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/11.0f) * width), (int)((1.0f/18.0f) * height));
		btn_createCarriage_add.setEnabled(false);
		
		btn_removeCarriage_remove = new JButton("Remove Last");
		btn_removeCarriage_remove.setBounds((int)((7.0f/18.0f) * width), (int)((29.0f/36.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		btn_removeCarriage_remove.setEnabled(false);
		
		btn_boardPassengers_board = new JButton("Board");
		btn_boardPassengers_board.setBounds((int)((21.0f/24.0f) * width), (int)((26.0f/36.0f) * height), (int)((3.0f/36.0f) * width), (int)((1.0f/18.0f) * height));
		btn_boardPassengers_board.setEnabled(true);
		
		JButton btn_reset_reset = new JButton("Reset");
		btn_reset_reset.setBounds((int)((17.0f/24.0f) * width), (int)((32.0f/36.0f) * height), (int)((2.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Formatted text fields
			//Weight
		tf_createCarriage_weight = new JFormattedTextField();
		tf_createCarriage_weight.setValue(new String("0"));
		tf_createCarriage_weight.setHorizontalAlignment(JTextField.RIGHT);
		tf_createCarriage_weight.setBounds((int)((1.0f/9.0f) * width), (int)((14.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
			//Power Class/Passenger Seats/Goods Type
		tf_createCarriage_secondary = new JFormattedTextField();
		tf_createCarriage_secondary.setValue(new String("0"));
		tf_createCarriage_secondary.setHorizontalAlignment(JTextField.RIGHT);
		tf_createCarriage_secondary.setBounds((int)((1.0f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
			//Boarding passengers
		tf_boardPassengers_passengers = new JFormattedTextField();
		tf_boardPassengers_passengers.setValue(new String("0"));
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
		
		add(l_currentCarriage_type);
		add(l_currentCarriage_weight);
		add(l_currentCarriage_carriageNumber);
		add(l_currentCarriage_powerOutput);
		add(l_currentCarriage_passengerLoad);
		add(l_currentCarriage_seatsAvailable);
		add(l_currentCarriage_goodsType);
		
		add(l_currentCarriage_input_type);
		add(l_currentCarriage_input_weight);
		add(l_currentCarriage_input_carriageNumber);
		add(l_currentCarriage_input_powerOutput);
		add(l_currentCarriage_input_passengerLoad);
		add(l_currentCarriage_input_seatsAvailable);
		add(l_currentCarriage_input_goodsType);
		
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
		add(tf_createCarriage_secondary);
		add(tf_boardPassengers_passengers);
		
		add(trainScrollPane);
		
		//Add listener behaviors
		lst_createCarriage_rollingStocks.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(e.getValueIsAdjusting())
				{
					ChangeListLabels();
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
				btn_createCarriage_add.setEnabled(AddButtonState());
			}
		});
		
		tf_createCarriage_secondary.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent arg0) { }
			
			@Override
			public void keyReleased(KeyEvent arg0) { update(); }
			
			@Override
			public void keyPressed(KeyEvent arg0) { }
			
			public void update()
			{
				btn_createCarriage_add.setEnabled(AddButtonState());
			}
		});
		
		//Add button behaviors
		btn_createCarriage_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//make sure an option is selected
				if(lst_createCarriage_rollingStocks.getSelectedIndex() >= 0)
				{
					AddCarriage(lst_createCarriage_rollingStocks.getSelectedValue().toString());
					ChangeListLabels();
					RemoveButtonState();
					RefreshTrainInfo();
				}
			}
		});
		
		btn_removeCarriage_remove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					dt_carriages.RemoveCarriage();
					carriageCount--;
					trainPanel.remove(trainCarriages.get(trainCarriages.size()-1));
					trainPanel.setPreferredSize(new Dimension(carriageCount * 210 + 10, trainPanel.getPreferredSize().height));
					validate();
					repaint();
					trainCarriages.removeLast();
					RemoveButtonState();
					int carriagesSize = trainCarriages.size();
					if (carriagesSize <= 0) UpdateCurrentCarriageStatistics(null);
					else if (currentCarriageIndex >= carriagesSize)
					{
						currentCarriageIndex--;
						UpdateCurrentCarriageStatistics(GetCarriageByIndex(currentCarriageIndex));
					}
					
					RefreshTrainInfo();
					
					if(dt_carriages.firstCarriage() == null)
					{
						ChangeList(new String[]{"Locomotive"});
						l_train_input_canMove.setText("Yes");
					}
					else if (GetLastCar().equals("Passenger Car") || GetLastCar().equals("Locomotive"))
					{
						ChangeList(new String[]{"Passenger Car", "Freight Car"});
					}
					else if (GetLastCar().equals("Freight Car"))
					{
						ChangeList(new String[]{"Freight Car"});
					}
					btn_createCarriage_add.setEnabled(AddButtonState());
				} 
				catch (TrainException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		btn_reset_reset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dt_carriages = new DepartingTrain();
				carriageCount = 0;
				for(JButton jl : trainCarriages)
				{
					trainPanel.remove(jl);
				}
				trainPanel.setPreferredSize(new Dimension(10, trainPanel.getPreferredSize().height));
				trainCarriages.clear();
				validate();
				repaint();
				RemoveButtonState();
				btn_createCarriage_add.setEnabled(AddButtonState());
				ChangeList(new String[]{"Locomotive"});
				
				tf_createCarriage_weight.setValue(new Integer(0));
				tf_createCarriage_secondary.setValue(new String("0"));
				
				//reset train info
				l_train_input_power.setText("0");
				l_train_input_weight.setText("0");
				l_train_input_powerFree.setText("0");
				l_train_input_passengers.setText("0");
				l_train_input_seats.setText("0");
				l_train_input_availableSeats.setText("0");
				l_train_input_canMove.setText("No");
				
				//reset carriage info
				UpdateCurrentCarriageStatistics(null);
			}
		});
		
		btn_boardPassengers_board.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Integer remaining = dt_carriages.board(Integer.parseInt((tf_boardPassengers_passengers.getText())));
					tf_boardPassengers_passengers.setText(remaining.toString());
					RefreshTrainInfo();
					if (dt_carriages.numberOnBoard() > 0)
					{
						btn_createCarriage_add.setEnabled(false);
						btn_removeCarriage_remove.setEnabled(false);
					}
					
					UpdateCurrentCarriageStatistics(GetCarriageByIndex(currentCarriageIndex));
				}
				catch (NumberFormatException | TrainException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	private Integer GetIndexOfCarriage(RollingStock carriage)
	{
		Integer i = 0;
		RollingStock compare = dt_carriages.firstCarriage();
		while (carriage != compare)
		{
			compare = dt_carriages.nextCarriage();
			if (compare == null) break;
			i++;
		}
		return i;
	}
	
	private void UpdateCurrentCarriageStatistics(RollingStock carriage)
	{
		l_currentCarriage_type.setVisible(false);
		l_currentCarriage_weight.setVisible(false);
		l_currentCarriage_carriageNumber.setVisible(false);
		l_currentCarriage_powerOutput.setVisible(false);
		l_currentCarriage_passengerLoad.setVisible(false);
		l_currentCarriage_seatsAvailable.setVisible(false);
		l_currentCarriage_goodsType.setVisible(false);
		
		l_currentCarriage_input_type.setVisible(false);
		l_currentCarriage_input_weight.setVisible(false);
		l_currentCarriage_input_carriageNumber.setVisible(false);
		l_currentCarriage_input_powerOutput.setVisible(false);
		l_currentCarriage_input_passengerLoad.setVisible(false);
		l_currentCarriage_input_seatsAvailable.setVisible(false);
		l_currentCarriage_input_goodsType.setVisible(false);
		
		if (carriage instanceof Locomotive)
		{
			Locomotive l = (Locomotive)carriage;
			
			l_currentCarriage_type.setVisible(true);
			l_currentCarriage_weight.setVisible(true);
			l_currentCarriage_carriageNumber.setVisible(true);
			l_currentCarriage_powerOutput.setVisible(true);
			
			l_currentCarriage_input_type.setVisible(true);
			l_currentCarriage_input_weight.setVisible(true);
			l_currentCarriage_input_carriageNumber.setVisible(true);
			l_currentCarriage_input_powerOutput.setVisible(true);
			
			l_currentCarriage_input_type.setText("Locomotive");
			l_currentCarriage_input_weight.setText(l.getGrossWeight().toString());
			l_currentCarriage_input_carriageNumber.setText(new Integer(GetIndexOfCarriage(carriage)+1).toString());
			l_currentCarriage_input_powerOutput.setText(l.power().toString());
		}
		else if (carriage instanceof PassengerCar)
		{
			PassengerCar p = (PassengerCar)carriage;
			
			l_currentCarriage_type.setVisible(true);
			l_currentCarriage_weight.setVisible(true);
			l_currentCarriage_carriageNumber.setVisible(true);
			l_currentCarriage_passengerLoad.setVisible(true);
			l_currentCarriage_seatsAvailable.setVisible(true);
			
			l_currentCarriage_input_type.setVisible(true);
			l_currentCarriage_input_weight.setVisible(true);
			l_currentCarriage_input_carriageNumber.setVisible(true);
			l_currentCarriage_input_passengerLoad.setVisible(true);
			l_currentCarriage_input_seatsAvailable.setVisible(true);
			
			l_currentCarriage_input_type.setText("Passenger Car");
			l_currentCarriage_input_weight.setText(p.getGrossWeight().toString());
			l_currentCarriage_input_carriageNumber.setText(new Integer(GetIndexOfCarriage(carriage)+1).toString());
			l_currentCarriage_input_passengerLoad.setText(p.numberOnBoard().toString());
			l_currentCarriage_input_seatsAvailable.setText(new Integer(p.numberOfSeats() - p.numberOnBoard()).toString());
		}
		else if (carriage instanceof FreightCar)
		{
			FreightCar f = (FreightCar)carriage;
			
			l_currentCarriage_type.setVisible(true);
			l_currentCarriage_weight.setVisible(true);
			l_currentCarriage_carriageNumber.setVisible(true);
			l_currentCarriage_goodsType.setVisible(true);
			
			l_currentCarriage_input_type.setVisible(true);
			l_currentCarriage_input_weight.setVisible(true);
			l_currentCarriage_input_carriageNumber.setVisible(true);
			l_currentCarriage_input_goodsType.setVisible(true);
			
			l_currentCarriage_input_type.setText("Freight Car");
			l_currentCarriage_input_weight.setText(f.getGrossWeight().toString());
			l_currentCarriage_input_carriageNumber.setText(new Integer(GetIndexOfCarriage(carriage)+1).toString());
			l_currentCarriage_input_goodsType.setText(f.goodsType());
		}
	}
	
	private RollingStock GetCarriageByIndex(Integer i)
	{
		RollingStock c = dt_carriages.firstCarriage();
		while (i > 0)
		{
			c = dt_carriages.nextCarriage();
			i--;
		}
		return c;
	}
	
	private void AddCarriage(String selected)
	{
		//Create a new train label to add to the GUI
		
		JButton trainLabel = new JButton("");		
		trainLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int index = trainCarriages.indexOf(e.getSource());
				UpdateCurrentCarriageStatistics(GetCarriageByIndex(index));
				currentCarriageIndex = index;
			}
		});
		trainLabel.setHorizontalAlignment(JLabel.CENTER);
		trainLabel.setOpaque(true);
		trainLabel.setBounds(carriageCount * 210 + 10, 50, 200, 100);
		trainCarriages.add(trainLabel);
		trainPanel.add(trainLabel);
		carriageCount++;
		trainPanel.setPreferredSize(new Dimension(carriageCount * 210 + 10, trainPanel.getPreferredSize().height));
		
		switch(selected)
		{
		case "Locomotive":
			try 
			{
				dt_carriages.addCarriage(new Locomotive(
						Integer.parseInt((tf_createCarriage_weight.getText().replaceAll(",",""))),
						tf_createCarriage_secondary.getText().toString()
						));
				
				ChangeList(new String[]{"Passenger Car", "Freight Car"});
				trainLabel.setText("Locomotive");
				trainLabel.setBackground(new Color(255,255,0));
				UpdateCurrentCarriageStatistics(dt_carriages.firstCarriage());
				
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Passenger Car":
			try {
				dt_carriages.addCarriage(new PassengerCar(
						Integer.parseInt(tf_createCarriage_weight.getText()),
						Integer.parseInt(tf_createCarriage_secondary.getText().toString())
						));
				
				//Set up train label as passenger car
				trainLabel.setText("Passenger Car");
				trainLabel.setBackground(new Color(255,0,0));
				
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Freight Car":
			try {
				dt_carriages.addCarriage(new FreightCar(
						Integer.parseInt(tf_createCarriage_weight.getText()),
						tf_createCarriage_secondary.getText().toString()
						));

				ChangeList(new String[]{"Freight Car"});
				
				//Set up train label as freight car
				trainLabel.setText("Freight Car");
				trainLabel.setBackground(new Color(0,0,255));
				
			} catch (TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		}
	}
	
	private String GetLastCar()
	{
		RollingStock car = dt_carriages.firstCarriage();
		String carType = "Locomotive";
		while (car != null)
		{
			if(car instanceof PassengerCar)
			{
				carType = "Passenger Car";
			}
			else if (car instanceof FreightCar)
			{
				carType = "Freight Car";
			}
			car = dt_carriages.nextCarriage();
		}
		
		return carType;
	}
	
	private void RemoveButtonState()
	{
		btn_removeCarriage_remove.setEnabled(carriageCount > 0 ? true : false);
	}
	
	private Boolean AddButtonState()
	{
		try
		{
			if(Integer.parseInt(l_train_input_passengers.getText()) > 0)
			{
				return false;
			}
			else if(Integer.parseInt(tf_createCarriage_weight.getText().toString()) >= 0)
			{
				String str;
				switch(lst_createCarriage_rollingStocks.getSelectedValue().toString())
				{
				case "Locomotive":
					str = tf_createCarriage_secondary.getText().toUpperCase();
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
					if(Integer.parseInt(tf_createCarriage_secondary.getText()) >= 0)
					{
						return true;
					}
					break;
				case "Freight Car":
					str = tf_createCarriage_secondary.getText().toUpperCase();
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
	
	private void RefreshTrainInfo()
	{
		RollingStock car = dt_carriages.firstCarriage();
		Integer weight = 0;
		Integer powerLeft = 0;
		Integer seats = 0;
		Integer seatsTaken = 0;
		if(car instanceof Locomotive)
		{
			powerLeft = ((Locomotive) car).power() + car.getGrossWeight();
		}
		seats = dt_carriages.numberOfSeats();
		seatsTaken = dt_carriages.numberOnBoard();
		l_train_input_power.setText(powerLeft.toString());
		while (car != null)
		{
			/*if(car instanceof PassengerCar)
			{
				seats += ((PassengerCar) car).numberOfSeats();
				seatsTaken += ((PassengerCar) car).numberOnBoard();
			}*/
			weight += car.getGrossWeight();
			powerLeft -= car.getGrossWeight();
			car = dt_carriages.nextCarriage();
		}
		l_train_input_weight.setText(weight.toString());
		l_train_input_powerFree.setText(powerLeft.toString());
		l_train_input_passengers.setText(seatsTaken.toString());
		l_train_input_seats.setText(seats.toString());
		Integer seatsLeft = seats - seatsTaken;
		l_train_input_availableSeats.setText(seatsLeft.toString());
		l_train_input_canMove.setText(powerLeft < 0? "No" : "Yes");
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
		ChangeListLabels();
		validate();
		repaint();
	}
	
	private void ChangeListLabels()
	{
		btn_createCarriage_add.setEnabled(AddButtonState());
		if(lst_createCarriage_rollingStocks.getSelectedValue().equals("Passenger Car"))
		{
			l_createCarriage_passengers.setText("Number of seats [non-negative]");
		}
		else if (lst_createCarriage_rollingStocks.getSelectedValue().equals("Freight Car"))
		{
			l_createCarriage_passengers.setText("Goods type ['D'|'G'|'R']");
		}
		else
		{
			l_createCarriage_passengers.setText("Power Class [1-9]['D'|'E'|'S']");
		}
	}
}