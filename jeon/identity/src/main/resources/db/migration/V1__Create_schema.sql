CREATE TABLE "association" (
  address TEXT   NOT NULL,
  medium  TEXT   NOT NULL,
  mxid    TEXT   NOT NULL,
  created BIGINT NOT NULL,
  expired BIGINT NOT NULL,
  ts      BIGINT NOT NULL,
  PRIMARY KEY (address, medium)
);

CREATE TABLE "session" (
  sid           TEXT PRIMARY KEY,
  token         TEXT                     NOT NULL,
  client_secret TEXT                     NOT NULL,
  email         TEXT                     NOT NULL UNIQUE,
  send_attempt  INT4,
  next_link     TEXT,
  created       TIMESTAMP WITH TIME ZONE NOT NULL,
  validated     TIMESTAMP WITH TIME ZONE
);

CREATE TABLE "invite" (
  medium       TEXT NOT NULL,
  address      TEXT NOT NULL,
  room_id      TEXT NOT NULL,
  sender       TEXT NOT NULL,
  token        TEXT NOT NULL,
  public_key   TEXT NOT NULL,
  display_name TEXT NOT NULL,
  PRIMARY KEY (medium, address, room_id)
);
