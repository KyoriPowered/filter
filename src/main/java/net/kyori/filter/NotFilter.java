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
import net.kyori.mu.examine.Examinable;
import net.kyori.mu.examine.ExaminableProperty;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A filter that returns the inverse response.
 */
public final class NotFilter implements Examinable, Filter {
  private final Filter filter;

  public NotFilter(final @NonNull Filter filter) {
    this.filter = filter;
  }

  @Override
  public @NonNull FilterResponse query(final @NonNull FilterQuery query) {
    return this.filter.query(query).inverse();
  }

  @Override
  public @NonNull Stream<? extends Component> dependencies() {
    return Stream.of(this.filter);
  }

  @Override
  public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
    return Stream.of(ExaminableProperty.of("filter", this.filter));
  }

  @Override
  public boolean equals(final Object other) {
    if(this == other) return true;
    if(other == null || this.getClass() != other.getClass()) return false;
    final NotFilter that = (NotFilter) other;
    return Objects.equals(this.filter, that.filter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.filter);
  }
}
