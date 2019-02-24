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

package io.github.ma1uta.matrix.server.model.federation.edu;

import io.github.ma1uta.matrix.server.model.federation.edu.content.ReceiptContent;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * An EDU representing receipt updates for users of the sending homeserver. When receiving receipts, the server should only update entries
 * that are listed in the EDU. Receipts previously received that do not appear in the EDU should not be removed or otherwise manipulated.
 */
@Schema(
    description = "An EDU representing receipt updates for users of the sending homeserver. When receiving receipts, the server"
        + " should only update entries that are listed in the EDU. Receipts previously received that do not appear in the EDU"
        + " should not be removed or otherwise manipulated."
)
public class Receipt extends EphemeralDataUnit<ReceiptContent> {

    /**
     * EDU type.
     */
    public static final String TYPE = "m.receipt";

    @Override
    public String getEduType() {
        return TYPE;
    }
}
