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

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A filter that returns a static response.
 */
public final class StaticFilter implements Filter {
  /**
   * A filter that always returns with {@link FilterResponse#ALLOW}.
   */
  static final StaticFilter ALLOW = new StaticFilter(FilterResponse.ALLOW);
  /**
   * A filter that always returns with {@link FilterResponse#ABSTAIN}.
   */
  static final StaticFilter ABSTAIN = new StaticFilter(FilterResponse.ABSTAIN);
  /**
   * A filter that always returns with {@link FilterResponse#DENY}.
   */
  static final StaticFilter DENY = new StaticFilter(FilterResponse.DENY);
  private final FilterResponse response;

  private StaticFilter(final @NonNull FilterResponse response) {
    this.response = response;
  }

  @Override
  public @NonNull FilterResponse query(final @NonNull FilterQuery query) {
    return this.response;
  }

  @Override
  public @NonNull String toString() {
    return "StaticFilter{response=" + this.response + '}';
  }

  @Override
  public boolean equals(final Object other) {
    if(this == other) return true;
    if(other == null || this.getClass() != other.getClass()) return false;
    final StaticFilter that = (StaticFilter) other;
    return Objects.equals(this.response, that.response);
  }

  @Override
  public int hashCode() {
    return this.response.hashCode();
  }
}
