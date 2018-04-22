-- Association between 3pid and mxid.
CREATE TABLE "association" (
  medium  TEXT   NOT NULL,
  address TEXT   NOT NULL,
  mxid    TEXT   NOT NULL,
  created BIGINT NOT NULL,
  expired BIGINT NOT NULL,
  ts      BIGINT NOT NULL,
  PRIMARY KEY (medium, address)
);

-- Expired associations.
CREATE TABLE "expired_association" (
  PRIMARY KEY (medium, address, expired)
)
  INHERITS ("association");

-- Validation session.
CREATE TABLE "session" (
  sid           TEXT PRIMARY KEY,
  token         TEXT                     NOT NULL,
  client_secret TEXT                     NOT NULL,
  medium        TEXT                     NOT NULL,
  address       TEXT                     NOT NULL UNIQUE,
  send_attempt  INT4,
  next_link     TEXT,
  created       TIMESTAMP WITH TIME ZONE NOT NULL,
  validated     TIMESTAMP WITH TIME ZONE
);

-- Invitation for the room.
CREATE TABLE "invitation" (
  medium       TEXT    NOT NULL,
  address      TEXT    NOT NULL,
  room_id      TEXT    NOT NULL,
  sender       TEXT    NOT NULL,
  token        TEXT    NOT NULL,
  public_key   TEXT [] NOT NULL,
  display_name TEXT    NOT NULL,
  PRIMARY KEY (medium, address, room_id)
);
