import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {


    private static Predicate<String> stringPredicate;

    public static void main(String[] args) {


        Map<String, Set<String>> propertiesGroupedBy =
                PropertiesUtil.getInstance().getAllPropertyNames().stream()
                        .collect(groupingBy(s -> s.substring(0, s.indexOf('.')), Collectors.toSet()));


        System.out.println("----");


        isValid(propertiesGroupedBy, "ct");

    }

    private static void isValid(Map<String, Set<String>> propertiesGroupedBy, String key) {

        stringPredicate = s -> {
            final String property = PropertiesUtil.getInstance().getProperty(s);
            return (property == null || property.isEmpty());
        };


        final String propertyKey = propertiesGroupedBy.get(key).stream().filter(stringPredicate).findFirst().get();

        if (propertyKey != null) {
            try {
                throw new Exception(propertyKey + " value is null or empty ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
