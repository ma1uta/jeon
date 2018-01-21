CREATE TABLE "user" (
  id          TEXT PRIMARY KEY,
  displayname TEXT UNIQUE,
  avatar_url  TEXT,
  password    TEXT NOT NULL,
  kind        TEXT DEFAULT 'user'
);

CREATE TABLE "device" (
  device_id    TEXT UNIQUE NOT NULL,
  user_id      TEXT        NOT NULL,
  display_name TEXT,
  last_seen_ip INET,
  last_seen_ts BIGINT,
  PRIMARY KEY (device_id, user_id),
  FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE "room" (
  id          TEXT PRIMARY KEY,
  displayname TEXT
);

CREATE TABLE "member" (
  user_id TEXT,
  room_id TEXT,
  PRIMARY KEY (room_id, user_id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (room_id) REFERENCES "room" (id)
);

CREATE TABLE "token" (
  token     TEXT UNIQUE,
  device_id TEXT,
  user_id   TEXT,
  PRIMARY KEY (token, device_id, user_id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (device_id) REFERENCES "device" (device_id)
);
