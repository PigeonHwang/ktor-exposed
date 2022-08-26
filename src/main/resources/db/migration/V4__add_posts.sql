DROP TABLE IF EXISTS "posts";
CREATE TABLE IF NOT EXISTS "posts" (
                                       "id" BIGSERIAL PRIMARY KEY,
                                       "title" VARCHAR(255) NOT NULL,
    "content" TEXT NOT NULL,
    "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "user_id" BIGINT NOT NULL REFERENCES "users" ("id")
    );