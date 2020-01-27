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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TypedFilterTest {
  @Test
  void testQueryable() {
    final TestFilterA f0 = new TestFilterA();
    assertTrue(f0.queryable(TestQueryA.create()));
    assertFalse(f0.queryable(TestQueryB.create()));
    final TestFilterB f1 = new TestFilterB();
    assertFalse(f1.queryable(TestQueryA.create()));
    assertTrue(f1.queryable(TestQueryB.create()));
  }

  @Test
  void testQuery() {
    final TestFilterA f0 = new TestFilterA();
    assertTrue(f0.abstains(TestQueryB.create()));
    final TestFilterB f1 = new TestFilterB();
    assertTrue(f1.abstains(TestQueryA.create()));
  }

  private static class TestFilterA implements TypedFilter.Strong<TestQueryA> {
    @Override
    public boolean queryable(final @NonNull FilterQuery query) {
      return query instanceof TestQueryA;
    }

    @Override
    public boolean queryResponse(final @NonNull TestQueryA query) {
      throw new UnsupportedOperationException();
    }
  }

  private static class TestFilterB implements TypedFilter.Strong<TestQueryB> {
    @Override
    public boolean queryable(final @NonNull FilterQuery query) {
      return query instanceof TestQueryB;
    }

    @Override
    public boolean queryResponse(final @NonNull TestQueryB query) {
      throw new UnsupportedOperationException();
    }
  }

  private interface TestQuery extends FilterQuery {
  }

  private interface TestQueryA extends TestQuery {
    static TestQueryA create() {
      return new TestQueryA() {
      };
    }
  }

  private interface TestQueryB extends TestQuery {
    static TestQueryB create() {
      return new TestQueryB() {
      };
    }
  }
}
