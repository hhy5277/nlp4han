package com.lc.nlp4han.ner.word;

import java.util.ArrayList;

import com.lc.nlp4han.ner.NERParseStrategy;
import com.lc.nlp4han.ner.NERWordOrCharacterSample;

/**
 * 对MSRA语料基于词的解析
 * 
 * @author 刘小峰
 * @author 王馨苇
 *
 */
public class NERParseWordMSRA implements NERParseStrategy
{

	/**
	 * 说明： msra语料是一个实体就是一个词，tags就是实体标记，但是基于字的或者是根据人民日报解析出来的基于词的，标记都是加上BMES的，
	 * 样本类中的toNer toWord也是针对带上BMES标记的处理。所以，在解析的时候，在每个标记前面加上S_，保证代码通用
	 */
	@Override
	public NERWordOrCharacterSample parse(String sentence)
	{

		String[] wordsAndTags = sentence.split("\\s+");

		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> tags = new ArrayList<String>();

		for (int i = 0; i < wordsAndTags.length; i++)
		{
			String[] wordanspos = wordsAndTags[i].split("/");
			String word = wordanspos[0];
			String tag = wordanspos[1];
			if (tag.equals("o"))
			{
				words.add(word);
				tags.add(tag);
			}
			else
			{
				words.add(word);
				tags.add("s_" + tag);
			}
		}
		// for (int i = 0; i < words.size(); i++) {
		// System.out.println(words.get(i)+"/"+tags.get(i));
		// }
		return new NERWordOrCharacterSample(words, tags);
	}
}
