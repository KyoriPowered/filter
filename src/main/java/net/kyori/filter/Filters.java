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

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;

/**
 * Filters.
 */
public interface Filters {
  /**
   * Creates filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an all filter
   */
  static @NonNull Filter all(final @NonNull Filter... filters) {
    return all(Arrays.asList(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an all filter
   */
  static @NonNull Filter all(final @NonNull Iterable<? extends Filter> filters) {
    return new AllFilter(filters);
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an any filter
   */
  static @NonNull Filter any(final @NonNull Filter... filters) {
    return any(Arrays.asList(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an any filter
   */
  static @NonNull Filter any(final @NonNull Iterable<? extends Filter> filters) {
    return new AnyFilter(filters);
  }

  /**
   * Creates a filter that returns the inverse response.
   *
   * @param filter a filter
   * @return a not filter
   */
  static @NonNull Filter not(final @NonNull Filter filter) {
    return new NotFilter(filter);
  }
}
