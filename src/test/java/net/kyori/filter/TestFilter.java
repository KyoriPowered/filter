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

abstract class TestFilter implements TypedFilter.Strong<TestFilter.Query> {
  final int number;

  TestFilter(final int number) {
    this.number = number;
  }

  @Override
  public boolean queryable(final @NonNull FilterQuery query) {
    return query instanceof Query;
  }

  static class Below extends TestFilter {
    Below(final int value) {
      super(value);
    }

    @Override
    public boolean queryResponse(final TestFilter.@NonNull Query query) {
      return query.number() < this.number;
    }
  }

  static class Equals extends TestFilter {
    Equals(final int number) {
      super(number);
    }

    @Override
    public boolean queryResponse(final @NonNull Query query) {
      return this.number == query.number();
    }
  }

  static class Above extends TestFilter {
    Above(final int value) {
      super(value);
    }

    @Override
    public boolean queryResponse(final TestFilter.@NonNull Query query) {
      return query.number() > this.number;
    }
  }

  public interface Query extends FilterQuery {
    static Query of(final int number) {
      return () -> number;
    }

    int number();
  }
}
