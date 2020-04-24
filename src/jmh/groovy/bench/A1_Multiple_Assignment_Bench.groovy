package bench

import groovy.transform.CompileStatic
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Matcher
import java.util.regex.Pattern

@State(Scope.Benchmark)
class A1_Multiple_Assignment_Bench {

    private static final Random random = new Random()

    private static final List<String> data = [
            'Some item name 1: $99.99 (-15%)',
            'Some item name 2: $49.99 (-5%)',
            'Some item name 3: $19.99',
            'Some item name 4: $29.99 (-13%)',
            'Some item name 5: $9.99',
            'Some item name 6: $51.21 (-2%)',
            'Some item name 7: $4.32 (-1%)',
            'Some item name 8: $3.14 (-23%)',
            'Some item name 9: $9.00'
    ]

    private static final Pattern pattern = ~/\$(\d{1,4}\.\d{2})\s?\(?(-\d+%)?\)?/

    private static final String getInput() {
        return data.get(random.nextInt(data.size()))
    }

    @Benchmark
    String multiple_assignment_dynamic() {
        def (_,price,discount) = (input =~ pattern)[0]

        return "price: $price, discount: $discount"
    }

    @Benchmark
    @CompileStatic
    String multiple_assignment_static() {
        final List list = (input =~ pattern)[0] as ArrayList

        def (_, price, discount) = [list[0], list[1], list[2]]

        return "price: $price, discount: $discount"
    }

    @Benchmark
    String standard_matcher_dynamic() {
        final Matcher matcher = pattern.matcher(input)
        if (!matcher.find()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def price = matcher.group(1)
        def discount = matcher.group(2)

        return "price: $price, discount: $discount"
    }

    @Benchmark
    @CompileStatic
    String standard_matcher_static() {
        final Matcher matcher = pattern.matcher(input)
        if (!matcher.find()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def price = matcher.group(1)
        def discount = matcher.group(2)

        return "price: $price, discount: $discount"
    }
}
