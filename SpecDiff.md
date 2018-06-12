# Differences between Spec and implementation (Synapse)

Remark: there are not all differences but only those I found.

Fields:
* Event (io.github.ma1uta.matrix.Event):
    * age
    * redacts
    * user_id
    * replaces_state
    * redacted_because
* LeftRoom (io.github.ma1uta.matrix.client.model.sync.LeftRoom)
    * account_data
* Device lists (io.github.ma1uta.matrix.client.model.sync.DeviceLists):
    * left
* Sync response (io.github.ma1uta.matrix.client.model.sync.SyncResponse):
    * device_one_time_keys_count
    * groups
* Unsigned (io.github.ma1uta.matrix.Unsigned):
    * prev_sender
    * replaces_state
 
Api:
* group (community api)
* incompleted federation api
