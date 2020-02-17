package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.useDelimiter(" ");
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		scan.close();
		
		
		
		List<TextProcessor> p = new ArrayList<TextProcessor>();
		p.add(new SingleWordCounter("nils"));
		p.add(new SingleWordCounter("norge"));
		p.add(new MultiWordCounter(REGIONS));
		p.add(new GeneralWordCounter(stopwords));

		

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor a: p)
				a.process(word);
		}

		s.close();
		
		for(TextProcessor a: p)
			a.report();
		
		long t1 = System.nanoTime();
				
		
		System.out.println("tid : " + (t1 - t0) / 1000000.0 + " ms");
	}
}