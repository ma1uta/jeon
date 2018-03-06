CREATE TABLE "association" (
  address TEXT   NOT NULL,
  medium  TEXT   NOT NULL,
  mxid    TEXT   NOT NULL,
  created BIGINT NOT NULL,
  expired BIGINT NOT NULL,
  ts      BIGINT NOT NULL,
  PRIMARY KEY (address, medium)
);
