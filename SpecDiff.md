# Differences between Spec and implementation (Synapse)

Remark: there are not all differences but only those I found.

Fields which receives from the server but don't described in the spec:
* Event:
    * age
    * redacts
    * user_id
    * replaces_state
    * redacted_because
    * age_ts
* Sync response:
    * device_one_time_keys_count
    * groups (missing api)
* LeftRoom (from the sync response - rooms - leave):
    * account_data
* Device lists (from the sync response):
    * left
* Unsigned (inside the event):
    * prev_sender
    * replaces_state
 
Missing api:
* group/community api
