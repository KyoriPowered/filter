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

import java.util.List;

public class FilterResponseResolver {
  // The quick response value is returned immediately if encountered while querying filters.
  private final FilterResponse immediateResponse;
  // The delayed response is the final response if it is encountered while querying filters and the quick response is not encountered.
  private final FilterResponse delayedResponse;

  public FilterResponseResolver(final @NonNull FilterResponse immediateResponse, final @NonNull FilterResponse delayedResponse) {
    this.immediateResponse = immediateResponse;
    this.delayedResponse = delayedResponse;
  }

  public @NonNull FilterResponse resolve(final @NonNull List<Filter> filters, final @NonNull FilterQuery query) {
    FilterResponse delayedResponse = FilterResponse.ABSTAIN;
    for(final Filter filter : filters) {
      final FilterResponse response = filter.query(query);
      if(response == this.immediateResponse) {
        return response;
      } else if(response == this.delayedResponse) {
        delayedResponse = response;
      }
    }
    return delayedResponse;
  }
}
