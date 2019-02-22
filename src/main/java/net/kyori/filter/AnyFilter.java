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

import net.kyori.component.Component;
import net.kyori.mu.collection.MuIterables;
import net.kyori.mu.examine.ExaminableProperty;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.stream.Stream;

/**
 * A filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
 */
public final class AnyFilter implements Filter {
  private final Iterable<? extends Filter> filters;

  public AnyFilter(final @NonNull Iterable<? extends Filter> filters) {
    this.filters = filters;
  }

  @Override
  public @NonNull FilterResponse query(final @NonNull FilterQuery query) {
    FilterResponse response = FilterResponse.ABSTAIN;
    for(final Filter filter : this.filters) {
      switch(filter.query(query)) {
        case ALLOW: return FilterResponse.ALLOW;
        case DENY: response = FilterResponse.DENY;
      }
    }
    return response;
  }

  @Override
  public @NonNull Stream<? extends Component> dependencies() {
    return MuIterables.stream(this.filters);
  }

  @Override
  public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
    return Stream.of(ExaminableProperty.of("filters", this.filters));
  }
}
