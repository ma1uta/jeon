# Jeon 

Yet another java implementation of the [Matrix](https://matrix.org) protocol, sdk and some application
services (currently exists only Matrix-Mastodon bot).

## Modules

* common-api - common classes used by all apis.
* client-api - [client-server api](https://matrix.org/docs/spec/client_server/r0.3.0.html).
* application-api - [application api](https://matrix.org/docs/spec/application_service/unstable.html).
* identity-api - [identity api](https://matrix.org/docs/spec/identity_service/unstable.html).
* push-api - [push api](https://matrix.org/docs/spec/push_gateway/unstable.html).
* server-api - [server-server api](https://matrix.org/docs/spec/server_server/unstable.html) (WIP).
* client-sdk - matrix client and template of the bot.
* common-backend - common classes of all implementations (WIP).
* identity-backend - common classes of the the identity server's realization.
* identity-dropwizard - sample implementation of the identity server using [dropwizard framework](https://www.dropwizard.io/) (Proof-of-concept, not ready for production).
* mxtoot - [matrix-mastodon bot](mxtoot/Readme.md).

