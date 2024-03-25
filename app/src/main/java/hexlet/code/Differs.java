package hexlet.code;

/** Обозначает разницу между двумя значениями.
 */

public class Differs {

    private String key;
    private String status;
    private Object oldValue;
    private Object newValue;

/** Обозначает объект Differs.
 *
 * @param oldValue Старое значение.
 * @param newValue Новое значение.
 * @param key      Ключ.
 * @param status   Статус изменений.
 */

    public Differs(Object oldValue, Object newValue, String key, String status) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.key = key;
        this.status = status;
    }

/** Гетер старого значения.
 *
 * @return Возвращает старое значение.
 */

    public Object getOldValue() {
        return oldValue;
    }

/** Гетер нового значения.
 *
 * @return Возвращает новое значение.
 */

    public Object getNewValue() {
        return newValue;
    }

/** Гетер ключа изменений.
 *
 * @return Возвращает ключ изменений.
 */

    public String getKey() {
        return key;
    }

/** Гетер статуса изменений.
 *
 * @return Возвращает статус изменений.
 */

    public String getStatus() {
        return status;
    }

/** Возвращает разницу в виде строки.
 *
 * @return Возвращает разницу в виде строки.
 */

    public String toString() {
        return ("\"" + key + "\", \"" + status + "\", \"" + oldValue + "\", \"" + newValue + "\"");
    }
}
