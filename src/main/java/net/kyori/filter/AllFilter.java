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

import java.util.List;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
 *
 * @since 1.0.0
 */
public final class AllFilter extends MultiFilter {
  AllFilter(final @NonNull Stream<? extends Filter> filters) {
    super(filters);
  }

  @Override
  @SuppressWarnings("ForLoopReplaceableByForEach")
  public @NonNull FilterResponse query(final @NonNull FilterQuery query) {
    FilterResponse response = FilterResponse.ABSTAIN;
    final List<? extends Filter> filters = this.filters;
    for(int i = 0, size = filters.size(); i < size; i++) {
      final FilterResponse reply = filters.get(i).query(query);
      if(reply == FilterResponse.ALLOW) {
        response = FilterResponse.ALLOW;
      } else if(reply == FilterResponse.DENY) {
        return FilterResponse.DENY;
      }
    }
    return response;
  }
}
