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

package io.github.ma1uta.matrix.events;

import io.github.ma1uta.matrix.EventContent;

/**
 * Raw message for all unknown messages.
 */
public class RawEventContent implements EventContent {

    public RawEventContent(Object content, String type) {
        this.content = content;
        this.type = type;
    }

    /**
     * Content.
     */
    private Object content;

    /**
     * Event content type.
     */
    private String type;

    public Object getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}