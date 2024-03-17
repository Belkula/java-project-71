package hexlet.code.formatters;

import hexlet.code.Differs;

import java.util.List;

public class Plain {
	
		public static final String REMOVED = "removed";
		public static final String ADDED = "added";
		public static final String UPDATED = "updated";
		public static final String NOT_CHANGED = "not Changed";

	private static String ComplexValues(Object value) {
		if (value == null) {
			return null;
		}

		if (value.toString().contains("[") || value.toString().contains("{")) {
			return "[complex value]";
		} else if (value instanceof String) {
			if ("null".equals(value)) {
				return null;
			}
			value = "'" + value + "'";
			return (String) value;
		} else {
			return value.toString();
		}
	}

    public static String formatPlain(List<Differs> output) {
        StringBuilder outStr = new StringBuilder();

        for (Differs differ: output) {
			switch (differ.getStatus()) {
				case REMOVED:
					outStr.append("Property '" + differ.getKey() + "' was removed\n");
					break;
				case UPDATED:
					outStr.append("Property '" + differ.getKey() + "' was updated. From " + ComplexValues(differ.getOldValue()) + " to " + ComplexValues(differ.getNewValue()) + "\n");
					break;
				case ADDED:
					outStr.append("Property '" + differ.getKey() + "' was added with value: " + ComplexValues(differ.getNewValue()) + "\n");
					break;
				default:
					break;
			}
        }

        return outStr.toString();
    }

}