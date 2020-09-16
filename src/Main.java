import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static javax.swing.plaf.basic.BasicHTML.propertyKey;

public class Main {


    private static Predicate<String> stringPredicate;

    public static void main(String[] args) {


        Map<String, Set<String>> propertiesGroupedBy =
                PropertiesUtil.getInstance().getAllPropertyNames().stream()
                        .collect(groupingBy(s -> s.substring(0, s.indexOf('.')), Collectors.toSet()));

        System.out.println("----");


        isValid(propertiesGroupedBy, "ct");
        isValid(propertiesGroupedBy, "pv");
        isValid(propertiesGroupedBy, "ev");


    }

    private static void isValid(Map<String, Set<String>> propertiesGroupedBy, String key) {


        // Just only checking for is null or empty
        stringPredicate = s -> {
            final String property = PropertiesUtil.getInstance().getProperty(s);
            return (property == null || property.isEmpty());
        };

        //but you can write more predicates as per your needs


        final Optional<String> optional = propertiesGroupedBy.get(key).stream().filter(stringPredicate).findFirst();

        if (optional.isPresent()) {
            try {
                throw new Exception(optional.get() + " value is null or empty ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
