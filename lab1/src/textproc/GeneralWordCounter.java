package textproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	
	private Map<String, Integer> m;
	private Set<String> no;
	
	public GeneralWordCounter(Set<String> w) {
		m = new TreeMap<String, Integer>();
		no = w;
	}

	@Override
	public void process(String w) {
		if(!no.contains(w)) {
			if(m.get(w) != null)
				m.put(w, m.get(w) + 1);
			else
				m.put(w, 1);
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords(){
		return m.entrySet();
		
	}

	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		//Collections.reverse(wordList);
		
		for(Entry<String, Integer> s: wordList)
			System.out.println(s.getKey() + ":" + s.getValue());

		/**
		for(String key: m.keySet()) {
			if(m.get(key) >= 200)
			System.out.println(key + ":" + m.get(key));
		}
		**/
	}

}
