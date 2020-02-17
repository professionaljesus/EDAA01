package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	
	private Map<String, Integer> words;
	
	
	public MultiWordCounter(String[] w) {
		words = new TreeMap<String, Integer>();
		for(String a: w)
			this.words.put(a, 0);
	}

	@Override
	public void process(String w) {
		if(words.get(w) != null)
			words.put(w, words.get(w) + 1);
	}

	@Override
	public void report() {
		for(String key: words.keySet())
			System.out.println(key + ":" + words.get(key));

	}

}
