package asgn2GUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private DepartingTrain dt_carriages = new DepartingTrain();
	
	private JList<String> lst_createCarriage_rollingStocks;
	private JFormattedTextField tf_createCarriage_weight;
	private JFormattedTextField tf_createCarriage_passengers;
	private JLabel l_createCarriage_passengers;
	private JButton btn_createCarriage_add;
	
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
		
		//Buttons
		btn_createCarriage_add = new JButton("Add");
		btn_createCarriage_add.setBounds((int)((2.05f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/10.0f) * width), (int)((1.0f/18.0f) * height));
		btn_createCarriage_add.setEnabled(false);
		
		//Formatted text fields
			//Weight
		tf_createCarriage_weight = new JFormattedTextField();
		tf_createCarriage_weight.setValue(new Integer(0));
		tf_createCarriage_weight.setBounds((int)((1.0f/9.0f) * width), (int)((14.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Passengers
		tf_createCarriage_passengers = new JFormattedTextField();
		tf_createCarriage_passengers.setValue(new String("0"));
		tf_createCarriage_passengers.setBounds((int)((1.0f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Add swing components to content pane
		add(l_createCarriage);
		add(l_removeCarriage);
		add(l_boardPassengers);
		add(l_reset);
		add(l_currentCarriage);
		add(l_train);
		
		add(l_createCarriage_weight);
		add(l_createCarriage_passengers);
		
		add(btn_createCarriage_add);
		
		add(lst_createCarriage_rollingStocks);
		
		add(tf_createCarriage_weight);
		add(tf_createCarriage_passengers);
		
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
		System.out.println(tf_createCarriage_weight.getText());
		System.out.println(tf_createCarriage_passengers.getText());
		try
		{
			if(Integer.parseInt(tf_createCarriage_weight.getText().toString()) >= 0)
			{
				String str;
				switch(selected.toString())
				{
				case "Locomotive":
					str = tf_createCarriage_passengers.getText();
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
					str = tf_createCarriage_passengers.getText();
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
		validate();
		repaint();
	}
}