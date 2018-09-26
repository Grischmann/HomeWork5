package ru.yamoney.test


import java.math.BigDecimal
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.assertThat



class AppTest {

    @Test
    fun paymentTest() {
        val sum = BigDecimal("33")
        val Payer = "Вася"
        val shopID = "4454"
        val depositOperation = Deposit(Payer, sum)
        println("Пополняем Васе счёт - " + depositOperation)
        val beforeBalance = billing.getUserBalance(Payer)
        billing.addOperation(depositOperation)                              //Пополнаяем Счет Васи на 33
        println("Проверяем Васин баланс до = " + beforeBalance)

        val paymentOperation = Payment(Payer, sum, shopID)
        println("Вася платит в магазин за Твист и РокНРолл - " + paymentOperation)

        val afterBalance = billing.getUserBalance(Payer)
        println("Проверим Васин Баланс после платежа = " + billing.getUserBalance(Payer))

        billing.addOperation(paymentOperation)                              //Вася платит за Твист и Рок&Ролл
        assertThat("Проверяем, что Вася действительно заплатил и баланс равен ожидаемому",
                afterBalance, Matchers.comparesEqualTo(beforeBalance + sum))
    }

    @Test
      fun balanceTest() {
        val depositSum = BigDecimal("100")
        val payer = "Depositmann"
        val shopId = "4454"
        val beforeBalance = billing.getUserBalance(payer)
        println(beforeBalance)

        val depositOperation = Deposit(payer, depositSum)
        billing.addOperation(depositOperation)
        println(depositOperation)

        val paymentOperation = Payment(payer, depositSum, shopId)
        billing.addOperation(paymentOperation)
        println(paymentOperation)

        val afterOperation = billing.getUserBalance(payer)
        println(afterOperation)

        assertThat("Проверяем, что баланс Depositmann остался как и прежде (до пополнения и затем списания)",
                afterOperation, Matchers.equalTo(beforeBalance))
    }

    @Test
    fun shopInfoTest() {
        val user = "Kaufmann"
        val price1 = BigDecimal("100")
        val price2 = BigDecimal("200")
        val shopId= "4454"
        listBefore = billing.addOperation(paymentOperation)
        val paymentOperation = Payment(user, price1, shopId)
        println("Платеж №1 " + paymentOperation)
        
        billing.addOperation(paymentOperation2)
        val paymentOperation2 = Payment(user, price2, shopId)
        println("Платеж №2 " + paymentOperation2)   
  
        listAfter= billing.getShopIdOperations(shopId))

        assertThat("Проверяем, что в листе операций магазина стало на две записи больше ",
                listAfter.size - listBefore.size .equalTo(2))

    @Test
    fun payment() {
        main(arrayOf("payment", "Вася", "100", "4454"))
    }

    @Test
    fun deposit() {
        main(arrayOf("deposit", "Вася", "123", "4454"))
    }

    @Test
    fun balance() {
        main(arrayOf("balance", "Вася"))
    }

    @Test
    fun shopInfo() {
        main(arrayOf("shop_info", "4454"))
    }
}
