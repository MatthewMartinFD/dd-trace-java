package datadog.trace.util

import datadog.trace.test.util.DDSpecification

class StringsTest extends DDSpecification {

  def "test join strings"() {
    when:
    String s = Strings.join(joiner, strings)
    then:
    s == expected
    where:
    // spotless:off
    joiner | strings         | expected
    ","    | ["a", "b", "c"] | "a,b,c"
    ","    | ["a", "b"]      | "a,b"
    ","    | ["a"]           | "a"
    ","    | []              | ""
    // spotless:on
  }

  def "test join strings varargs"() {
    when:
    // apparently groovy doesn't like this but it runs as if it's java
    String s = Strings.join(joiner, strings.toArray(new CharSequence[0]))
    then:
    s == expected
    where:
    // spotless:off
    joiner | strings         | expected
    ","    | ["a", "b", "c"] | "a,b,c"
    ","    | ["a", "b"]      | "a,b"
    ","    | ["a"]           | "a"
    ","    | []              | ""
    // spotless:on
  }

  def "test replace strings"() {
    when:
    String replacedAll = Strings.replace(string, delimiter, replacement)
    String replacedFirst = Strings.replaceFirst(string, delimiter, replacement)

    then:
    replacedAll == expected
    replacedFirst == expectedFirst

    where:
    // spotless:off
    string       | delimiter | replacement | expected          | expectedFirst
    "teststring" | "tstr"    | "a"         | "tesaing"         | "tesaing"
    "teststring" | "t"       | "a"         | "aesasaring"      | "aeststring"
    "teststring" | "t"       | ""          | "essring"         | "eststring"
    "teststring" | "z"       | "s"         | "teststring"      | "teststring"
    "tetetetete" | "t"       | "te"        | "teeteeteeteetee" | "teetetetete"
    "tetetetete" | "te"      | "t"         | "ttttt"           | "ttetetete"
    "tetetetete" | "tet"     | "e"         | "eeeete"          | "eetetete"
    // spotless:on
  }
}
