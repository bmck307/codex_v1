
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
		
		char[] backtracked = new char[user_input.length()];
		
		int back_iterator = 0;
		int front_iterator = 0;
		for (int i = 0; i < user_input.length(); i = i + 2)
		{
			if (i + 1 == user_input.length())
			{
				backtracked[i] = user_input.charAt(front_iterator);
			}
			else
			{
				backtracked[i] = user_input.charAt(front_iterator);
				backtracked[i + 1] = user_input.charAt(user_input.length() - 1 - back_iterator);
				++back_iterator;
				++front_iterator;
			}
		}
		
		return String.valueOf(backtracked);
	}
	
	public static String[] backtrack(String[] user_input)
	{	
		String current_string = "";
		for(int iterator = 0; iterator < user_input.length; iterator++)
		{
			current_string = user_input[iterator];
			char[] future = new char[current_string.length()];
			String backtracked_string = "";
			
			int loop_iterator = 0;
			for(int i = 0; i < current_string.length(); i++)
			{
				char j = current_string.charAt(i);
				char k = current_string.charAt(current_string.length() - i - 1);
				
				if (i != current_string.length() - i - 1)
				{
					future[loop_iterator] = j;
					future[loop_iterator + 1] = k;
					loop_iterator = loop_iterator + 2;
				}
				else
				{
					future[loop_iterator] = j;
					++loop_iterator;
				}
			}
			user_input[iterator] = String.valueOf(future);
		}
		
		return user_input;
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
	
	public static String convert_to_ascii(String user_input)
	{
		String new_string = "";
		for (int i = 0; i < user_input.length(); i++)
		{
			if ((int)user_input.charAt(i) < 100)
			{
				new_string += "0" +(int)user_input.charAt(i);
			}
			else
			{
				new_string += (int)user_input.charAt(i);
			}
		}
		return new_string;
	}
	
	public static String spongebob_text(String user_input)
	{
		String spongebob = "";
		boolean previous_capital = true;
		for(int i = 0; i < user_input.length(); ++i)
		{
			char j = user_input.charAt(i);
			if (j == ' ' | j == '.' | j == '!' | j == '?')
			{
				spongebob += j;
			}
			else if (previous_capital)
			{
				spongebob += j;
				previous_capital = false;
			}
			else
			{
				if (j >= 65 && j <= 90)
				{
					spongebob += (char)(j + 32);
				}
				else if (j >= 97 && j <= 122)
				{
					spongebob += (char)(j - 32);
				}
				else 
				{
					spongebob += j;
				}
				
				previous_capital = true;
			}
		}
		return spongebob;
	}
	
	/*
	 * HEY DIPSHIT, BREAKING IT INTO WORDS ISNT WORKING YOU FUCKING DONKEY
	 */
	public static String encode(String user_input)
	{
		String[] words = user_input.split(" ");
		
		for(int iterator = 0; iterator < words.length; iterator++)
		{
			words[iterator] = backtrack(words[iterator]);
			//words[iterator] = spongebob_text(words[iterator]);
			//words[iterator] = convert_to_ascii(words[iterator]);
		}
		
		user_input = reassemble(words);
		
		return user_input;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new JFrame("Encoder");
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
