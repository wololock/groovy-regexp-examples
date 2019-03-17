package bench


import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import java.util.regex.Matcher
import java.util.regex.Pattern

@State(Scope.Benchmark)
class A2_Create_Matcher_Bench {

    private Pattern pattern = ~/\S+er\b/

    private String longText = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore 
eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt 
mollit anim id est laborum. My code is groovier and better when I use Groovy there. Sed ut perspiciatis unde omnis 
iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, 
eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam 
voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem 
sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed 
quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima 
veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis 
autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem 
eum fugiat quo voluptas nulla pariatur?
At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque 
corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in 
culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et 
expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id 
quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem 
quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et 
molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus 
maiores alias consequatur aut perferendis doloribus asperiores repellat.
"""
    private String shortText = "My code is groovier and better when I use Groovy there"

    @Benchmark
    void long_text_find_operator_dynamic() {
        def matcher = longText =~ pattern

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void long_text_find_operator_static() {
        def matcher = longText =~ pattern

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    void long_text_pattern_matches_dynamic() {
        def matcher = pattern.matcher(longText)

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void long_text_pattern_matches_static() {
        def matcher = pattern.matcher(longText)

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    void long_text_tokenize_dynamic() {
        def result = longText.tokenize().findAll { it.endsWith("er") }

        assert result.equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void long_text_tokenize_static() {
        def result = longText.tokenize().findAll { it.endsWith("er") }

        assert result.equals(['groovier', 'better'])
    }

    @Benchmark
    void short_text_find_operator_dynamic() {
        def matcher = shortText =~ pattern

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void short_text_find_operator_static() {
        def matcher = shortText =~ pattern

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    void short_text_pattern_matches_dynamic() {
        def matcher = pattern.matcher(shortText)

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void short_text_pattern_matches_static() {
        def matcher = pattern.matcher(shortText)

        assert matcher.class.equals(Matcher)

        assert matcher[0..-1].equals(['groovier', 'better'])
    }

    @Benchmark
    void short_text_tokenize_dynamic() {
        def result = shortText.tokenize().findAll { it.endsWith("er") }

        assert result.equals(['groovier', 'better'])
    }

    @Benchmark
    @CompileStatic
    @TypeChecked
    void short_text_tokenize_static() {
        def result = shortText.tokenize().findAll { it.endsWith("er") }

        assert result.equals(['groovier', 'better'])
    }
}
