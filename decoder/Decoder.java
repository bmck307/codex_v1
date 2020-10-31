import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Decoder {
	
	public static String unbacktrack(String decoded_string)
	{
		char[] future = new char[decoded_string.length()];
		int front_iterator = 0;
		int back_iterator = decoded_string.length() - 1;
		
		for (int i = 0; i < decoded_string.length(); i++)
		{
			if (i % 2 == 0 || i == 0)
			{
				future[front_iterator] = decoded_string.charAt(i);
				++front_iterator;
			}
			else
			{
				future[back_iterator] = decoded_string.charAt(i);
				back_iterator--;
			}
		}
		
		decoded_string = String.valueOf(future);
		return decoded_string;
	}
	
	public static String reassemble(String[] words)
	{
		String reassembled = "";
		
		for (int i = 0; i < words.length; i++)
		{
			if (i != words.length - 1)
			{
				reassembled += words[i] + " ";
			}
			else
			{
				reassembled += words[i];
			}
		}
		
		return reassembled;
	}
	
	public static String convert_from_ascii(String user_input)
	{	
		String new_string = "";
		for (int i = 0; i < user_input.length(); i = i + 3)
		{
			String temp = user_input.substring(i, i + 3);
			int temp_int = Integer.parseInt(temp);
			new_string += (char) temp_int;
		}
		
		return new_string;
	}
	
	public static String make_lowercase(String user_input)
	{
		String spongebob = "";
		for(int i = 0; i < user_input.length(); ++i)
		{
			char j = user_input.charAt(i);
			if (j >= 65 && j <= 90)
			{
				spongebob += (char)(j + 32);
			}
			else 
			{
				spongebob += j;
			}
		}
		return spongebob;
	}
	
	public static String unfuck_numbers(String user_input)
	{
		String new_ui = "";
		for(int iterator = 0; iterator < user_input.length(); iterator = iterator + 3)
		{
			String temp = user_input.substring(iterator, iterator + 3);
			int temp_int = Integer.parseInt(temp);
			temp_int = (int)((temp_int - 69)/ 2);
			if (temp_int < 100)
			{
				new_ui += "0" + String.valueOf(temp_int);
			}
			else
			{
				new_ui += String.valueOf(temp_int);
			}
		}
		return new_ui;
	}
	
	public static String words_length_one(String user_input)
	{
		int ui = Integer.parseInt(user_input);
		char user_char = (char)(ui - 69323);
		return String.valueOf(user_char);
	}
	
	public static String decode(String user_input)
	{
		String[] words = user_input.split("00");
		
		for(int iterator = 0; iterator < words.length; iterator++)
		{
			if(words[iterator].length() == 5)
			{
				words[iterator] = words_length_one(words[iterator]);
			}
			else
			{
				words[iterator] = unfuck_numbers(words[iterator]);
				words[iterator] = convert_from_ascii(words[iterator]);
				words[iterator] = make_lowercase(words[iterator]);
				words[iterator] = unbacktrack(words[iterator]);
			}
		}
	
		user_input = reassemble(words);
		
		return user_input;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new JFrame("Decoder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Put the phrase in that you want to be decoded:");
		JTextArea user_input = new JTextArea(5, 40);
		
		JButton button = new JButton("Compile");
		
		button.addActionListener(new ActionListener()
        {
			JTextArea response = new JTextArea("");
            public void actionPerformed(ActionEvent ae)
            {
            	String user_input_string = user_input.getText();
            	user_input_string = decode(user_input_string);
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
