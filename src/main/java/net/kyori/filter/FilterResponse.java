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

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A response from querying a {@link Filter}.
 */
public enum FilterResponse {
  ALLOW {
    @Override
    public @NonNull FilterResponse inverse() {
      return DENY;
    }
  },
  ABSTAIN {
    @Override
    public @NonNull FilterResponse inverse() {
      return ABSTAIN;
    }
  },
  DENY {
    @Override
    public @NonNull FilterResponse inverse() {
      return ALLOW;
    }
  };

  /**
   * Gets the inverse response.
   *
   * @return the inverse response
   */
  public abstract @NonNull FilterResponse inverse();

  /**
   * Gets a response from a boolean.
   *
   * @param bool the boolean
   * @return the response
   */
  public static @NonNull FilterResponse fromBoolean(final boolean bool) {
    return bool ? ALLOW : DENY;
  }
}
