package mobi.sevenwinds.repository;

import mobi.sevenwinds.models.Budget;

import java.util.List;

@Repository
public interface BudgetRepository  {
    Budget save (Budget budget);

    List<Budget> findByYear(int year);
}
