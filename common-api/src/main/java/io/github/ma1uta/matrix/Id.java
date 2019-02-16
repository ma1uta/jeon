/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Matrix id (MXID) util class.
 */
public abstract class Id implements CharSequence {

    private Collection<IdParseException> errors;

    /**
     * Provides a sigil symbol.
     *
     * @return sigil.
     */
    public abstract char getSigil();

    /**
     * Parse the input string and return a mxid instance.
     *
     * @param mxid mxid in string format.
     * @return mxid instance.
     */
    public static Id valueOf(String mxid) {
        return IdParser.getInstance().parse(mxid);
    }

    /**
     * Is valid mxid or not.
     *
     * @return {@code true} if mxid valid else {@code false}.
     */
    public boolean isValid() {
        return errors() == null || errors().isEmpty();
    }

    /**
     * Return all mxid violations.
     *
     * @return mxid spec violations.
     */
    public Collection<IdParseException> errors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    /**
     * Add a new mxid mistake.
     *
     * @param errors spec violation.
     */
    public void errors(List<IdParseException> errors) {
        this.errors = new ArrayList<>(errors);
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    /**
     * Mismatches with the specification.
     */
    public static class IdParseException extends RuntimeException {

        public IdParseException(String message) {
            super(message);
        }
    }
}
