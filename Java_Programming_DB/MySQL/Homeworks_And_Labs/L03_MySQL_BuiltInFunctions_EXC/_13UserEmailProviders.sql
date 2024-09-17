SELECT user_name, SUBSTRING(email, LOCATE('@', email) + 1) AS `emal_provider`
FROM diablo.users
ORDER BY emal_provider, user_name;