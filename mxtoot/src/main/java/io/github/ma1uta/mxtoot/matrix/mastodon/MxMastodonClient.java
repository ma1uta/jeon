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

package io.github.ma1uta.mxtoot.matrix.mastodon;

import com.sys1yagi.mastodon4j.MastodonClient;
import com.sys1yagi.mastodon4j.api.Handler;
import com.sys1yagi.mastodon4j.api.Shutdownable;
import com.sys1yagi.mastodon4j.api.entity.Account;
import com.sys1yagi.mastodon4j.api.entity.Attachment;
import com.sys1yagi.mastodon4j.api.entity.Notification;
import com.sys1yagi.mastodon4j.api.entity.Status;
import com.sys1yagi.mastodon4j.api.entity.Tag;
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException;
import com.sys1yagi.mastodon4j.api.method.Statuses;
import com.sys1yagi.mastodon4j.api.method.Streaming;
import io.github.ma1uta.matrix.bot.BotHolder;
import io.github.ma1uta.matrix.bot.ShutdownListener;
import io.github.ma1uta.matrix.client.MatrixClient;
import io.github.ma1uta.mxtoot.matrix.MxTootConfig;
import io.github.ma1uta.mxtoot.matrix.MxTootDao;
import io.github.ma1uta.mxtoot.matrix.MxTootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Mastodon client.
 */
public class MxMastodonClient implements Handler, ShutdownListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MxMastodonClient.class);

    private final MastodonClient mastodonClient;
    private final BotHolder<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> holder;
    private Shutdownable shutdownable;
    private boolean runnings;

    public MxMastodonClient(MastodonClient mastodonClient,
                            BotHolder<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> holder) {
        this.mastodonClient = mastodonClient;
        this.holder = holder;
    }

    public MastodonClient getMastodonClient() {
        return mastodonClient;
    }

    public boolean isRunnings() {
        return runnings;
    }

    public BotHolder<MxTootConfig, MxTootDao, MxTootService<MxTootDao>, MxMastodonClient> getHolder() {
        return holder;
    }

    /**
     * Start streaming.
     *
     * @return {@code true} if has started streaming, else {@code false}.
     */
    public boolean streaming() {
        if (isRunnings()) {
            return true;
        }

        try {
            this.shutdownable = new Streaming(getMastodonClient()).user(this);
            this.runnings = true;
            return true;
        } catch (Mastodon4jRequestException e) {
            LOGGER.error("Failed streaming", e);
            return false;
        }
    }

    /**
     * Stop streaming.
     */
    public void shutdown() {
        if (this.shutdownable != null) {
            this.shutdownable.shutdown();
        }
        this.runnings = false;
    }

    @Override
    public void onDelete(long l) {

    }

    @Override
    public void onStatus(Status status) {
        getHolder().runInTransaction((holder, dao) -> {

            StringBuilder sb = writeStatus(status).append("</hr>");

            MatrixClient matrixClient = holder.getMatrixClient();
            MxTootConfig config = holder.getConfig();
            matrixClient.sendFormattedNotice(config.getRoomId(), sb.toString());

            config.setTxnId(matrixClient.getTxn().get());
            getHolder().setConfig(dao.save(config));
        });
    }

    protected StringBuilder writeStatus(Status status) {
        StringBuilder sb = new StringBuilder();

        Account account = status.getAccount();
        if (account != null) {
            sb.append("<a href=\"").append(account.getUrl()).append("\">").append(account.getDisplayName()).append("</a>");
        }

        LocalDateTime created = LocalDateTime.parse(status.getCreatedAt(), DateTimeFormatter.ISO_DATE_TIME);
        String formattedDateTime = created.format(DateTimeFormatter.ofPattern("MMM dd, yyyy, hh:mm", Locale.ENGLISH));
        sb.append(" at <a href=\"").append(status.getUrl()).append("\">").append(formattedDateTime).append("</a>");

        MxTootConfig config = holder.getConfig();
        MatrixClient matrixClient = holder.getMatrixClient();

        if (status.getInReplyToId() != null) {
            Status repliedStatus;
            try {
                repliedStatus = new Statuses(holder.getData().getMastodonClient()).getStatus(status.getInReplyToId()).execute();
            } catch (Mastodon4jRequestException e) {
                String msg = "Cannot get status: " + status.getInReplyToId();
                LOGGER.error("", e);
                matrixClient.sendFormattedNotice(config.getRoomId(), msg);
                return null;
            }
            sb.append(" in reply to <a href=\"").append(repliedStatus.getUrl()).append("\"></a>");
        }

        Status reblog = status.getReblog();
        if (reblog == null) {
            sb.append(" wrote:<br/>").append(status.getContent());

            List<Tag> tags = status.getTags();
            if (!tags.isEmpty()) {
                sb.append("<br/>");
                for (Tag tag : tags) {
                    sb.append("<a href=\"").append(tag.getUrl()).append("\">").append(tag.getName()).append("</a> ");
                }
            }

            List<Attachment> mediaAttachments = status.getMediaAttachments();
            if (!mediaAttachments.isEmpty()) {
                sb.append("<br/>");
                for (Attachment mediaAttachment : mediaAttachments) {
                    sb.append("<a href=\"").append(mediaAttachment.getUrl()).append("\"><img src=\"")
                        .append(mediaAttachment.getPreviewUrl()).append("\"/>");
                }
            }

            sb.append("</hr>");
        } else {
            sb.append(" boosted:<br/>").append(writeStatus(reblog));
        }

        return sb;
    }

    @Override
    public void onNotification(Notification notification) {

    }
}
