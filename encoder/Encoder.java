
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Encoder {
	/*
	 * This loop takes a letter from the front and back and puts them together at the front
	 * Ex:fuckoff would be ffufcok
	 */
	public static String backtrack(String user_input)
	{	
		
		String backtracked_string = "";
		
		for(int i = 0; i < (user_input.length() / 2); i++)
		{
			char j = user_input.charAt(i);
			char k = user_input.charAt(user_input.length() - i - 1);
			backtracked_string += j;
			backtracked_string += k;
		}
		
		return backtracked_string;
	}
	
	public static String encode(String user_input)
	{
		String[] words = user_input.split(" ");
		user_input = backtrack(user_input);
		
		return user_input;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new JFrame("Spongebob");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Put the words in that you want to be encoded:");
		JTextArea user_input = new JTextArea(5, 40);
		
		JButton button = new JButton("Compile");
		
		button.addActionListener(new ActionListener()
        {
			JTextArea response = new JTextArea("");
            public void actionPerformed(ActionEvent ae)
            {
            	String user_input_string = user_input.getText();
            	user_input_string = encode(user_input_string);
            	response.setText(user_input_string);
            	panel.add(response);
            	frame.add(panel);
            	frame.show();
            }
        });
		panel.add(title);
		panel.add(new JScrollPane(user_input, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		panel.add(button);
		
		panel.setLayout(new GridLayout(4,1));
		
		frame.add(panel);
		
		frame.setSize(600,600);
		frame.show();
	}

}