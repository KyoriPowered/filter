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

import net.kyori.mu.examine.Examinable;
import net.kyori.mu.examine.ExaminableProperty;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.stream.Stream;

/**
 * A filter that returns a static response.
 */
public final class StaticFilter implements Examinable, Filter {
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

  public StaticFilter(final @NonNull FilterResponse response) {
    this.response = response;
  }

  @Override
  public @NonNull FilterResponse query(final @NonNull FilterQuery query) {
    return this.response;
  }

  @Override
  public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
    return Stream.of(ExaminableProperty.of("response", this.response));
  }
}
