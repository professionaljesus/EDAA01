package textproc;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String,Integer>>{

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if(o1.getValue() - o2.getValue() == 0)
			return o1.getKey().compareTo(o2.getKey());
		else
			return o2.getValue() - o1.getValue();
	}


}
