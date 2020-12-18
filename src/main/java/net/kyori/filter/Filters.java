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

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Filters.
 *
 * @since 1.0.0
 */
public interface Filters {
  /**
   * Creates filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an all filter
   * @since 1.0.0
   */
  static @NonNull Filter all(final @NonNull Filter... filters) {
    return all(Stream.of(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an all filter
   * @since 1.0.0
   */
  static @NonNull Filter all(final @NonNull Iterable<? extends Filter> filters) {
    return all(StreamSupport.stream(filters.spliterator(), false));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an all filter
   * @since 1.0.0
   */
  static @NonNull Filter all(final @NonNull Stream<? extends Filter> filters) {
    return new AllFilter(filters);
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an any filter
   * @since 1.0.0
   */
  static @NonNull Filter any(final @NonNull Filter... filters) {
    return any(Stream.of(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an any filter
   * @since 1.0.0
   */
  static @NonNull Filter any(final @NonNull Iterable<? extends Filter> filters) {
    return any(StreamSupport.stream(filters.spliterator(), false));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an any filter
   * @since 1.0.0
   */
  static @NonNull Filter any(final @NonNull Stream<? extends Filter> filters) {
    return new AnyFilter(filters);
  }

  /**
   * Creates a filter that returns the inverse response.
   *
   * @param filter a filter
   * @return a not filter
   * @since 1.0.0
   */
  static @NonNull Filter not(final @NonNull Filter filter) {
    if(filter instanceof NotFilter) return ((NotFilter) filter).filter;
    return new NotFilter(filter);
  }

  /**
   * Gets a filter that always responds with {@code response}.
   *
   * @param response the response to always respond with
   * @return a filter
   * @since 1.0.0
   */
  static @NonNull Filter always(final @NonNull FilterResponse response) {
    if(response == FilterResponse.ALLOW) {
      return allow();
    } else if(response == FilterResponse.ABSTAIN) {
      return abstain();
    } else if(response == FilterResponse.DENY) {
      return deny();
    } else {
      throw new IllegalArgumentException(String.format("Unknown filter response '%s'", response.name()));
    }
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#ALLOW}.
   *
   * @return a filter
   * @since 1.0.0
   */
  static @NonNull Filter allow() {
    return StaticFilter.ALLOW;
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#ABSTAIN}.
   *
   * @return a filter
   * @since 1.0.0
   */
  static @NonNull Filter abstain() {
    return StaticFilter.ABSTAIN;
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#DENY}.
   *
   * @return a filter
   * @since 1.0.0
   */
  static @NonNull Filter deny() {
    return StaticFilter.DENY;
  }
}
