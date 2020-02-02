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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.kyori.feature.Feature;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An abstract implementation of a filter that determines its response from the responses of the children filters.
 */
public abstract class MultiFilter implements Filter {
  private static final Collector<Filter, ?, List<Filter>> COLLECTOR = Collectors.toCollection(ArrayList::new);
  protected final List<? extends Filter> filters;

  protected MultiFilter(final @NonNull Stream<? extends Filter> filters) {
    this.filters = filters
      .filter(filter -> !filter.equals(StaticFilter.ABSTAIN)) // remove any filters that always abstain
      .collect(COLLECTOR);
  }

  @Override
  public @NonNull Stream<? extends Feature> dependencies() {
    return this.filters.stream();
  }

  @Override
  public @NonNull String toString() {
    return this.getClass().getSimpleName() + "{filters=" + this.filters + '}';
  }

  @Override
  public boolean equals(final @Nullable Object other) {
    if(this == other) return true;
    if(!(other instanceof MultiFilter)) return false;
    final MultiFilter that = (MultiFilter) other;
    return Objects.equals(this.filters, that.filters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.filters);
  }
}
