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

import com.google.common.testing.EqualsTester;
import net.kyori.filter.data.TestQuery1;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticFilterTest {
  @Test
  void testQuery_allow() {
    for(int i = 0; i < 10; i++) {
      assertEquals(FilterResponse.ALLOW, Filters.allow().query(TestQuery1.of(i)));
    }
  }

  @Test
  void testQuery_abstain() {
    for(int i = 0; i < 10; i++) {
      assertEquals(FilterResponse.ABSTAIN, Filters.abstain().query(TestQuery1.of(i)));
    }
  }

  @Test
  void testQuery_deny() {
    for(int i = 0; i < 10; i++) {
      assertEquals(FilterResponse.DENY, Filters.deny().query(TestQuery1.of(i)));
    }
  }

  @Test
  void testDependencies() {
    assertThat(Filters.allow().dependencies()).isEmpty();
    assertThat(Filters.abstain().dependencies()).isEmpty();
    assertThat(Filters.deny().dependencies()).isEmpty();
  }

  @Test
  void testExaminableProperties() {
    assertThat(Filters.allow().examinableProperties()).hasSize(1);
    assertThat(Filters.abstain().examinableProperties()).hasSize(1);
    assertThat(Filters.deny().examinableProperties()).hasSize(1);
  }

  @Test
  void testEquality() {
    new EqualsTester()
      .addEqualityGroup(Filters.allow(), Filters.allow())
      .addEqualityGroup(Filters.abstain(), Filters.abstain())
      .addEqualityGroup(Filters.deny(), Filters.deny())
      .testEquals();
  }
}
