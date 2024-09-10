SELECT title
FROM book_library.books
WHERE SUBSTRING(title, 1, 4) = 'The ';
/*
SELECT title
FROM book_library.books
WHERE title LIKE('The%');*/