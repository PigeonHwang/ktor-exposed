DROP TYPE IF EXISTS "user_role";
CREATE TYPE "user_role" AS ENUM('USER', 'ADMIN');

ALTER TABLE "users" ADD COLUMN "roles" user_role[] NOT NULL DEFAULT '{USER}';