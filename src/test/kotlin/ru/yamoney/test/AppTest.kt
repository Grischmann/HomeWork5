package ru.yamoney.test


import java.math.BigDecimal
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.assertThat



class AppTest {

    @Test
    fun paymentTest() {
        val sum = BigDecimal("33")
        val depositOperation = Deposit("Вася", sum)
        val beforeBalance = billing.getUserBalance("Вася")
        billing.addOperation(depositOperation)                              //Пополнаяем Счет Васи на 33
        val paymentOperation = Payment("Вася", sum, "4454")
        val afterBalance = billing.getUserBalance("Вася")
        billing.addOperation(paymentOperation)                              //Вася платит за Твист и Рок&Ролл
        println("1 Пополняем Васе счёт - " + depositOperation)
        println("2 Проверяем Васин баланс до = " + beforeBalance)
        println("3 Вася платит в магазин за Твист и РокНРолл - " + paymentOperation)
        println("4 Проверим Васин Баланс после платежа = " + billing.getUserBalance("Вася"))

        assertThat("5 Проверяем, что Вася действительно заплатил и баланс равен ожидаемому",
                afterBalance, Matchers.comparesEqualTo(beforeBalance + sum))
    }

    @Test
    fun balanceTest() {
        val depositSum = BigDecimal("100")
        val user = "Depositmann"
        val beforeBalance = billing.getUserBalance(user)
        val depositOperation = Deposit(user, depositSum)
        billing.addOperation(depositOperation)

        val paymentOperation = Payment(user, depositSum, "4454")
        billing.addOperation(paymentOperation)
        val afterOperation = billing.getUserBalance(user)

        println(beforeBalance)
        println(depositOperation)
        println(paymentOperation)
        println(afterOperation)

        assertThat("Проверяем, что баланс Depositmann остался как и прежде (до пополнения и затем списания)",
                afterOperation, Matchers.equalTo(beforeBalance))
    }

    @Test
    fun shopInfoTest() {
        val user = "Kaufmann"
        val kaufSumEins = BigDecimal("100")
        val kaufSumZwei = BigDecimal("100")
        listBefore = billing.addOperation(paymentOperation)
        val paymentOperation = Payment(user, kaufSumEins, "4454")
        val paymentOperation2 = Payment(user, kaufSumZwei, "4454")
        billing.addOperation(paymentOperation2)
    // Payment 1
        println("Платеж №1 " + paymentOperation)
    // Payment 2
        println("Платеж №2 " + paymentOperation2)
    // shopInfo
        listAfter= billing.getShopIdOperations("4454"))

        assertThat("Проверяем, что в листе операций магазина стало на две записи больше ",
                listAfter.size - listBefore.size .equalTo(2))





        // val list = billing.getShopIdOperations("4454"))


        //val shopInfo = mutableListOf<String>()

                //.add(toString(paymentOperation))

        //billing.getShopIdOperations("4454")

       //billing.addOperation(shopInfo(shopInfo))
        //println("Смотрим в лист операций магазина " + shopInfo )
    // assert that shop has 2 payments
//        assertThat("Проверяем, что в магазине присутствуют две записи о платежах",
//                shopInfo("4454") )
    }

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
