import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFiles<T extends Comparable<T>> {
	public InputFiles(List<BufferedReader> inputfiles,Class<T> clazz) {
		this.inputfiles = inputfiles;
		this.clazz = clazz;
		toList();
	}
	
	public List<T> returnList() {
		sort(this.list, this.clazz);
		return list;
	}
	
	
	private void close() {
		for(BufferedReader bf : inputfiles) {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		inputfiles.clear();
	}
	
	private List<BufferedReader> inputfiles;
	private List<T> list = new ArrayList<>();
	private Class<T> clazz;
	
		
	private void sort(List<T> list, Class<T> c) {
		T[] objects = (T[])Array.newInstance(c,list.size());
		for(int i = 0; i < objects.length; ++i) {
			objects[i] = list.get(i);
		}
		sortUnsorted(objects, 0, objects.length - 1);
		list.clear();
		list.addAll(Arrays.asList(objects));
		
	}
	
	private void sortUnsorted(T[] a, int lo, int hi) {

	    if (hi <= lo)
	        return;
	    int mid = lo + (hi - lo) / 2;
	    sortUnsorted(a, lo, mid);
	    sortUnsorted(a, mid + 1, hi);

	    T[] buf = Arrays.copyOf(a, a.length);

	    for (int k = lo; k <= hi; k++)
	        buf[k] = a[k];

	    int i = lo, j = mid + 1;
	    for (int k = lo; k <= hi; k++) {

	        if (i > mid) {
	            a[k] = buf[j];
	            j++;
	        } else if (j > hi) {
	            a[k] = buf[i];
	            i++;
	        } else if ((buf[j]!= null) && (buf[j].compareTo(buf[i])) < 0) {
	            a[k] = buf[j];
	            j++;
	        } else {
	            a[k] = buf[i];
	            i++;
	        }
	    }
	}
	
	private void toList() {
		String s = null;
		
		
		for(int i = 0; i < inputfiles.size(); ++i) {
			try {
				while((s=inputfiles.get(i).readLine()) != null) {
					if(this.clazz == Integer.class) {
						try {
							list.add((T) Integer.valueOf(s));
						} catch(NumberFormatException e) {
							System.err.println("\n\nThe row is not integer, data may be lost...\n\n");
						}
					} else {
						list.add((T)s);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	
	
	
}
