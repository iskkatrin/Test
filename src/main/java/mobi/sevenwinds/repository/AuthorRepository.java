package mobi.sevenwinds.repository;

import mobi.sevenwinds.models.Author;

@Repository
public interface AuthorRepository {
    Author findById(int id);
    Author save(Author author);

}
