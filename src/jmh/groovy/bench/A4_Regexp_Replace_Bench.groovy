package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Pattern

@State(Scope.Benchmark)
class A4_Regexp_Replace_Bench {

    private String version = "v3.4.23"

    private String expected = "v3.5.0"

    private Pattern pattern = ~/^v(\d{1,3})\.(\d{1,3})\.\d{1,4}$/

    @Benchmark
    void string_replace_first_dynamic() {
        def newVersion = version.replaceFirst(pattern) { _,major,minor -> "v${major}.${(minor as int) + 1}.0"}

        assert newVersion.equals(expected)
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void string_replace_first_static() {
        def newVersion = version.replaceFirst(pattern) { _,major,minor -> "v${major}.${(minor as int) + 1}.0"}

        assert newVersion.equals(expected)
    }

    @Benchmark
    void matcher_matches_use_case_dynamic() {
        def matcher = pattern.matcher(version)
        if (!matcher.matches()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def major = matcher.group(1)
        def minor = matcher.group(2)

        def newVersion = "v${major}.${(minor as int) + 1}.0".toString()

        assert newVersion.equals(expected)
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void matcher_matches_use_case_static() {
        def matcher = pattern.matcher(version)
        if (!matcher.matches()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def major = matcher.group(1)
        def minor = matcher.group(2)

        def newVersion = "v${major}.${(minor as int) + 1}.0".toString()

        assert newVersion.equals(expected)
    }
}

