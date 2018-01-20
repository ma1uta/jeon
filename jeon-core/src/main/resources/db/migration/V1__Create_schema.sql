CREATE TABLE "user" (
  id          TEXT PRIMARY KEY,
  displayname TEXT,
  avatar      BYTEA,
  password    TEXT NOT NULL,
  kind        TEXT DEFAULT 'user'
);

CREATE TABLE "device" (
  device TEXT,
  userId TEXT,
  name   TEXT,
  token  TEXT,
  PRIMARY KEY (device, userId),
  FOREIGN KEY (userId) REFERENCES "user" (id)
);

CREATE TABLE "room" (
  id          TEXT PRIMARY KEY,
  displayname TEXT
);

CREATE TABLE "member" (
  userId TEXT,
  roomId TEXT,
  PRIMARY KEY (roomId, userId)
)
