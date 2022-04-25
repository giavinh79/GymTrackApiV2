-- Create functions for db triggers
CREATE OR REPLACE FUNCTION trigger_update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now(); -- now() is equivalent to CURRENT_TIMESTAMP
RETURN NEW;
END;
$$ language 'plpgsql';

-- Create location table
CREATE TABLE location(
    id SERIAL PRIMARY KEY,
    google_place_id varchar(100),
    address varchar(100),
    city varchar(100),
    zip varchar(50)
);

-- Create image table
CREATE TABLE image(
     id SERIAL PRIMARY KEY,
     url varchar(255),
     file_id varchar(255),
     created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
     extension varchar(25)
);

-- Create user tables & triggers (since user is a reserved keyword, app_user is used instead)
CREATE TABLE app_user(
    id SERIAL PRIMARY KEY,
    firebase_id varchar(100) NOT NULL,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(100),
    last_logged_in_date timestamptz,
    last_logged_in_ip varchar(50),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamptz,
    deleted_at timestamptz,
    location_id int,
    image_id int,
    is_super_admin boolean NOT NULL DEFAULT FALSE,
    FOREIGN KEY (location_id) REFERENCES location(id) ON DELETE SET NULL,
    FOREIGN KEY (image_id) REFERENCES image(id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX firebase_id_idx ON app_user(firebase_id);

CREATE TRIGGER set_user_updated_timestamp
    BEFORE UPDATE ON app_user
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_update_timestamp();

-- Create settings tables
CREATE TABLE setting(
    id SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE app_user_setting(
    app_user_id int NOT NULL,
    setting_id int NOT NULL,
    enabled boolean NOT NULL DEFAULT FALSE,
    FOREIGN KEY(app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    FOREIGN KEY(setting_id) REFERENCES setting(id) ON DELETE CASCADE,
    CONSTRAINT app_user_setting_cpk PRIMARY KEY (app_user_id, setting_id)
);

-- Create tag tables
CREATE TABLE tag_type(
    id SERIAL PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL
);
INSERT INTO tag_type VALUES (1, 'ROUTINE');

CREATE TABLE tag(
    id SERIAL PRIMARY KEY,
    name varchar(50) NOT NULL,
    tag_type_id int NOT NULL,
    FOREIGN KEY(tag_type_id) REFERENCES tag_type(id) ON DELETE CASCADE
);

-- Create routine tables
CREATE TABLE routine(
    id SERIAL PRIMARY KEY,
    public_id varchar(100),
    creator_id int NOT NULL,
    image_id int,
    name varchar(100),
    description varchar(255),
    rating decimal(4,3) CHECK (rating <= 5),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamptz,
    deleted_at timestamptz,
    num_times_copied int NOT NULL DEFAULT 0,
    base_routine_id int,
    FOREIGN KEY(creator_id) REFERENCES app_user(id) ON DELETE SET NULL,
    FOREIGN KEY(image_id) REFERENCES image(id) ON DELETE SET NULL
);
CREATE INDEX name_idx ON routine(name);
CREATE INDEX rating_idx ON routine(rating);

CREATE TABLE app_user_routine(
    app_user_id int NOT NULL,
    routine_id int NOT NULL,
    is_selected boolean NOT NULL DEFAULT FALSE,
    updated_at timestamptz,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY(app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    FOREIGN KEY(routine_id) REFERENCES routine(id) ON DELETE CASCADE,
    CONSTRAINT app_user_routine_cpk PRIMARY KEY (app_user_id, routine_id)
);

CREATE TABLE routine_rating(
    id SERIAL PRIMARY KEY,
    routine_id int NOT NULL,
    app_user_id int NOT NULL,
    FOREIGN KEY(routine_id) REFERENCES routine(id) ON DELETE CASCADE,
    FOREIGN KEY(app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    rating decimal(4,3) CHECK (rating <= 5)
);

CREATE TABLE routine_tag(
    routine_id int NOT NULL,
    tag_id int NOT NULL,
    FOREIGN KEY(routine_id) REFERENCES routine(id) ON DELETE CASCADE,
    FOREIGN KEY(tag_id) REFERENCES tag(id) ON DELETE CASCADE,
    CONSTRAINT routine_tag_cpk PRIMARY KEY (routine_id, tag_id)
);

-- Create and populate muscles table
CREATE TABLE muscle(
    id SERIAL PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL
);
INSERT INTO muscle VALUES (1, 'PECS');

-- Create exercise tables
CREATE TABLE exercise_value_type(
    id SERIAL PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL
);
INSERT INTO exercise_value_type VALUES (1, 'REPS'), (2, 'TIME_IN_SEC'), (3, 'TIME_IN_MIN'), (4, 'WEIGHT_LBS'), (5, 'WEIGHT_KGS');

CREATE TABLE exercise(
    id SERIAL PRIMARY KEY,
    event_log_type_id int NOT NULL,
    app_user_id int,
    description text,
    image_id int NOT NULL,
    exercise_value_type_id int NOT NULL,
    default_value varchar(20) NOT NULL,
    default_num_sets int NOT NULL,
    created_at timestamptz,
    creator_id int,
    FOREIGN KEY (image_id) REFERENCES image(id) ON DELETE SET NULL,
    FOREIGN KEY (exercise_value_type_id) REFERENCES exercise_value_type(id) ON DELETE CASCADE,
    FOREIGN KEY (creator_id) REFERENCES app_user(id) ON DELETE CASCADE
);

CREATE TABLE exercise_muscle(
    exercise_id int NOT NULL,
    muscle_id int NOT NULL,
    FOREIGN KEY(exercise_id) REFERENCES exercise(id) ON DELETE CASCADE,
    FOREIGN KEY(muscle_id) REFERENCES muscle(id) ON DELETE CASCADE,
    CONSTRAINT exercise_muscle_cpk PRIMARY KEY (exercise_id, muscle_id)
);

CREATE TYPE day_enum AS ENUM ('MONDAY', 'TUESDAY','WEDNESDAY','THURSDAY','FRIDAY', 'SATURDAY', 'SUNDAY');
CREATE TABLE routine_exercise(
    id SERIAL PRIMARY KEY,
    routine_id int NOT NULL,
    exercise_id int NOT NULL,
    day day_enum DEFAULT 'MONDAY',
    exercise_order int NOT NULL DEFAULT 1,
    FOREIGN KEY(routine_id) REFERENCES routine(id) ON DELETE CASCADE,
    FOREIGN KEY(exercise_id) REFERENCES exercise(id) ON DELETE CASCADE
);
CREATE INDEX day_idx ON routine_exercise(day);

-- Create session/workout tables
CREATE TABLE session_log(
    id SERIAL PRIMARY KEY,
    routine_id int NOT NULL,
    app_user_id int NOT NULL,
    start_time timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_time timestamptz,
    length_in_min decimal NOT NULL,
    FOREIGN KEY (routine_id) REFERENCES routine(id) ON DELETE CASCADE,
    FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE
);

CREATE TABLE session_exercise_log(
     id SERIAL PRIMARY KEY,
     exercise_id int NOT NULL,
     session_log_id int NOT NULL,
     start_time timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
     end_time timestamptz,
     length_in_min decimal NOT NULL,
     num_sets int NOT NULL,
     FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE,
     FOREIGN KEY (session_log_id) REFERENCES session_log(id) ON DELETE CASCADE
);

CREATE TABLE session_exercise_set_log(
    id SERIAL PRIMARY KEY,
    session_exercise_log_id int NOT NULL,
    value varchar(20) NOT NULL,
    FOREIGN KEY(session_exercise_log_id) REFERENCES session_exercise_log(id) ON DELETE CASCADE
);

-- Create event tables
CREATE TABLE event_log_type(
    id SERIAL PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);

CREATE TABLE event_log(
    id SERIAL PRIMARY KEY,
    event_log_type_id int NOT NULL,
    app_user_id int,
    description text,
    value varchar(50),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (event_log_type_id) REFERENCES event_log_type(id) ON DELETE CASCADE
);

-- Create feature flag tables
CREATE TABLE feature_flag(
    id SERIAL PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
    description varchar(255) NOT NULL
);

CREATE TABLE user_feature_flag(
    id SERIAL PRIMARY KEY,
    app_user_id int NOT NULL,
    feature_flag_id int NOT NULL,
    FOREIGN KEY (feature_flag_id) REFERENCES feature_flag(id) ON DELETE CASCADE
);

