package ru.test.task.customercontacts.model;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum ContactType implements Predicate<String> {

    EMAIL {
        @Override
        public boolean test(String s) {
            if (!StringUtils.hasText(s)) {
                return false;
            }
            return s.contains("@");
        }
    },
    PHONE {
        @Override
        public boolean test(String s) {
            if (!StringUtils.hasText(s)) {
                return false;
            }
            return PHONE_NUMBER.matcher(s).matches();
        }
    };
    private static final Pattern PHONE_NUMBER = Pattern.compile("^\\+?[\\d\\-()\\s]{8,15}$");
    private static final Set<ContactType> allTypes;

    static {
        ContactType[] values = values();
        ContactType[] rest = new ContactType[values.length - 1];
        System.arraycopy(values, 1, rest, 0, rest.length);
        EnumSet<ContactType> all = EnumSet.of(values[0], rest);
        allTypes = Collections.unmodifiableSet(all);
    }

    public static Set<ContactType> all() {
        return allTypes;
    }

    @Override
    public boolean test(String s) {
        return StringUtils.hasText(s);
    }
}
