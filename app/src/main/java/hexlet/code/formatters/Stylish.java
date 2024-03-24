package hexlet.code.formatters;

import hexlet.code.Differs;
import java.util.List;

public class Stylish {
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";
    public static final String UPDATED = "updated";
    public static final String NOT_CHANGED = "not Changed";

    public static String toStringExceptNull(Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }
    }

    public static String formatStylish(List<Differs> output) {

        StringBuilder outStr = new StringBuilder("{\n");

        for (Differs differ: output) {
            switch (differ.getStatus()) {
                case REMOVED:
                    outStr.append("- " + differ.getKey() + ": " + toStringExceptNull(differ.getOldValue()) + "\n");
                    break;
                case NOT_CHANGED:
                    outStr.append("  " + differ.getKey() + ": " + toStringExceptNull(differ.getOldValue()) + "\n");
                    break;
                case UPDATED:
                    outStr.append("- " + differ.getKey() + ": " + toStringExceptNull(differ.getOldValue()) + "\n");
                    outStr.append("+ " + differ.getKey() + ": " + toStringExceptNull(differ.getNewValue()) + "\n");
                    break;
                case ADDED:
                    outStr.append("+ " + differ.getKey() + ": " + toStringExceptNull(differ.getNewValue()) + "\n");
                default:
                    break;
            }
        }

        outStr.append("}");
        return outStr.toString();
    }
}
