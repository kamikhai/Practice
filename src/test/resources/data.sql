INSERT INTO public.academic_group (id, numeric)
VALUES (2, '11-802'),
       (3, '11-803');

INSERT INTO public.app_user (id, email, full_name, role, photo_path, pass_hash)
VALUES (2, 'test@test.test', 'Тест Тестович', 'STUDENT', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       (3, '123@test.test', 'Тестовый студент', 'STUDENT', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       (4, 'test@123.test', 'Тестовый препод', 'TEACHER', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS'),
       (5, 'test@test.123', 'Тестовый админ', 'ADMIN', '/img/empty_user.jpg', '$2y$10$3iKuQFwLBC/8XS7k7jr5secLLhK.IGdjymk1jgSEdRtBhcWeWnytS');

INSERT INTO public.job_profile (id, title)
VALUES (2, 'Java-разработчик'),
       (3, 'Web-вёрстка');

INSERT INTO public.student (description, user_id, group_id, job_profile_id, work_experience, link)
VALUES ('я первый', 2, 2, 2, '-', 'https://vk.com/id0'),
       ('я второй', 3, 3, 3, '-', 'https://vk.com/id0');

INSERT INTO public.teacher (user_id, information, position, link)
VALUES (4, 'закончил 9 классов', 'хуй с горы', 'https://vk.com/id0');

INSERT INTO public.teacher_curated_groups (teacher_user_id, curated_groups_id)
VALUES (4, 2),
       (4, 3);

INSERT INTO public.tag (id, name)
VALUES (2, 'Java'),
       (3, 'Spring'),
       (4, 'Javascript');

INSERT INTO public.competence (id, description, confirmed_by_user_id, student_user_id)
VALUES (2, 'я знаю яву', 4, 2),
       (3, 'весну тоже', 4, 2),
       (4, 'я знаю джаву', null, 3),
       (5, 'также скрипт', 4, 3);

INSERT INTO public.competence_tags (competence_id, tags_id)
VALUES (2, 2),
       (3, 3),
       (4, 2),
       (5, 4);

INSERT INTO public.project (id, title, student_user_id, description)
VALUES (2, 'классный стартап', 2, 'какое-то говно'),
       (3, 'крутейший проект', 3, 'какое-то говно');

INSERT INTO public.project_tags (project_id, tags_id)
VALUES (2, 3),
       (2, 3),
       (3, 4);