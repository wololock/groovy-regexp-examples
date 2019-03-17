package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Pattern

@State(Scope.Benchmark)
class A1_Create_Pattern_Bench {

    @Benchmark
    void pattern_operator_dynamic() {
        def pattern = ~/([Gg])roovy/

        assert pattern.class.equals(Pattern)
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void pattern_operator_sstatic() {
        def pattern = ~/([Gg])roovy/

        assert pattern.class.equals(Pattern)
    }

    @Benchmark
    void pattern_compile_dynamic() {
        def pattern = Pattern.compile(/([Gg])roovy/)

        assert pattern.class.equals(Pattern)
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void pattern_compile_static() {
        def pattern = Pattern.compile(/([Gg])roovy/)

        assert pattern.class.equals(Pattern)
    }
}
