/*
 * Copyright Anatoliy Sablin tolya@sablin.xyz
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

package io.github.ma1uta.matrix.event;

import io.github.ma1uta.matrix.event.content.RawEventContent;

import java.util.Map;

/**
 * Raw message for all unknown messages.
 */
public class RawEvent extends Event<RawEventContent> {

    private String type;

    private Object properties;

    public RawEvent(Map props, String type) {
        this.type = type;
        this.properties = props;
    }

    @Override
    public String getType() {
        return type;
    }

    public Object getProperties() {
        return properties;
    }
}
