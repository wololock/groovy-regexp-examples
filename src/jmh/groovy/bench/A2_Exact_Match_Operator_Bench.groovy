package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Matcher
import java.util.regex.Pattern

@State(Scope.Benchmark)
class A2_Exact_Match_Operator_Bench {

    private static final Random random = new Random()

    private static final List<String> data = [
            "1605-FACD-0000-EXIT",
            "1606-FACD-0000-EXIT",
            "1607-FACD-0000-EXIT",
            "1608-FACD-0000-EXIT",
            "1609-FACD-0000-EXIT",
            "1610-FACD-0000-EXIT",
            "1611-FACD-0000-EXIT",
            "1611-FACD-0001-EXIT",
            "1611-FACD-0002-EXIT",
            "1611-FACD-0003-EXIT",
            "1612-FACD-0000-EXIT"
    ]

    private static final Pattern pattern = ~/^\d{4}-[A-Z]{4}-0000-EXIT$/

    private static final String getInput() {
        return data.get(random.nextInt(data.size()))
    }

    @Benchmark
    boolean match_operator_dynamic() {
        return input ==~ pattern
    }

    @Benchmark
    @CompileStatic
    boolean match_operator_static() {
        return input ==~ pattern
    }

    @Benchmark
    boolean matcher_matches_dynamic() {
        final Matcher matcher = pattern.matcher(input)

        return matcher.matches()
    }

    @Benchmark
    @CompileStatic
    boolean matcher_matches_static() {
        final Matcher matcher = pattern.matcher(input)

        return matcher.matches()
    }
}
