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
package net.kyori.filter.parser;

import net.kyori.filter.Filter;
import net.kyori.xml.XMLException;
import net.kyori.xml.node.Node;
import net.kyori.xml.node.parser.Parser;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class FilterParser implements Parser<Filter> {
  private final Map<String, Parser<? extends Filter>> parsers;

  @Inject
  public FilterParser(final Map<String, Parser<? extends Filter>> parsers) {
    this.parsers = parsers;
  }

  @Override
  public @NonNull Filter throwingParse(@NonNull final Node node) throws XMLException {
    final /* @Nullable */ Parser<? extends Filter> parser = this.parsers.get(node.name());
    if(parser != null) {
      return parser.parse(node);
    } else {
      throw new XMLException("Could not find filter parser with name '" + node.name() + '\'');
    }
  }
}
