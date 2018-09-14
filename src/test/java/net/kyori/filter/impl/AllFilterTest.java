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

import net.kyori.filter.Filter;
import net.kyori.filter.FilterResponse;
import net.kyori.filter.TestFilter;
import net.kyori.filter.TestQuery;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllFilterTest {
  @Test
  void testAllow() {
    final List<Filter> filters = new ArrayList<>();
    filters.add(new TestFilter(10));
    filters.add(new TestFilter(10));
    final AllFilter filter = new AllFilter(filters);
    assertEquals(FilterResponse.ALLOW, filter.query((TestQuery) () -> 10));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 15));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 20));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 25));
  }

  @Test
  void testDeny() {
    final List<Filter> filters = new ArrayList<>();
    filters.add(new TestFilter(10));
    filters.add(new TestFilter(20));
    final AllFilter filter = new AllFilter(filters);
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 10));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 15));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 20));
    assertEquals(FilterResponse.DENY, filter.query((TestQuery) () -> 25));
  }
}
