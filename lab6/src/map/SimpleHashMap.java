package map;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;

import map.Map.Entry;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	private Entry<K, V>[] table;
	private double loadfactor; 
	private int size;
	private int mod;
	
	  public static void main(String[] args) throws IOException {
	    	SimpleHashMap<Integer, Integer> a = new SimpleHashMap<Integer, Integer>(4);
	    	java.util.Random random = new java.util.Random(123456);
			HashSet<Integer> randNbrs = new HashSet<Integer>();
			for (int i = 0; i < 10000; i++) {
				int r = random.nextInt(10000);
				a.put(r, r);
				randNbrs.add(r);
				if(i % 10 == 0)
					System.out.println(a.show());
			}

			
		}
	

	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap(){
		mod = 16;
		table = (Entry<K,V>[]) new Entry[16];
		loadfactor = 0.75;
		size = 0;

	}
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity){
		mod = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		loadfactor = 0.75;
		size = 0;

	}
	
	public String show() {
		Entry<K, V> temp;
		String s = "";
		for(int i = 0; i < table.length; i++) {
			temp = table[i];
			s += i + " - ";
			if(temp != null) {
				s += temp + " ";
				while(temp.next != null) {
					s += temp.next + " ";
					temp = temp.next;
				}
			}
			s += "\n";
		}
		return s;
	}
	
	private int index(K key) {
		return (Math.abs(key.hashCode()) % mod);
	}
	
	
	private Entry<K,V> find(int index, K key){
		Entry<K, V> temp = table[index];
		if(temp != null) {
			if(temp.getKey().equals(key))
				return temp;
			while(temp.next != null) {
				if(temp.next.getKey().equals(key))
					return temp.next;
				temp = temp.next;
			}

		}
		return null;
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K,V> ret = find(index(key), key);
		return ret == null? null:ret.getValue();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public V put(K arg0, V arg1) {
		V ret = null;
		int index = index(arg0);
		Entry<K, V> shit = find(index, arg0);
		if(shit == null) {
			Entry<K, V> in = new Entry<K, V>(arg0, arg1);
			if(table[index] == null) {
				table[index] = in;
			}else {
				Entry<K, V> temp = table[index];
				while(temp.next != null) {
					temp = temp.next;
				}
				temp.next = in;
			}
			ret = null;
			size++;
			
			if(size/(double)mod > loadfactor)
				rehash(nextPrime(mod*2));
				
		}else {
			ret = shit.getValue();
			shit.setValue(arg1);
		}
		return ret;
	}
	
	private void rehash(int newmod) {
		Entry<K, V>[] newtable = (Entry<K,V>[]) new Entry[newmod];
		for(int i = 0; i < table.length; i++) {
			Entry<K, V> temp = table[i];
			if(temp != null) {
				do{
					int neu = temp.getKey().hashCode() % newmod;
					if(newtable[neu] == null)
						newtable[neu] = new Entry<K, V>(temp.getKey(), temp.getValue());
					else {
						Entry<K, V> temp2 = newtable[neu];
						while(temp2.next != null) {
							temp2 = temp2.next;
						}
						temp2.next = new Entry<K, V>(temp.getKey(), temp.getValue());
					}
					temp = temp.next;
				}while(temp != null);
			
			}
			
		}
		table = newtable;
		mod = newmod;
		
	}
	
	static int nextPrime(int n)
    {
        BigInteger b = new BigInteger(String.valueOf(n));
        return Integer.valueOf((b.nextProbablePrime().toString()));
    }

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		Entry<K, V> e = table[index];
		if(e != null) {
			if(e.getKey().equals(key)) {
				table[index] = e.next;
				size--;
				if(size/(double)mod < 0.2)
					rehash(nextPrime(mod/2));
				return e.getValue();
			}
			while(e.next != null) {
				if(e.next.getKey().equals(key)) {
					Entry<K, V> x = e.next; //HÄR JÄVLAR ÄR BUGGEN IGEN JÄVLA SKIT
					e.next = x.next;
					size--;
					
					if(size/(double)mod < 0.2)
						rehash(nextPrime(mod/2));
					
					return x.getValue();
				}
				e = e.next;
			}
			return null;
		}
		else
			return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	private static class Entry<K, V> implements Map.Entry<K, V> {
		
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}


		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
			return this.value;
		}
		
		public String toString() {
			return key + "=" + value;
		}

	}

}
