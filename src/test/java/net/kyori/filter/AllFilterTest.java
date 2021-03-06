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
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllFilterTest {
  @Test
  void testQuery() {
    assertTrue(Filters.all(
      new TestFilter.Equals(10),
      new TestFilter.Equals(10)
    ).allows(TestFilter.Query.of(10)));
    assertTrue(Filters.all(
      new TestFilter.Equals(10),
      new TestFilter.Equals(20)
    ).denies(TestFilter.Query.of(10)));
  }

  @Test
  void testDependencies() {
    final TestFilter f0 = new TestFilter.Equals(0);
    final TestFilter f1 = new TestFilter.Equals(1);
    final TestFilter f2 = new TestFilter.Equals(2);
    final Filter a0 = Filters.all(f0, f1);
    assertThat(a0.dependencies()).containsExactly(f0, f1).inOrder();
    final Filter a1 = Filters.all(f1, f2);
    assertThat(a1.dependencies()).containsExactly(f1, f2).inOrder();
  }

  @Test
  void testEquality() {
    final TestFilter f0 = new TestFilter.Equals(0);
    final TestFilter f1 = new TestFilter.Equals(1);
    final TestFilter f2 = new TestFilter.Equals(2);
    new EqualsTester()
      .addEqualityGroup(
        Filters.all(f0, f1),
        Filters.all(Arrays.asList(f0, f1)),
        Filters.all(Stream.of(f0, f1))
      )
      .addEqualityGroup(
        Filters.all(f1, f2),
        Filters.all(Arrays.asList(f1, f2)),
        Filters.all(Stream.of(f1, f2))
      )
      .testEquals();
  }
}
