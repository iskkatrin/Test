package mobi.sevenwinds.controller;


import mobi.sevenwinds.app.budget.BudgetService;
import mobi.sevenwinds.models.BudgetStatsDTO;
import mobi.sevenwinds.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    private AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Фильтрация по ФИО автора в статистике бюджета за год
    @GetMapping ("/budget/year/{year}/stats")
    public List<BudgetStatsDTO> getBudgetStatsByYearWithAuthorFilter(@PathVariable int year, @RequestParam(required = false) String authorNameFilter) {
        return authorService.getBudgetStatsByYear(year, authorNameFilter);

    }
}
