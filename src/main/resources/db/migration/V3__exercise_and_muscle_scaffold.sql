-- Add muscles and pre-made exercises
DELETE
FROM image
WHERE id <= 20;
INSERT INTO image (id, url)
VALUES (1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Bench_press_2.svg/1200px-Bench_press_2.svg.png'),
       (2,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Biceps_curl_with_dumbbell_2.svg/1200px-Biceps_curl_with_dumbbell_2.svg.png'),
       (3,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Wide_grip_lat_pull_down_2.svg/793px-Wide_grip_lat_pull_down_2.svg.png'),
       (4,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Barbell_dead_lifts_1.svg/525px-Barbell_dead_lifts_1.svg.png'),
       (5, 'https://upload.wikimedia.org/wikipedia/commons/8/82/Squats.svg'),
       (6, 'https://upload.wikimedia.org/wikipedia/commons/5/59/Dipexercise.svg'),
       (7,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Barbell_shoulder_press_2.svg/1529px-Barbell_shoulder_press_2.svg.png'),
       (8,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Standing_leg_curls_2.svg/853px-Standing_leg_curls_2.svg.png'),
       (9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Air_bike_2.svg/2560px-Air_bike_2.svg.png'),
       (10,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Decline_ez_bar_triceps_extension_with_barbell_1.svg/885px-Decline_ez_bar_triceps_extension_with_barbell_1.svg.png');

DELETE
FROM muscle
WHERE id <= 22;
INSERT INTO muscle
    (id, name)
VALUES (1, 'TRAPEZIUS'),
       (2, 'UPPER_BACK'),
       (3, 'LOWER_BACK'),
       (4, 'CHEST'),
       (5, 'BICEPS'),
       (6, 'TRICEPS'),
       (7, 'FOREARM'),
       (8, 'BACK_DELTOIDS'),
       (9, 'FRONT_DELTOIDS'),
       (10, 'ABS'),
       (11, 'OBLIQUES'),
       (12, 'ABDUCTOR'),
       (13, 'ABDUCTORS'),
       (14, 'HAMSTRING'),
       (15, 'QUADRICEPS'),
       (16, 'CALVES'),
       (17, 'GLUTEAL'),
       (18, 'HEAD'),
       (19, 'NECK'),
       (20, 'KNEES'),
       (21, 'LEFT_SOLEUS'),
       (22, 'RIGHT_SOLEUS');

DELETE
FROM exercise
WHERE id <= 22;
INSERT INTO exercise
    (id, name, description, image_id, exercise_value_type_id)
VALUES (1, 'BENCH_PRESS', 'An exercise where the user presses a weight upwards while lying on a bench.', 1, 3),
       (2, 'BICEPS_CURL', 'An exercise where the user curls a set of dumb-bells.', 2, 3),
       (3, 'WIDE_GRIP_LAT_PULL_DOWN',
        'An exercise where the user, using a wide grip, pulls a bar down towards the top of their chest.', 3, 3),
       (4, 'BARBELL_DEADLIFT',
        'An exercise where the user lifts a barbell from the ground to their hips and straightens their back.', 4, 3),
       (5, 'BARBELL_SQUATS',
        'An exercise where the user, carrying a barbell on their neck, lowers their hips from a standing position until their knee bends at a 90 degree angle before standing back up.',
        5, 3),
       (6, 'DIPS',
        'An exercise where the user supports themselves on a dip bar before lowering themselves slowly until their arms are bent at a 90 degree angle before lifting themselves back up.',
        6, 1),
       (7, 'BARBELL_SHOULDER_PRESS',
        'An exercise where the user presses a barbell over their head while sitting or standing.', 7, 3),
       (8, 'STANDING_LEG_CURLS',
        'An exercise where, while standing, the user curls their lower leg against some type of resistance.', 8, 3),
       (9, 'AIR_BIKE',
        'An exercise where a user lays down and lifts their legs and bikes in the air to work their abdominal muscles.',
        9, 2),
       (10, 'BARBELL_LYING_TRICEPS_EXTENSION',
        'An exercise where the user lies down, extends a barbell wide-grip behind their head until their arm bends at a 90 degree angle and then lifts it backup directly above their face.',
        10, 3);

INSERT INTO exercise_muscle
    (exercise_id, muscle_id)
VALUES (1, 4),
       (1, 6),
       (2, 5),
       (3, 2),
       (3, 5),
       (4, 2),
       (4, 3),
       (4, 15),
       (5, 15),
       (5, 17),
       (5, 14),
       (6, 6),
       (7, 1),
       (7, 8),
       (7, 9),
       (8, 14),
       (8, 16),
       (9, 10),
       (10, 6);