SELECT
    CONCAT_WS(' ',first_name, last_name) AS 'FUll Name',
        TIMESTAMPDIFF(DAY, born, died) AS 'Days Lived'
FROM book_library.authors;