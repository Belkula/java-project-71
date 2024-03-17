package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
	    public static final String REMOVED = "removed";
		public static final String ADDED = "added";
		public static final String UPDATED = "updated";
		public static final String NOT_CHANGED = "not Changed";

    public static String getDiff(Object Map1, Object Map2) {
		StringBuilder strOut = new StringBuilder();
		if (Map1 != null && Map2 == null) {
			return REMOVED;
		} else if (Map1 != null && Map2 != null && Map1.equals(Map2)) {
			return NOT_CHANGED;
		} else if (Map1 != null && Map2 != null) {
			return UPDATED;
		} else if (Map1 == null && Map2 != null) {
			return ADDED;
		} else {
			return NOT_CHANGED;
		}
		}


	 public static List<Differs> TreeMapsDiff(TreeMap<String, Object> treeMap1, TreeMap<String, Object> treeMap2) {
        List<Differs> treeMapDiff = new LinkedList<>();
        Object Map1;
        Object Map2;

        TreeMap<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treeMap1);
        keysFromBothFile.putAll(treeMap2);

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {
            Map1 = treeMap1.get(entry.getKey());
            Map2 = treeMap2.get(entry.getKey());

            Differs differs = new Differs(Map1, Map2,entry.getKey(), getDiff(Map1, Map2));
            treeMapDiff.add(differs);

        }

        return treeMapDiff;

	}


    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        TreeMap<String, Object> treeMap1 = Parser.readFile(filePath1);
        TreeMap<String, Object> treeMap2 = Parser.readFile(filePath2);
        List<Differs> treeMapsDiffs = TreeMapsDiff(treeMap1, treeMap2);

        return Styles.Formater(format, treeMapsDiffs);
    }
}