package bench

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Matcher
import java.util.regex.Pattern

@State(Scope.Benchmark)
class A3_Regexp_Replace_Bench {

    private static final Random random = new Random()

    private static final List<String> data = [
            "v3.4.23",
            "v3.4.24",
            "v3.4.25",
            "v3.4.26",
            "v3.5.0",
            "v3.5.1",
            "v3.5.2",
            "v3.5.3",
            "v3.5.4",
            "v3.5.5",
            "v4.0.0",
            "v4.0.1",
            "v4.0.2",
            "v4.0.3",
            "v4.0.4",
            "v4.1.0",
            "v4.1.1",
            "v4.1.2",
            "v4.1.3"
    ]

    private static final Pattern pattern = ~/^v(\d{1,3})\.(\d{1,3})\.\d{1,4}$/

    private static String getVersion() {
        return data.get(random.nextInt(data.size()))
    }

    @Benchmark
    String string_replace_first_dynamic() {
        return version.replaceFirst(pattern) { _,major,minor -> "v${major}.${(minor as int) + 1}.0"}
    }

    @Benchmark
    @CompileStatic
    String string_replace_first_static() {
        return version.replaceFirst(pattern) { _,major,minor -> "v${major}.${(minor as int) + 1}.0"}
    }

    @Benchmark
    String matcher_matches_use_case_dynamic() {
        final Matcher matcher = pattern.matcher(version)
        if (!matcher.matches()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def major = matcher.group(1)
        def minor = matcher.group(2)

        return "v${major}.${(minor as int) + 1}.0".toString()
    }

    @Benchmark
    @CompileStatic
    String matcher_matches_use_case_static() {
        final Matcher matcher = pattern.matcher(version)
        if (!matcher.matches()) {
            throw new IllegalStateException("Pattern didn't match!")
        }

        def major = matcher.group(1)
        def minor = matcher.group(2)

        return "v${major}.${(minor as int) + 1}.0".toString()
    }
}

