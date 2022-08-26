--DROP TYPE IF EXISTS "user_role";

DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
   "id" BIGSERIAL PRIMARY KEY,
   "username" VARCHAR(255) NOT NULL,
   "password" VARCHAR(255) NOT NULL,
   UNIQUE ("username")
);