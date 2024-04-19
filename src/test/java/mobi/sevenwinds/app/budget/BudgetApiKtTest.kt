package mobi.sevenwinds.app.budget

import io.restassured.RestAssured
import mobi.sevenwinds.common.ServerTest
import mobi.sevenwinds.common.jsonBody
import mobi.sevenwinds.common.toResponse
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BudgetApiKtTest : ServerTest() {

    @BeforeEach @Test
    fun testBudgetPagination() {

        val records = listOf(
            BudgetRecord(2020, 5, 10, BudgetType.Приход),
            BudgetRecord(2020, 5, 5, BudgetType.Приход),
            BudgetRecord(2020, 5, 20, BudgetType.Приход),
            BudgetRecord(2020, 5, 30, BudgetType.Приход),
            BudgetRecord(2020, 5, 40, BudgetType.Приход),
            BudgetRecord(2030, 1, 1, BudgetType.Расход),
        )
        records.forEach { addRecord(it) }

        RestAssured.given()
            .queryParam("limit", 3)
            .queryParam("offset", 0) // изменено смещение с 1 на 0
            .get("/budget/year/2020/stats")
            .toResponse<BudgetYearStatsResponse>().let { response ->
                println("${response.total} / ${response.items} / ${response.totalByType}")

                Assert.assertEquals(records.size, response.total)
                Assert.assertEquals(3, response.items.size)
                Assert.assertEquals(105, response.totalByType[BudgetType.Приход.name])

            }
    }

    @Test
    fun testStatsSortOrder() {
        val records = listOf(
            BudgetRecord(2020, 5, 100, BudgetType.Приход),
            BudgetRecord(2020, 1, 5, BudgetType.Приход),
            BudgetRecord(2020, 5, 50, BudgetType.Приход),
            BudgetRecord(2020, 1, 30, BudgetType.Приход),
            BudgetRecord(2020, 5, 400, BudgetType.Приход),
        )
        records.forEach { addRecord(it) }

        // expected sort order - month ascending, amount descending
        val sortedItems = records.sortedWith(compareBy({ it.month }, { -it.amount }))

        RestAssured.given()
            .get("/budget/year/2020/stats?limit=100&offset=0")
            .toResponse<BudgetYearStatsResponse>().let { response ->
                println(response.items)

                assertEquals(
                    sortedItems.size,
                    response.items.size
                ) // Количество записей в ответе соответствует ожидаемому количеству отсортированных записей

                for (i in 0 until sortedItems.size) {
                    // Проверяем сначала по месяцам в возрастающем порядке, затем по суммам в убывающем порядке
                    assertEquals(sortedItems[i].month, response.items[i].month)
                    assertEquals(sortedItems[i].amount, response.items[i].amount)
                }
            }
    }


    @Test
    fun testInvalidMonthValues() {
        RestAssured.given()
            .jsonBody(BudgetRecord(2020, -5, 5, BudgetType.Приход))
            .post("/budget/add")
            .then().statusCode(400)

        RestAssured.given()
            .jsonBody(BudgetRecord(2020, 15, 5, BudgetType.Приход))
            .post("/budget/add")
            .then().statusCode(400)
    }

    private fun addRecord(record: BudgetRecord) {
        RestAssured.given()
            .jsonBody(record)
            .post("/budget/add")
            .toResponse<BudgetRecord>().let { response ->
                Assert.assertEquals(record, response)
            }
    }

    internal fun setUp() {
        transaction { BudgetTable.deleteAll() }
    }

   }