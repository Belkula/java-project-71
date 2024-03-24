package hexlet.code.formatters;

import hexlet.code.Differs;

import java.util.List;

public class Plain {
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";
    public static final String UPDATED = "updated";
    public static final String NOT_CHANGED = "not Changed";

    private static String complexValues(Object value) {
        String result;
        if (value == null || "null".equals(value)) {
            result = null;
        }

        if (value.toString().contains("[") || value.toString().contains("{")) {
            result = "[complex value]";
        } else if (value instanceof String) {
            result = "'" + value.toString() + "'";
        } else {
            result = value.toString();
        }
        return result;
    }

    public static String formatPlain(List<Differs> output) {
        StringBuilder outStr = new StringBuilder();

        for (Differs differ: output) {
            switch (differ.getStatus()) {
                case REMOVED:
                    outStr.append("Property '" + differ.getKey() + "' was removed\n");
                    break;
                case UPDATED:
                    outStr.append("Property '" + differ.getKey() + "' was updated. From "
                        + complexValues(differ.getOldValue()) + " to " + complexValues(differ.getNewValue()) + "\n");
                    break;
                case ADDED:
                    outStr.append("Property '" + differ.getKey()
                        + "' was added with value: " + complexValues(differ.getNewValue()) + "\n");
                    break;
                default:
                    break;
            }
        }
		
    if (outStr.length() > 0) {
        outStr.setLength(outStr.length() - 1);
    }

        return outStr.toString();
    }

}
