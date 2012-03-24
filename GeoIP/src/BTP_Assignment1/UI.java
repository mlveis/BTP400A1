/*This application is using API from google and Nazmul Idris
 * http://code.google.com/apis/maps/documentation/staticmaps/index.html
 * @author Nazmul Idris
 * @version 1.0
 * @since Apr 16, 2008, 10:55:50 PM
 *
 * UI.java 
 * @author: Chris Lin
 * 
 * 
 * */

package BTP_Assignment1;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Provider.GoogleMapsStatic.*;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class UI extends JFrame implements ActionListener,ChangeListener {
	//Swing Components
	private JPanel pLeft,pRight,pRightUp,pRightDown;
	private JButton bMoveUp,bMoveLeft,bMoveDown,bMoveRight,btnQuit;
	private JTextField tfLatitude,tfLongitude,tfZoomValue;
    private JSlider sZoom;
	private JLabel lblLeftTitle,lblRightTitle,lblLatitude,lblLongtitude,lblZoomValue,lblCityList,lblMessage;
	
	//Variables
	private BigDecimal lat,lon;
	private int zoom;
	private JLabel lblMessageTitle;

	@SuppressWarnings("unchecked")
	public UI(String title) {
		super(title);
		
		this.lat = new BigDecimal(43.652527).setScale(6, RoundingMode.HALF_UP);
		this.lon = new BigDecimal(-79.381961).setScale(6, RoundingMode.HALF_UP);
		this.zoom = 15;
		this.getContentPane().setLayout(null);
		
		//JPanel Left - for map display
		pLeft = new JPanel();
		pLeft.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pLeft.setBounds(10, 33, 400, 400);
		
		getContentPane().add(pLeft);	
		pLeft.setLayout(new GridLayout(0, 1, 0, 0));
		
		//JPanel Right
		pRight = new JPanel();
		pRight.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pRight.setBounds(420, 33, 275, 400);
		getContentPane().add(pRight);				
		pRight.setLayout(null);
		
		//JPanel Right Up		
		pRightUp = new JPanel();
		pRightUp.setBounds(10, 11, 255, 153);
		pRight.add(pRightUp);		
		pRightUp.setLayout(null);
		
		//JButton Move Up
		bMoveUp = new JButton("UP");
		bMoveUp.setBackground(Color.LIGHT_GRAY);
		bMoveUp.setBounds(48, 31, 71, 20);
		pRightUp.add(bMoveUp);
		bMoveUp.addActionListener(this);
		
		//JButton Move Left
		bMoveLeft = new JButton("LEFT");
		bMoveLeft.setBackground(Color.LIGHT_GRAY);
		bMoveLeft.setBounds(5, 62, 73, 20);
		pRightUp.add(bMoveLeft);
		bMoveLeft.addActionListener(this);
		
		//JButton Move Right
		bMoveRight = new JButton("RIGHT");
		bMoveRight.setBackground(Color.LIGHT_GRAY);
		bMoveRight.setBounds(88, 62, 71, 20);
		pRightUp.add(bMoveRight);
		bMoveRight.addActionListener(this);
		
		//JButton Move Down
		bMoveDown = new JButton("DOWN");
		bMoveDown.setBackground(Color.LIGHT_GRAY);
		bMoveDown.setBounds(48, 93, 71, 20);
		pRightUp.add(bMoveDown);
		bMoveDown.addActionListener(this);
		
		//JSilder
		//JSlider(int orientation, int min, int max, int value) 
		sZoom = new JSlider(SwingConstants.VERTICAL,1,19,this.zoom);
		sZoom.setBackground(Color.LIGHT_GRAY);
		sZoom.setBounds(177, 11, 63, 131);
		sZoom.setMajorTickSpacing(1);
		sZoom.setMinorTickSpacing(1);
		sZoom.setPaintTrack(true);
		sZoom.addChangeListener(this);
		pRightUp.add(sZoom);
		
		//JPanel Right Down
		pRightDown = new JPanel();
		pRightDown.setBounds(10, 178, 255, 211);
		pRight.add(pRightDown);
		pRightDown.setLayout(null);
		
		//JLabel Latitude
		lblLatitude = new JLabel("Lantitude");
		lblLatitude.setBounds(10, 11, 71, 14);
		pRightDown.add(lblLatitude);
		
		//JLabel Longitude
		lblLongtitude = new JLabel("Longtitude");
		lblLongtitude.setBounds(10, 36, 71, 14);		
		pRightDown.add(lblLongtitude);
		
		
		
		//JLabel Left Panel Title
		lblLeftTitle = new JLabel("Map");
		lblLeftTitle.setBounds(190, 8, 46, 14);
		getContentPane().add(lblLeftTitle);
		
		//JLabel  Right Panel Title
		lblRightTitle = new JLabel("Control Panel");
		lblRightTitle.setBounds(522, 8, 113, 14);
		getContentPane().add(lblRightTitle);
		
		//JTextField Latitude
		tfLatitude = new JTextField();
		tfLatitude.setBounds(107, 8, 138, 20);
		pRightDown.add(tfLatitude);
		tfLatitude.setColumns(10);
		tfLatitude.setText(this.lat.toString());
		
		//JTextField Longitude
		tfLongitude = new JTextField();
		tfLongitude.setBounds(107, 33, 138, 20);
		pRightDown.add(tfLongitude);
		tfLongitude.setColumns(10);
		tfLongitude.setText(this.lon.toString());
		
		
		
		lblCityList = new JLabel("City List");
		lblCityList.setBounds(10, 89, 46, 14);
		pRightDown.add(lblCityList);
		
		
		
		
		btnQuit = new JButton("Quit");
	    btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		btnQuit.setBackground(Color.LIGHT_GRAY);
		btnQuit.setBounds(107, 186, 89, 23);
		pRightDown.add(btnQuit);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(107, 168, 138, 14);
		pRightDown.add(lblMessage);
		
		lblZoomValue = new JLabel("Zoom");
		lblZoomValue.setBounds(10, 61, 46, 14);
		pRightDown.add(lblZoomValue);
		
		tfZoomValue = new JTextField(this.zoom+"");
		tfZoomValue.setBounds(107, 58, 35, 20);
		pRightDown.add(tfZoomValue);
		tfZoomValue.setColumns(10);
		
		lblMessageTitle = new JLabel("Message:");
		lblMessageTitle.setBounds(10, 168, 87, 14);
		pRightDown.add(lblMessageTitle);
		
		String cities[] = {"Toronto","Quebec","Waterloo"};
		JComboBox cbCities = new JComboBox(cities);
		cbCities.setBounds(107, 86, 138, 20);
		pRightDown.add(cbCities);
				
	}
	public static void main(String[] args) {
		UI ui = new UI("GeoIP System - Chris Lin");
		// Initial Loading Downtown Toronto Map
		ui.paintMap();		
		ui.setSize(724,487);
		ui.setResizable(false);
		ui.setVisible(true);	
	}

	public String paintMap(){
		//Toronto 43.652527,-79.381961
		//String path = MapLookup.getMap(43.652527, -79.381961, 400, 400, 14);
		String path = MapLookup.getMap(this.lat.doubleValue(), this.lon.doubleValue(), 400, 400, this.zoom);
		URL uri;
		try{
			//static method getDataFromURI(uri) return ByteBuffer
			uri = new URL(path);
			BufferedImage bi = ImageIO.read(uri);
			ImageIcon ii = new ImageIcon(bi);
			this.pLeft.removeAll();
			this.pLeft.add(new JLabel(ii));
			this.repaint();
			Graphics g = getGraphics();
			if(g!=null) paintComponents(g);
			else repaint();
			return "Map successfully loaded";	
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
			return "Fail to load map";		
		}		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton b = (JButton)e.getSource();
			if(b==this.bMoveUp){
				double d = this.lat.doubleValue() + 0.003;
				this.lat = new BigDecimal(d).setScale(6, RoundingMode.HALF_UP);
				this.tfLatitude.setText(this.lat.toString());
				this.lblMessage.setText(this.paintMap());
			}
			if(b==this.bMoveDown){
				double d = this.lat.doubleValue() - 0.003;
				this.lat = new BigDecimal(d).setScale(6, RoundingMode.HALF_UP);
				this.tfLatitude.setText(this.lat.toString());
				this.lblMessage.setText(this.paintMap());
			}
			if(b==this.bMoveLeft){
				double d = this.lon.doubleValue() - 0.003;
				this.lon = new BigDecimal(d).setScale(6, RoundingMode.HALF_UP);
				this.tfLongitude.setText(this.lon.toString());
				this.lblMessage.setText(this.paintMap());
			}
			if(b==this.bMoveRight){
				double d = this.lon.doubleValue() + 0.003;
				this.lon = new BigDecimal(d).setScale(6, RoundingMode.HALF_UP);
				this.tfLongitude.setText(this.lon.toString());
				this.lblMessage.setText(this.paintMap());
			}
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() instanceof JSlider){
			JSlider s = (JSlider) e.getSource();
			
			if(s==this.sZoom){
				if(!s.getValueIsAdjusting()){
					this.zoom = s.getValue();
					this.tfZoomValue.setText(this.zoom+"");
					this.paintMap();
				}
			}
		}
		
	}
}
