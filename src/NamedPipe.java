import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
 
class NamedPipe extends JFrame{
	private static final long serialVersionUID = 1L;
	RandomAccessFile pipe;
	String pipeName;
	
	NamedPipe(String pipeName){
		super("MyWindow2");
		this.pipeName=pipeName;
		setLayout(new FlowLayout());
		
		JTextField txtText=new JTextField("Message                         ");
		JButton btnW=new JButton("Write");
		JButton btnR=new JButton("Read");
		JButton btnO=new JButton("Open");
		JButton btnC=new JButton("Close");
		JButton btnD=new JButton("Delete");
		
		add(txtText);
		add(btnO);
		add(btnR);
		add(btnW);
		add(btnC);
		add(btnD);
		
		btnW.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getTitle() + " WRITE: " + writeToPipe(txtText.getText()));
			}
		});
		
		btnR.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getTitle() +" READ:" + readFromPipe());
			}
			
		});
		
		btnO.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getTitle() +" OPEN: " + openPipe());
			}
		});
		
		btnC.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getTitle() + " CLOSE: " + closePipe());
			}
			
		});
		
		btnD.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getTitle() + " DELETE: " + deletePipe());
			}
		});
		
		setSize(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	boolean writeToPipe(String str){
		boolean done=false;
		 try {
			pipe.write (str.getBytes());
			done=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return done;
	}
	
	String readFromPipe(){
		String str=null;
		try {
			str = pipe.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	boolean openPipe(){
		boolean done=false;
		try {
			pipe= new RandomAccessFile(pipeName, "rw");
			done=true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			done=false;
		}
		return done;
	}
	
	boolean closePipe(){
		boolean done=false;
		try {
			pipe.close();
			done=true;
		} catch (IOException e) {
			done=false;
		}
		return done;
	}
	
	boolean deletePipe(){
		File f=new File(pipeName);
		return f.delete();
	}
	
	public static void main(String args[]){
		new NamedPipe("fooPipe");
	}
 
}