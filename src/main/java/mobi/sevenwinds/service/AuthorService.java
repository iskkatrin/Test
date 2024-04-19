package mobi.sevenwinds.service;

import mobi.sevenwinds.exception.AuthorNotFoundException;
import mobi.sevenwinds.models.Author;
import mobi.sevenwinds.models.Budget;
import mobi.sevenwinds.repository.AuthorRepository;
import mobi.sevenwinds.repository.BudgetRepository;

import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private BudgetRepository budgetRepository;

    //Добавление возможности указать ID автора при создании записи в Budget
    public Budget createBudgetWithAuthor(int year, int month, int amount, String type, int authorId) {
        Author author = authorRepository.findById(authorId);
        if (author != null) {
            Budget budget = new Budget(year, month, amount, type, author);
            budget.setAuthor(author);
            return budgetRepository.save(budget);
        } else {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found.");
        }
    }

    //Вывод ФИО автора и время создания записи автора в статистике бюджета за год
    public List<Budget> getBudgetStatsByYear(int year) {
        List<Budget> budgets = budgetRepository.findByYear(year);
        for (Budget budget : budgets) {
            Author author = budget.getAuthor();
            if (author != null) {
                System.out.println("Author Name: " + author.getFullName());
                System.out.println("Author Created At: " + author.getCreatedAt());
            }
        }
        return budgets;
    }
}
