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

import net.kyori.component.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A filter.
 */
public interface Filter extends Component {
  /**
   * Query this filter for a response.
   *
   * @param query the query
   * @return the response
   */
  @NonNull FilterResponse query(final @NonNull FilterQuery query);

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#ALLOW}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if allowed, {@code false} otherwise
   */
  default boolean allows(final @NonNull FilterQuery query) {
    return this.query(query) == FilterResponse.ALLOW;
  }

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#ABSTAIN}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if abstained, {@code false} otherwise
   */
  default boolean abstains(final @NonNull FilterQuery query) {
    return this.query(query) == FilterResponse.ABSTAIN;
  }

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#DENY}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if denied, {@code false} otherwise
   */
  default boolean denies(final @NonNull FilterQuery query) {
    return this.query(query) == FilterResponse.DENY;
  }
}
