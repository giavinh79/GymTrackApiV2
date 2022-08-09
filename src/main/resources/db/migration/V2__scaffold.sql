-- Create dummy data for testing and debugging

-- Create users
DELETE
FROM app_user
WHERE app_user.id = 1;
INSERT INTO app_user
(id, first_name, last_name, email, last_logged_in_date, last_logged_in_ip, created_at, updated_at, deleted_at,
 location_id, image_id)
VALUES (1, 'Test', 'User', 'tester@gmail.com', null, null, NOW(), null, null, null, null);

-- Create routines
DELETE
FROM routine
WHERE routine.id = 1;
INSERT INTO routine
(id, public_id, creator_id, image_id, name, description, rating, created_at, updated_at, deleted_at, num_times_copied,
 base_routine_id)
VALUES (1, 'testroutine1', 1, null, 'Zertovsky''s Routine', 'PPL with heavy emphasis on chest', 5.0, NOW(), null, null,
        0, null);

-- Connect users with routines
DELETE
FROM app_user_routine
WHERE app_user_routine.routine_id = 1;
INSERT INTO app_user_routine
    (app_user_id, routine_id, is_selected, updated_at, created_at)
VALUES (1, 1, true, null, NOW());
