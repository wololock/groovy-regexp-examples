package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Pattern

@State(Scope.Benchmark)
class A3_Match_Operator_Bench {

    private String input = "1605-FACD-0000-EXIT"

    private Pattern pattern = ~/^\d{4}-[A-Z]{4}-0000-EXIT$/

    @Benchmark
    void match_operator_dynamic() {
        assert input ==~ pattern
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void match_operator_static() {
        assert input ==~ pattern
    }

    @Benchmark
    void matcher_matches_dynamic() {
        def matcher = pattern.matcher(input)

        assert matcher.matches()
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void matcher_matches_static() {
        def matcher = pattern.matcher(input)

        assert matcher.matches()
    }
}
