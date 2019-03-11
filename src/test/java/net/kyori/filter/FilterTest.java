/*
 * This file is part of filter, licensed under the MIT License.
 *
 * Copyright (c) 2018-2019 KyoriPowered
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

import net.kyori.filter.data.TestFilter;
import net.kyori.filter.data.TestQuery1;
import net.kyori.filter.data.TestQuery2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterTest {
  @Test
  void testQuery_allow() {
    final TestFilter f0 = new TestFilter(0);
    final FilterQuery q0 = TestQuery1.of(0);
    assertEquals(FilterResponse.ALLOW, f0.query(q0));
    assertTrue(f0.allows(q0));
  }

  @Test
  void testQuery_abstain() {
    final TestFilter f0 = new TestFilter(0);
    final FilterQuery q0 = TestQuery2.of();
    assertEquals(FilterResponse.ABSTAIN, f0.query(q0));
    assertTrue(f0.abstains(q0));
  }

  @Test
  void testQuery_deny() {
    final TestFilter f0 = new TestFilter(0);
    final FilterQuery q0 = TestQuery1.of(1);
    assertEquals(FilterResponse.DENY, f0.query(q0));
    assertTrue(f0.denies(q0));
  }
}
