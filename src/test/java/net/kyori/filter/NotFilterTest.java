/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2018-2020 KyoriPowered
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

import com.google.common.testing.EqualsTester;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotFilterTest {
  @Test
  void testQuery() {
    final Filter filter = Filters.not(
      new TestFilter.Equals(20)
    );
    assertTrue(filter.allows(TestFilter.Query.of(10)));
    assertTrue(filter.allows(TestFilter.Query.of(15)));
    assertTrue(filter.denies(TestFilter.Query.of(20)));
  }

  @Test
  void testNotNotIsOriginal() {
    final Filter f0 = new TestFilter.Equals(0);
    final Filter n0 = Filters.not(f0);
    final Filter n1 = Filters.not(n0);
    assertSame(f0, n1);
  }

  @Test
  void testDependencies() {
    final TestFilter f0 = new TestFilter.Equals(0);
    final Filter n0 = Filters.not(f0);
    assertThat(n0.dependencies()).containsExactly(f0);
  }

  @Test
  void testEquality() {
    final TestFilter f0 = new TestFilter.Equals(0);
    final TestFilter f1 = new TestFilter.Equals(1);
    new EqualsTester()
      .addEqualityGroup(
        Filters.not(f0),
        Filters.not(f0)
      )
      .addEqualityGroup(
        Filters.not(f1),
        Filters.not(f1)
      )
      .testEquals();
  }
}
