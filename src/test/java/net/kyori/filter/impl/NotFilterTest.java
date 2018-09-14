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
package net.kyori.filter.impl;

import net.kyori.filter.FilterResponse;
import net.kyori.filter.TestFilter;
import net.kyori.filter.TestQuery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFilterTest {
  @Test
  void testNot() {
    final TestFilter filter = new TestFilter(69);
    assertEquals(FilterResponse.ALLOW, filter.query((TestQuery) () -> 69));
    assertEquals(FilterResponse.DENY, new NotFilter(filter).query((TestQuery) () -> 69));
  }

  @Test
  void testNotNot() {
    final TestFilter filter = new TestFilter(69);
    assertEquals(FilterResponse.ALLOW, filter.query((TestQuery) () -> 69));
    assertEquals(FilterResponse.ALLOW, new NotFilter(new NotFilter(filter)).query((TestQuery) () -> 69));
  }
}
