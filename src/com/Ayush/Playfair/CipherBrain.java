package com.Ayush.Playfair;

public class CipherBrain 
{
	
	public CipherBrain()
	{

	}
	
	public String encryptOrDecrypt(String inputText, String key, boolean isEncrypted)
	{
		if(isEncrypted)
		{
			return decryptText(inputText, key);
		}
		else
		{
			return encryptText(inputText, key);
		}
		
	}

	private String encryptText(String inputText, String key)
	{
		char[][] digraphs = prepareInputText(inputText);
		PlayfairBlock playfairBlock = new PlayfairBlock(key);
		StringBuilder sb_encryptedTextBuilder = new StringBuilder();
		
		
		for(char[] each_digraph : digraphs)
		{
			sb_encryptedTextBuilder.append(playfairBlock.encryptDigraph(each_digraph));
		}
		
		return sb_encryptedTextBuilder.toString();
	}
	
	private String decryptText(String inputText, String key)
	{
		char[][] digraphs = prepareInputText(inputText);
		PlayfairBlock playfairBlock = new PlayfairBlock(key);
		StringBuilder sb_decryptedTextBuilder = new StringBuilder();
		
		for(char[] each_digraph : digraphs)
		{
			sb_decryptedTextBuilder.append(playfairBlock.decryptDigraph(each_digraph));
		}
		
		return sb_decryptedTextBuilder.toString();
	}
	
	private char[][] prepareInputText(String inputText)
	{
		inputText = inputText.replaceAll("\\s", "");
		inputText = inputText.toLowerCase();
		
		if(inputText.length()%2 != 0)
		{
			inputText = inputText + "z";
		}
		
		char[][] digraphs = new char[inputText.length()/2][2];
		int index = 0;
		for(int i=0; i<inputText.length()/2; i++)
		{
			for(int j=0; j<2; j++)
			{
				digraphs[i][j] = inputText.charAt(index);
				index++;
			}
		}
		return digraphs;
	}



}
