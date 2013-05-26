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

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private DepartingTrain dt_carriages = new DepartingTrain();
	
	private String passengerLabel;
	
	JList<String> lst_createCarriage_rollingStocks;
	
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
		
//		final JPopupMenu pop_carriageSelect = new JPopupMenu("Carriage Type");
//		pop_carriageSelect.setBounds(int)(0.005f * width), (int)((13.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((2.0f/9.0f) * height));
//		pop_carriageSelect.add(
		
		//Lists
		lst_createCarriage_rollingStocks = new JList<String>(new String[]{"Locomotive"});
		lst_createCarriage_rollingStocks.setBounds((int)(0.005f * width), (int)((13.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((2.0f/9.0f) * height));
		lst_createCarriage_rollingStocks.setSelectedIndex(0);
		
		//Labels
			//weight
		JLabel l_createCarriage_weight = new JLabel("Weight", JLabel.CENTER);
		l_createCarriage_weight.setVerticalAlignment(JLabel.CENTER);
		l_createCarriage_weight.setBounds((int)((1.05f/9.0f) * width), (int)((13.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
				
		passengerLabel = "Power Class [1-9]['D'|'E'|'S']";
		JLabel l_createCarriage_passengers = new JLabel(passengerLabel, JLabel.CENTER);
		l_createCarriage_passengers.setVerticalAlignment(JLabel.CENTER);
		l_createCarriage_passengers.setBounds((int)((1.05f/9.0f) * width), (int)((15.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Buttons
		JButton btn_createCarriage_add = new JButton("Add");
		btn_createCarriage_add.setBounds((int)((2.05f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/10.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Formatted text fields
			//Weight
		final JFormattedTextField tf_createCarriage_weight = new JFormattedTextField();
		tf_createCarriage_weight.setValue(new Integer(0));
		tf_createCarriage_weight.setBounds((int)((1.05f/9.0f) * width), (int)((14.0f/18.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
		//Passengers
		final JFormattedTextField tf_createCarriage_passengers = new JFormattedTextField();
		tf_createCarriage_passengers.setValue(new String("0"));
		tf_createCarriage_passengers.setBounds((int)((1.05f/9.0f) * width), (int)((8.0f/9.0f) * height), (int)((1.0f/9.0f) * width), (int)((1.0f/18.0f) * height));
		
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
					if(lst_createCarriage_rollingStocks.getSelectedValue().equals("Passenger Car"))
					{
						passengerLabel = "Number of seats";
					}
					else if (lst_createCarriage_rollingStocks.getSelectedValue().equals("Freight Car"))
					{
						passengerLabel = "Goods type";
					}
				}
			}
		});
		
		//Add button behavior
			//Add carriage
		btn_createCarriage_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(lst_createCarriage_rollingStocks.getSelectedIndex());
				//make sure an option is selected
				if(lst_createCarriage_rollingStocks.getSelectedIndex() >= 0)
				{
					AddCarriage(lst_createCarriage_rollingStocks.getSelectedValue(), tf_createCarriage_weight.getValue(), tf_createCarriage_passengers.getValue());
				}
				
//				lst_createCarriage_rollingStocks.getSelectedValue()
//				tf_createCarriage_weight.getValue()
//				tf_createCarriage_passengers.getValue()
			}
		});
	}
	
	private void AddCarriage(Object selected, Object item1, Object item2)
	{
		switch(selected.toString())
		{
		case "Locomotive":
			remove(lst_createCarriage_rollingStocks);
			lst_createCarriage_rollingStocks = new JList<String>(new String[]{"Passenger Car", "Freight Car"});
			add(lst_createCarriage_rollingStocks);
			try 
			{
				dt_carriages.addCarriage(new Locomotive(
						Integer.parseInt(item1.toString()),
						item2.toString()
						));
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Passenger Car":
			try {
				dt_carriages.addCarriage(new PassengerCar(
						Integer.parseInt(item1.toString()),
						Integer.parseInt(item2.toString())
						));
			} catch (NumberFormatException | TrainException e1) {
//				e1.printStackTrace();
			}
			break;
		case "Freight Car":
			try {
				dt_carriages.addCarriage(new FreightCar(
						Integer.parseInt(item1.toString()),
						item2.toString()
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
}