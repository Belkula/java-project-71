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

    /** компилирует разницу между двумя объектами.
     *
     * @param map1 первый объект.
     * @param map2 второй объект.
     * @return возвращает разницу между объектами.
     */

    public static String getDiff(Object map1, Object map2) {
        String result = NOT_CHANGED;

        if (map1 != null && map2 == null) {
            result = REMOVED;
        } else if (map1 != null && map2 != null) {
            if (map1.equals(map2)) {
                result = NOT_CHANGED;
            } else {
                result = UPDATED;
            }
        } else if (map1 == null && map2 != null) {
            result = ADDED;
        }
        return result;
    }

    /** Собирает разницу между двумя картами.
     *
     * @param treemap1 Первая карта.
     * @param treemap2 Вторая карта.
     * @return Список различий между двумя картами.
     */

    public static List<Differs> treeMapsDiff(TreeMap<String, Object> treemap1, TreeMap<String, Object> treemap2) {
        List<Differs> treeMapDiff = new LinkedList<>();
        Object map1;
        Object map2;

        TreeMap<String, Object> keysFromBothFile = new TreeMap<>();
        keysFromBothFile.putAll(treemap1);
        keysFromBothFile.putAll(treemap2);

        for (Map.Entry<String, Object> entry : keysFromBothFile.entrySet()) {
            map1 = treemap1.get(entry.getKey());
            map2 = treemap2.get(entry.getKey());

            Differs differs = new Differs(map1, map2, entry.getKey(), getDiff(map1, map2));
            treeMapDiff.add(differs);

        }
        return treeMapDiff;
    }

    /** Генерирует отчёт различий между двумя файлами.
     *
     * @param filePath1 путь к первому файлу.
     * @param filePath2 путь ко второму файлу.
     * @return возвращает различия.
     * @throws Exception Выдаёт ошибку.
     */

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        TreeMap<String, Object> treemap1 = Parser.readFile(filePath1);
        TreeMap<String, Object> treemap2 = Parser.readFile(filePath2);
        List<Differs> treeMapsDiffs = treeMapsDiff(treemap1, treemap2);

        return Styles.formater(format, treeMapsDiffs);
    }
}
