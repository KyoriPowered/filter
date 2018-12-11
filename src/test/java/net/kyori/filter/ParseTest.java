/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2018 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.filter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.kyori.filter.data.TestFilter;
import net.kyori.filter.data.TestQuery1;
import net.kyori.filter.parser.FilterBinder;
import net.kyori.filter.parser.FilterModule;
import net.kyori.filter.parser.FilterParser;
import net.kyori.lambda.Composer;
import net.kyori.lambda.function.ThrowingSupplier;
import net.kyori.violet.AbstractModule;
import net.kyori.xml.document.factory.DocumentFactory;
import net.kyori.xml.node.Node;
import net.kyori.xml.node.parser.Parser;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParseTest {
  private final Node node = Composer.get(ThrowingSupplier.of(() -> {
    final Document document = DocumentFactory.builder()
      .builder(new SAXBuilder())
      .build()
      .read(Paths.get(ParseTest.class.getResource("/test.xml").toURI()));
    return Node.of(document.getRootElement());
  }));
  private final Injector injector = Guice.createInjector(new AbstractModule() {
    @Override
    protected void configure() {
      this.install(new FilterModule());

      final FilterBinder filters = new FilterBinder(this.binder());
      filters.bind("test").toInstance(node -> new TestFilter(Integer.parseInt(node.value())));
    }
  });
  private final Parser<Filter> parser = this.injector.getInstance(FilterParser.class);

  @Test
  void testAll() {
    final Filter good = this.parser.parse(this.node.node("a").get().node("all").get());
    assertTrue(good.allows(TestQuery1.of(1024)));
    assertTrue(good.denies(TestQuery1.of(2048)));
    final Filter bad = this.parser.parse(this.node.node("b").get().node("all").get());
    assertTrue(bad.denies(TestQuery1.of(1024)));
    assertTrue(bad.denies(TestQuery1.of(2048)));
  }

  @Test
  void testAny() {
    final Filter good = this.parser.parse(this.node.node("a").get().node("any").get());
    assertTrue(good.allows(TestQuery1.of(1024)));
    assertTrue(good.allows(TestQuery1.of(2048)));
    assertTrue(good.denies(TestQuery1.of(4096)));
  }

  @Test
  void testNot() {
    final Filter once = this.parser.parse(this.node.node("a").get().node("not").get());
    assertTrue(once.denies(TestQuery1.of(1024)));
    assertTrue(once.allows(TestQuery1.of(2048)));
    final Filter twice = this.parser.parse(this.node.node("b").get().node("not").get());
    assertTrue(twice.allows(TestQuery1.of(1024)));
    assertTrue(twice.denies(TestQuery1.of(2048)));
  }

  @Test
  void testStatic() {
    final Filter allow = this.parser.parse(this.node.node("a").get().node("always").get());
    assertEquals(FilterResponse.ALLOW, allow.query(TestQuery1.of(ThreadLocalRandom.current().nextInt())));
    assertEquals(FilterResponse.ALLOW, allow.query(TestQuery1.of(ThreadLocalRandom.current().nextInt())));
    final Filter deny = this.parser.parse(this.node.node("a").get().node("never").get());
    assertEquals(FilterResponse.DENY, deny.query(TestQuery1.of(ThreadLocalRandom.current().nextInt())));
    assertEquals(FilterResponse.DENY, deny.query(TestQuery1.of(ThreadLocalRandom.current().nextInt())));
  }
}